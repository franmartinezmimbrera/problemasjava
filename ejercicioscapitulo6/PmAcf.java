// fichero PmAcf.java 

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Queue; 
import java.util.LinkedList;

/** Interfaz base para los mensajes */
interface Mensaje {}

class PeticionCoger implements Mensaje {
    public final int idFilosofo;
    public final BlockingQueue<Boolean> canalRespuesta; 

    public PeticionCoger(int id, BlockingQueue<Boolean> canal) {
        this.idFilosofo = id;
        this.canalRespuesta = canal;
    }
}
class PeticionSoltar implements Mensaje {
    public final int idFilosofo;
    public PeticionSoltar(int id) {
        this.idFilosofo = id;
    }
}


class MesaActor extends Thread {
    
    public static final int N_FILOSOFOS = 5;
    private final BlockingQueue<Mensaje> buzonPeticiones = new LinkedBlockingQueue<>();
    private final boolean[] palillosLibres = new boolean[N_FILOSOFOS];
    private final Queue<PeticionCoger> filosofosEnEspera = new LinkedList<>();

    public MesaActor() {
        for (int i = 0; i < N_FILOSOFOS; i++) {
            palillosLibres[i] = true; 
        }
        this.start(); 
    }
    public void enviarPeticion(Mensaje msg) throws InterruptedException {
        this.buzonPeticiones.put(msg);
    }
    @Override
    public void run() {
        System.out.println("Mesa (Servidor) iniciada. Esperando peticiones...");
        try {
            while (true) {
                Mensaje msg = buzonPeticiones.take();
                if (msg instanceof PeticionCoger) {
                    procesarPeticionCoger((PeticionCoger) msg);
                } 
                else if (msg instanceof PeticionSoltar) {
                    procesarPeticionSoltar((PeticionSoltar) msg);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Mesa (Servidor) interrumpida.");
        }
    }
    private void procesarPeticionCoger(PeticionCoger p) throws InterruptedException {
        int id = p.idFilosofo;
        int izq = id;
        int der = (id + 1) % N_FILOSOFOS;
        
        if (palillosLibres[izq] && palillosLibres[der]) {
            palillosLibres[izq] = false;
            palillosLibres[der] = false;
            System.out.println("Mesa: Concediendo palillos a Filósofo " + id);
            p.canalRespuesta.put(true); 
        } else {
            // Poner en espera
            System.out.println("Mesa: Filósofo " + id + " en espera (palillos ocupados).");
            filosofosEnEspera.add(p);
        }
    }
    private void procesarPeticionSoltar(PeticionSoltar p) throws InterruptedException {
        int id = p.idFilosofo;
        int izq = id;
        int der = (id + 1) % N_FILOSOFOS;
        palillosLibres[izq] = true;
        palillosLibres[der] = true;
        System.out.println("Mesa: Filósofo " + id + " soltó palillos.");

        // Revisar cola de espera
        int enEsperaSize = filosofosEnEspera.size();
        for (int i = 0; i < enEsperaSize; i++) {
            PeticionCoger peticion = filosofosEnEspera.poll(); 
            
            int p_id = peticion.idFilosofo;
            int p_izq = p_id;
            int p_der = (p_id + 1) % N_FILOSOFOS;

            if (palillosLibres[p_izq] && palillosLibres[p_der]) {
                palillosLibres[p_izq] = false;
                palillosLibres[p_der] = false;
                System.out.println("Mesa: Concediendo palillos a " + p_id + " (estaba en espera).");
                peticion.canalRespuesta.put(true); 
            } else {
                filosofosEnEspera.add(peticion);
            }
        }
    }
}

class FilosofoActor extends Thread {

    private final int id;
    private final MesaActor mesa; 
    private final Random random = new Random();
    private final BlockingQueue<Boolean> miBuzon = new ArrayBlockingQueue<>(1);

    public FilosofoActor(int id, MesaActor mesa) {
        this.id = id;
        this.mesa = mesa;
    }
    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está PENSANDO.");
        Thread.sleep(random.nextInt(2000) + 1000);
    }
    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está COMIENDO.");
        Thread.sleep(random.nextInt(1500) + 1000);
    }
    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                System.out.println("Filósofo " + id + " tiene HAMBRE y pide palillos.");
                PeticionCoger peticion = new PeticionCoger(id, miBuzon);
                mesa.enviarPeticion(peticion);
                miBuzon.take();
                System.out.println("Filósofo " + id + " obtuvo permiso y palillos.");
                comer();                
                System.out.println("Filósofo " + id + " terminó de comer. Suelta palillos.");
                mesa.enviarPeticion(new PeticionSoltar(id));
            }
        } catch (InterruptedException e) {
            System.out.println("Filósofo " + id + " fue interrumpido.");
        }
    }
}

public class PmAcf {
    public static void main(String[] args) {
        System.out.println("Iniciando la Cena de los Filósofos (Paso de Mensajes Asíncrono)....");
        
        // Usamos las clases renombradas
        MesaActor mesa = new MesaActor();
        for (int i = 0; i < MesaActor.N_FILOSOFOS; i++) {
            new FilosofoActor(i, mesa).start();
        }
    }
}
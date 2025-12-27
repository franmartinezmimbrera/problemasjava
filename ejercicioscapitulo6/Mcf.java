//fichero Mfc.java
import java.util.Random;

/**
 * Monitor que implementa la solución del CAMARERO (Árbitro).
 * Clave: Limita el número de comensales sentados a (N-1) para evitar interbloqueo.
 */
class MesaCamarero {

    public static final int N_FILOSOFOS = 5;
    // Semáforo lógico (aforo): Máximo 4 filósofos sentados a la vez
    private static final int AFORO_MAXIMO = N_FILOSOFOS - 1; 
    
    private final boolean[] palillosLibres = new boolean[N_FILOSOFOS];
    private int comensalesSentados = 0; // Contador de gente sentada a la mesa

    public MesaCamarero() {
        for (int i = 0; i < N_FILOSOFOS; i++) {
            palillosLibres[i] = true;
        }
    }

    /**
     * Intenta coger los palillos.
     * 1. Primero pide permiso para sentarse (si hay 4 sentados, espera).
     * 2. Luego intenta coger los palillos (si están ocupados, espera).
     */
    public synchronized void cogerPalillos(int id) throws InterruptedException {
        int izq = id;
        int der = (id + 1) % N_FILOSOFOS;

        // Si la mesa está casi llena (4 personas), el 5º se queda de pie esperando.
        while (comensalesSentados >= AFORO_MAXIMO) {
            System.out.println("    Filósofo " + id + " ESPERA DE PIE (Mesa llena: " + comensalesSentados + " comensales).");
            wait();
        }       
        // Si pasa aquí, se sienta
        comensalesSentados++;
        System.out.println("Filósofo " + id + " se sienta a la mesa (Hay " + comensalesSentados + " sentados).");

        // Ahora que está sentado, intenta coger los tenedores.
        while (!palillosLibres[izq] || !palillosLibres[der]) {
            System.out.println("    Filósofo " + id + " espera cubiertos (Sentado, pero sin tenedores).");
            wait();
        }

        // Reserva los tenedores
        palillosLibres[izq] = false;
        palillosLibres[der] = false;
        
        System.out.println("Filósofo " + id + " COGIÓ los palillos " + izq + " y " + der);
    }

    /**
     * Suelta los palillos y se levanta de la mesa.
     */
    public synchronized void soltarPalillos(int id) {
        int izq = id;
        int der = (id + 1) % N_FILOSOFOS;

        // Libera tenedores
        palillosLibres[izq] = true;
        palillosLibres[der] = true;

        comensalesSentados--; // Deja un hueco libre en la mesa
        
        System.out.println("Filósofo " + id + " soltó palillos y SE LEVANTÓ (Quedan " + comensalesSentados + " sentados).");
        
        // Avisa a todos:
        // - A los que esperan de pie (wait del aforo)
        // - A los que esperan cubiertos (wait de palillos)
        notifyAll();
    }
}

class Filosofo extends Thread {
    private final int id;
    private final MesaCamarero mesa;
    private final Random random = new Random();

    public Filosofo(int id, MesaCamarero mesa) {
        this.id = id;
        this.mesa = mesa;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está PENSANDO ...");
        Thread.sleep(random.nextInt(1000) + 500); 
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está COMIENDO ...");
        Thread.sleep(random.nextInt(1000) + 500); 
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                System.out.println("Filósofo " + id + " tiene HAMBRE.");
                
                mesa.cogerPalillos(id);
                comer();
                mesa.soltarPalillos(id);
            }
        } catch (InterruptedException e) {
            System.out.println("Filósofo " + id + " fue interrumpido.");
        }
    }
}

public class Mcf {
    public static void main(String[] args) {
        System.out.println("EJEMPLO CENA DE LOS FILÓSOFOS (SOLUCIÓN DEL CAMARERO/AFORO) ");
        System.out.println("  Mesa para " + MesaCamarero.N_FILOSOFOS + " filósofos.");
        System.out.println("  El camarero solo deja sentarse a " + (MesaCamarero.N_FILOSOFOS - 1) + " a la vez.\n");

        MesaCamarero mesa = new MesaCamarero();
        
        // Creamos y arrancamos los hilos
        for (int i = 0; i < MesaCamarero.N_FILOSOFOS; i++) {
            new Filosofo(i, mesa).start();
        }
    }
}
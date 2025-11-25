// fichero Mcf.java 
import java.util.Random;

//La clase Mesa gestiona los recursos compartidos (palillos)
//usando el patrón Monitor (synchronized, wait, notifyAll)

class Mesa {

    public static final int N_FILOSOFOS = 5;
    private final boolean[] palillosLibres = new boolean[N_FILOSOFOS];

    public Mesa() {
        for (int i = 0; i < N_FILOSOFOS; i++) {
            palillosLibres[i] = true;
        }
    }
    //Método sincronizado para que un filósofo intente coger los palillos
    //Un filósofo esperará (wait) HASTA QUE ambos palillos estén libres 
    public synchronized void cogerPalillos(int id) throws InterruptedException {
      
        int palilloIzquierdo = id;
        int palilloDerecho = (id + 1) % N_FILOSOFOS;

        System.out.println("    Filósofo " + id + " intenta coger palillos (" + palilloIzquierdo + " y " + palilloDerecho + ")");
        while (!palillosLibres[palilloIzquierdo] || !palillosLibres[palilloDerecho]) {
            System.out.println("    Filósofo " + id + " ESPERA (Palillos ocupados).");
            wait(); 
        }
        palillosLibres[palilloIzquierdo] = false;
        palillosLibres[palilloDerecho] = false;
        
        System.out.println("Filósofo " + id + " cogió sus palillos.");
    }
    //Método sincronizado para que un filósofo suelte los palillos
    public synchronized void soltarPalillos(int id) {
     
        int palilloIzquierdo = id;
        int palilloDerecho = (id + 1) % N_FILOSOFOS;

        palillosLibres[palilloIzquierdo] = true;
        palillosLibres[palilloDerecho] = true;

        System.out.println("Filósofo " + id + " soltó palillos (" + palilloIzquierdo + " y " + palilloDerecho + ")");   
        notifyAll();
    }
}
//El hilo Filosofo, que alterna entre pensar y comer
class Filosofo extends Thread {

    private final int id;
    private final Mesa mesa; 
    private final Random random = new Random();

    public Filosofo(int id, Mesa mesa) {
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
//Clase principal para ejecutar la simulación
public class Mcf {
    public static void main(String[] args) {
        System.out.println("Iniciando la Cena de los Filósofos (con Monitores)");
        
        Mesa mesa = new Mesa();
        for (int i = 0; i < Mesa.N_FILOSOFOS; i++) {
            new Filosofo(i, mesa).start();
        }
    }
}
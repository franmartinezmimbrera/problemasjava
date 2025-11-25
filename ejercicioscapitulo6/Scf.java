// fichero Scf.java 
import java.util.concurrent.Semaphore;
import java.util.Random;

/**
 * La clase Mesa gestiona los recursos compartidos (palillos y la sala)
 */
class Mesa {

    public static final int N_FILOSOFOS = 5;
    public final Semaphore[] palillos = new Semaphore[N_FILOSOFOS];
    public final Semaphore sala = new Semaphore(N_FILOSOFOS - 1);

    public Mesa() {
        for (int i = 0; i < N_FILOSOFOS; i++) {
            palillos[i] = new Semaphore(1);
        }
    }
}

/**
 * El hilo Filosofo, que alterna entre pensar y comer.
 */
class Filosofo extends Thread {

    private final int id;
    private final Mesa mesa;
    private final Random random = new Random();

    private final int palilloIzquierdo;
    private final int palilloDerecho;

    public Filosofo(int id, Mesa mesa) {
        this.id = id;
        this.mesa = mesa;
        
        this.palilloIzquierdo = id;
        this.palilloDerecho = (id + 1) % Mesa.N_FILOSOFOS;
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

                System.out.println("Filósofo " + id + " tiene HAMBRE y quiere entrar al comedor...");
                mesa.sala.acquire();
                System.out.println("Filósofo " + id + " entró al comedor.");

                mesa.palillos[palilloIzquierdo].acquire();
                System.out.println("Filósofo " + id + " cogió palillo izq (" + palilloIzquierdo + ")");
                
                Thread.sleep(100); 
                
                mesa.palillos[palilloDerecho].acquire();
                System.out.println("Filósofo " + id + " cogió palillo der (" + palilloDerecho + ")");

                comer();                

                System.out.println("Filósofo " + id + " terminó de comer. Suelta palillos.");
                mesa.palillos[palilloDerecho].release();
                mesa.palillos[palilloIzquierdo].release();

                mesa.sala.release();
                System.out.println("Filósofo " + id + " salió del comedor.");
            }
        } catch (InterruptedException e) {
            System.out.println("Filósofo " + id + " fue interrumpido.");
        }
    }
}

public class Scf {

    public static void main(String[] args) {
        System.out.println("--- Iniciando la Cena de los Filósofos ---");
        Mesa mesa = new Mesa();
        
        for (int i = 0; i < Mesa.N_FILOSOFOS; i++) {
            new Filosofo(i, mesa).start();
        }
    }
}

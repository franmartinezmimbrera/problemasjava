// fichero Spc.java 
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
/**
 * Buffer con control de acceso usando semáforos.
 * Los semáforos controlan explícitamente el número de espacios disponibles y elementos presentes en el buffer.*/
class BufferConSemaforos {
    private final Queue<Integer> buffer;
    private final Semaphore espaciosDisponibles; 
    private final Semaphore elementosDisponibles; 
    private final Semaphore mutexBuffer; 
    
    public BufferConSemaforos(int capacidad) {
        this.buffer = new LinkedList<>();
        this.espaciosDisponibles = new Semaphore(capacidad); 
        this.elementosDisponibles = new Semaphore(0); 
        this.mutexBuffer = new Semaphore(1); 
    }
    /**
     * Producir usando semáforos para control de concurrencia.
     */
    public void producir(int item) throws InterruptedException {
        // Esperar por un espacio disponible
        espaciosDisponibles.acquire();
        // Entrar en sección crítica
        mutexBuffer.acquire();
        try {
            buffer.add(item);
            System.out.println(Thread.currentThread().getName() + 
                             " - Producido: " + item + " (Buffer: " + buffer.size() + ")");
        } finally {
            // Salir de sección crítica
            mutexBuffer.release();
        }
        // Señalar que hay un nuevo elemento disponible
        elementosDisponibles.release();
    }
    /**
     * Consumir usando semáforos para control de concurrencia.
     */
    public int consumir() throws InterruptedException {
        // Esperar por un elemento disponible
        elementosDisponibles.acquire();
        int item;
        // Entrar en sección crítica
        mutexBuffer.acquire();
        try {
            item = buffer.poll();
            System.out.println(Thread.currentThread().getName() + 
                             " - Consumido: " + item + " (Buffer: " + buffer.size() + ")");
        } finally {
            // Salir de sección crítica
            mutexBuffer.release();
        }
        // Señalar que hay un nuevo espacio disponible
        espaciosDisponibles.release();
        return item;
    }
}
public class Spc{
    public static void main(String[] args) {
        System.out.println("\n EJEMPLO: USANDO SEMÁFOROS\n");
        final int CAPACIDAD = 5;
        final int ITEMS_TOTALES = 10;
        BufferConSemaforos buffer = new BufferConSemaforos(CAPACIDAD);
        // Productores con semáforos
        Thread productor = new Thread(() -> {
            try {
                for (int i = 1; i <= ITEMS_TOTALES; i++) {
                    Thread.sleep((long)(Math.random() * 800));
                    buffer.producir(i);
                }
                System.out.println(Thread.currentThread().getName() + " - ¡Producción completada!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Productor-Semáforo");
        // Consumidor con semáforos
        Thread consumidor = new Thread(() -> {
            try {
                for (int i = 0; i < ITEMS_TOTALES; i++) {
                    int item = buffer.consumir();
                    Thread.sleep((long)(Math.random() * 1200));
                }
                System.out.println(Thread.currentThread().getName() + " - ¡Consumo completado!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumidor-Semáforo");
        productor.start();
        consumidor.start();        
        try {
            productor.join();
            consumidor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   
        System.out.println("\n¡Ejemplo completado!\n");
    }
}
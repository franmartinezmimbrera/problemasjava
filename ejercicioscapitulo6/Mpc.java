// fichero Mpc.java 
import java.util.LinkedList;
import java.util.Queue;

class BufferCompartido {
    private final Queue<Integer> buffer;
    private final int capacidad;
    
    public BufferCompartido(int capacidad) {
        this.buffer = new LinkedList<>();
        this.capacidad = capacidad;
    }
    /**
     * Método sincronizado para producir un elemento.
     * El productor espera si el buffer está lleno.*/
    public synchronized void producir(int item) throws InterruptedException {
        // Esperar mientras el buffer esté lleno
        // Usamos while (no if) para proteger contra "spurious wakeups"
        while (buffer.size() == capacidad) {
            System.out.println(Thread.currentThread().getName() + 
                             " - Buffer lleno. Productor esperando...");
            wait(); // Libera el lock y espera
        }
        // Agregar el item al buffer
        buffer.add(item);
        System.out.println(Thread.currentThread().getName() + 
                         " - Producido: " + item + " (Buffer: " + buffer.size() + "/" + capacidad + ")");
        
        // Notificar a los consumidores que hay datos disponibles
        notifyAll(); // Despierta a todos los hilos en espera
    }
    /**
     * Método sincronizado para consumir un elemento.
     * El consumidor espera si el buffer está vacío.*/
    public synchronized int consumir() throws InterruptedException {
        // Esperar mientras el buffer esté vacío
        while (buffer.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + 
                             " - Buffer vacío. Consumidor esperando...");
            wait();
        }
        // Extraer el item del buffer
        int item = buffer.poll();
        System.out.println(Thread.currentThread().getName() + 
                         " - Consumido: " + item + " (Buffer: " + buffer.size() + "/" + capacidad + ")");
        // Notificar a los productores que hay espacio disponible
        notifyAll();       
        return item;
    }
}
/**
 * Clase Productor que genera elementos y los coloca en el buffer.
 */
class Productor implements Runnable {
    private final BufferCompartido buffer;
    private final int itemsAPrducir;
    
    public Productor(BufferCompartido buffer, int itemsAProducir) {
        this.buffer = buffer;
        this.itemsAPrducir = itemsAProducir;
    }   
    @Override
    public void run() {
        try {
            for (int i = 1; i <= itemsAPrducir; i++) {
                // Simular tiempo de producción
                Thread.sleep((long)(Math.random() * 1000));
                
                buffer.producir(i);
            }
            System.out.println(Thread.currentThread().getName() + " - ¡Producción completada!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Productor interrumpido");
        }
    }
}
/**
 * Clase Consumidor que extrae elementos del buffer y los procesa.
 */
class Consumidor implements Runnable {
    private final BufferCompartido buffer;
    private final int itemsAConsumir;
    
    public Consumidor(BufferCompartido buffer, int itemsAConsumir) {
        this.buffer = buffer;
        this.itemsAConsumir = itemsAConsumir;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < itemsAConsumir; i++) {
                int item = buffer.consumir();
                // Simular tiempo de procesamiento
                Thread.sleep((long)(Math.random() * 1500));
            }
            System.out.println(Thread.currentThread().getName() + " - ¡Consumo completado!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Consumidor interrumpido");
        }
    }
}
public class Mpc{
    public static void main(String[] args) {

        System.out.println("\nEJEMPLO: MONITOR USANDO WAIT/NOTIFY\n");
        
        final int CAPACIDAD = 5;
        final int ITEMS_TOTALES = 10;
        
        BufferCompartido buffer = new BufferCompartido(CAPACIDAD);
        
        // Crear 2 productores y 2 consumidores
        Thread productor1 = new Thread(new Productor(buffer, ITEMS_TOTALES/2), "Productor-1");
        Thread productor2 = new Thread(new Productor(buffer, ITEMS_TOTALES/2), "Productor-2");
        Thread consumidor1 = new Thread(new Consumidor(buffer, ITEMS_TOTALES/2), "Consumidor-1");
        Thread consumidor2 = new Thread(new Consumidor(buffer, ITEMS_TOTALES/2), "Consumidor-2");
        
        // Iniciar todos los hilos
        productor1.start();
        productor2.start();
        consumidor1.start();
        consumidor2.start();
        
        // Esperar a que terminen
        try {
            productor1.join();
            productor2.join();
            consumidor1.join();
            consumidor2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n¡Ejemplo completado!\n");
    }
}

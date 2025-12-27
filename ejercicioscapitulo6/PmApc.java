// fichero PmApc.java 
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
/**
 * Clase que representa al hilo Productor.
 * Envía "mensajes" (los ítems) a un buzón.*/
class Productor extends Thread {
    private BlockingQueue<Integer> buzon;
    public Productor(BlockingQueue<Integer> buzon) {
        this.buzon = buzon;
    }
    @Override
    public void run() {
        int item = 0;
        try {
            while (true) {
                item++; 
                Thread.sleep((int) (Math.random() * 1000));
                System.out.println("Productor produce y envía: " + item);
                buzon.put(item); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
/**
 * Clase que representa al hilo Consumidor.
 * Recibe "mensajes" (los ítems) del buzón.*/
class Consumidor extends Thread {
    private BlockingQueue<Integer> buzon;
    public Consumidor(BlockingQueue<Integer> buzon) {
        this.buzon = buzon;
    }
    @Override
    public void run() {
        try {
            while (true) {
           
                int item = buzon.take();
                System.out.println("Consumidor recibe y consume: " + item);
                
                Thread.sleep((int) (Math.random() * 1500)); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
public class PmApc {
    public static void main(String[] args) {
        
        final int CAPACIDAD_BUZON = 5;
        BlockingQueue<Integer> buzon = new ArrayBlockingQueue<>(CAPACIDAD_BUZON);

        Productor p1 = new Productor(buzon);
        p1.start();      
        Consumidor c1 = new Consumidor(buzon);
        c1.start();
        Productor p2 = new Productor(buzon);
        p2.start();
        Consumidor c2 = new Consumidor(buzon);
        c2.start();
    }
}
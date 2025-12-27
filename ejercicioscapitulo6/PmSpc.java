// fichero PmSpc.java 
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

//Clase que representa al hilo Productor.Envía "mensajes" (los ítems) a un buzón síncrono.
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
                System.out.println("Productor produce: " + item + " e INTENTA ENVIAR...");
                buzon.put(item); 
                System.out.println("...Productor ENVIÓ " + item + " (El consumidor lo recibió)");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
//Clase que representa al hilo Consumidor. Recibe "mensajes" (los ítems) de un buzón síncrono.
class Consumidor extends Thread {
    
    private BlockingQueue<Integer> buzon;
    public Consumidor(BlockingQueue<Integer> buzon) {
        this.buzon = buzon;
    }
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Consumidor ESPERANDO para recibir...");
                int item = buzon.take();
                System.out.println("Consumidor RECIBIÓ: " + item);
                Thread.sleep((int) (Math.random() * 1500)); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
public class PmSpc {
    public static void main(String[] args) {
        
        BlockingQueue<Integer> buzon = new SynchronousQueue<>();
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
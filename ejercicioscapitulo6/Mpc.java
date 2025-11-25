// fichero Mpc.java 

//Clase que representa el buffer compartido limitado para un Monitor.
class Buffer {

    private int[] buffer;         // El array que simula el búfer
    private int size;             // Tamaño máximo del búfer
    private int in, out;          // Punteros para el búfer circular
    private int count;            // Contador de items en el buffer

    public Buffer(int size) {
        this.size = size;
        this.buffer = new int[size];
        this.in = 0;   
        this.out = 0;
        this.count = 0; 

    }
    //Método para que el Productor inserte un item.
    public synchronized void put(int item) throws InterruptedException {
        
        while (count == size) {
            System.out.println("Productor esperando (Buffer lleno)...");
            wait();
        }
        buffer[in] = item;
        System.out.println("Productor produce: " + item + " en posición " + in);
        in = (in + 1) % size;
        count++; 
        
        notifyAll();
    }
    //Método para que el Consumidor extraiga un item.
    public synchronized int get() throws InterruptedException {
        
        while (count == 0) {
            System.out.println("Consumidor esperando (Buffer vacío)...");
            wait();
        }
        int item = buffer[out];
        System.out.println("Consumidor consume: " + item + " de posición " + out);
        out = (out + 1) % size;
        count--; 
        notifyAll();
        return item;
    }
}
//Clase que representa al hilo Productor.
class Productor extends Thread {
    private Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        int item = 0;
        try {
            while (true) {
                item++; 
                Thread.sleep((int) (Math.random() * 1000));
                buffer.put(item);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
//Clase que representa al hilo Consumidor.
class Consumidor extends Thread {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }
    @Override
    public void run() {
        try {
            while (true) {
                int item = buffer.get();               
                Thread.sleep((int) (Math.random() * 1500)); 
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
public class Mpc {
    public static void main(String[] args) {
        
        final int BUFFER_SIZE = 5;
        Buffer buffer = new Buffer(BUFFER_SIZE);

        Productor p1 = new Productor(buffer);
        p1.start();      
        Consumidor c1 = new Consumidor(buffer);
        c1.start();
        Productor p2 = new Productor(buffer);
        p2.start();
        Consumidor c2 = new Consumidor(buffer);
        c2.start();
    }
}
// fichero Mle.java 

class FicheroSimuladoMonitor {

    private String contenido = "[Contenido inicial del fichero]";
    
    // Variables de estado del monitor
    private int activeReaders = 0;  
    private int waitingWriters = 0; 
    private boolean isWriting = false; 

    /**
     * Lógica de entrada para un Lector.
     * Un lector debe esperar si hay un escritor escribiendo O si hay escritores esperando.
     */
    public synchronized void startLeer(String nombre) throws InterruptedException {
        System.out.println(nombre + " quiere leer...");
        
       
        while (isWriting || waitingWriters > 0) {
            System.out.println(nombre + " espera (Hay escritores esperando o escribiendo).");
            wait(); 
        }
        
        activeReaders++;
    }

    /**
     * Lógica de salida para un Lector.
     */
    public synchronized void endLeer(String nombre) {
        System.out.println(nombre + " terminó de leer.");
        activeReaders--;
        
        if (activeReaders == 0) {
            notifyAll(); 
        }
    }
    /**
     * Lógica de entrada para un Escritor.
     * Un escritor debe esperar si hay lectores leyendo O si hay otro escritor escribiendo.
     */
    public synchronized void startEscribir(String nombre) throws InterruptedException {
        System.out.println(nombre + " quiere escribir...");
        waitingWriters++;
        
        while (isWriting || activeReaders > 0) {
            System.out.println(nombre + " espera (Hay lectores activos o alguien escribiendo).");
            wait(); 
        }
        
        waitingWriters--; 
        isWriting = true;
        System.out.println(nombre + " está ESCRIBIENDO...");
    }

    /**
     * Lógica de salida para un Escritor.
     */
    public synchronized void endEscribir(String nombre) {
        System.out.println(nombre + " terminó de escribir.");
        isWriting = false;
    
        notifyAll();
    }
    
    /**
     * Simula la acción de leer.
     * No necesita ser 'synchronized' porque el protocolo start/end ya lo protege.
     */
    public void doLeer(String nombre) {
        System.out.println(nombre + " está LEYENDO. Contenido: " + contenido + " (Lectores concurrentes: " + activeReaders + ")");
    }

    /**
     * Simula la acción de escribir.
     * Debe ser llamado *mientras* se tiene el bloqueo de escritura.
     * Lo hacemos 'synchronized' por seguridad, aunque el protocolo ya lo protege.
     */
    public synchronized void doEscribir(String nombre) {
        this.contenido = "[Escrito por " + nombre + "]";
    }
}

/**
 * Hilo Lector (Modificado para usar el protocolo start/end del monitor)
 */
class Lector extends Thread {
    private final FicheroSimuladoMonitor fichero;
    private final String nombre;

    public Lector(FicheroSimuladoMonitor f, String n) {
        this.fichero = f;
        this.nombre = n;
    }
    
    @Override
    public void run() {
        try {
            while (true) {

                fichero.startLeer(nombre);
                
                fichero.doLeer(nombre);
                Thread.sleep((long) (Math.random() * 500)); 
                
                fichero.endLeer(nombre);
                
                Thread.sleep((long) (Math.random() * 2000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Hilo Escritor (Modificado para usar el protocolo start/end del monitor)
 */
class Escritor extends Thread {
    private final FicheroSimuladoMonitor fichero;
    private final String nombre;

    public Escritor(FicheroSimuladoMonitor f, String n) {
        this.fichero = f;
        this.nombre = n;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                fichero.startEscribir(nombre);
                
                Thread.sleep((long) (Math.random() * 1500)); 
                fichero.doEscribir(nombre);
                                
                fichero.endEscribir(nombre);

                Thread.sleep((long) (Math.random() * 3000)); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/**
 * Fichero principal que contiene todas las clases para la simulación
 * de Lectores-Escritores con Prioridad para Escritores, usando Monitores.
 */
public class Sle {

    public static void main(String[] args) {

        FicheroSimuladoMonitor fichero = new FicheroSimuladoMonitor();

        System.out.println("Iniciando Simulación (Prioridad Escritores con Monitores)");

        
        new Escritor(fichero, "Escritor A").start();
        new Escritor(fichero, "Escritor B").start();

        new Lector(fichero, "Lector 1").start();
        new Lector(fichero, "Lector 2").start();
        new Lector(fichero, "Lector 3").start();
        new Lector(fichero, "Lector 4").start();
    }
}


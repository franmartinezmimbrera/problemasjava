// fichero Sle.java 
import java.util.concurrent.Semaphore;

/**
 * Clase que simula el recurso (fichero de texto) y gestiona
 * la sincronización con prioridad para escritores.
 */
class FicheroSimulado {

    private String contenido = "[Contenido inicial del fichero]";
    private int readcount = 0;
    private final Semaphore r_gate = new Semaphore(1); 
    private final Semaphore wrt = new Semaphore(1);    
    private final Semaphore mutex_r = new Semaphore(1); 

    /**
     * Lógica de acceso para un Lector.
     */
    public void leer(String nombre) throws InterruptedException {
        
        System.out.println(nombre + " quiere leer...");
        r_gate.acquire();
        r_gate.release();

        mutex_r.acquire();       
        readcount++;
        if (readcount == 1) {
            System.out.println("    " + nombre + " (siendo el 1er lector) bloquea 'wrt'.");
            wrt.acquire(); 
        }
        
        mutex_r.release();

        System.out.println(nombre + " está LEYENDO. Contenido: " + contenido + " (Lectores: " + readcount + ")");
        Thread.sleep((long) (Math.random() * 500));
        System.out.println(nombre + " terminó de leer.");

        mutex_r.acquire();
        
        readcount--;
        
        if (readcount == 0) {
            System.out.println(nombre + " (siendo el último lector) libera 'wrt'.");
            wrt.release();
        }
        
        mutex_r.release();
    }

    /**
     * Lógica de acceso para un Escritor.
     */
    public void escribir(String nombre) throws InterruptedException {
        
        
        System.out.println(nombre + " quiere escribir...");

        System.out.println(nombre + " cierra la puerta 'r_gate'.");
        r_gate.acquire();
        
        wrt.acquire();
        
        
        System.out.println(nombre + " está ESCRIBIENDO...");
        Thread.sleep((long) (Math.random() * 1500)); 
        this.contenido = "[Escrito por " + nombre + "]";
        System.out.println(nombre + " terminó de escribir.");
        
        wrt.release();
        
        System.out.println("    " + nombre + " abre la puerta 'r_gate'.");
        r_gate.release();
    }
}

/**
 * Hilo Lector
 */
class Lector extends Thread {
    private final FicheroSimulado fichero;
    private final String nombre;

    public Lector(FicheroSimulado f, String n) {
        this.fichero = f;
        this.nombre = n;
    }
    @Override
    public void run() {
        try {
            while (true) {
                fichero.leer(nombre);
                Thread.sleep((long) (Math.random() * 2000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Hilo Escritor
 */
class Escritor extends Thread {
    private final FicheroSimulado fichero;
    private final String nombre;

    public Escritor(FicheroSimulado f, String n) {
        this.fichero = f;
        this.nombre = n;
    }
    @Override
    public void run() {
        try {
            while (true) {
                fichero.escribir(nombre);
                Thread.sleep((long) (Math.random() * 3000)); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Sle {

    public static void main(String[] args) {

        FicheroSimulado fichero = new FicheroSimulado();

        System.out.println("Iniciando Simulación (Prioridad Escritores)");

        new Escritor(fichero, "Escritor A").start();
        new Escritor(fichero, "Escritor B").start();
        new Lector(fichero, "Lector 1").start();
        new Lector(fichero, "Lector 2").start();
        new Lector(fichero, "Lector 3").start();
        new Lector(fichero, "Lector 4").start();
    }
}


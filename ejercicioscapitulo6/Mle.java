// fichero Mle.java 
/**
 * Monitor para el problema Lector-Escritor con Prioridad a Escritores.*/
class BaseDeDatosMonitor {
    private int dato = 0;
    
    // Variables de estado del Monitor
    private int lectoresActivos = 0;
    private boolean escribiendo = false;
    private int escritoresEsperando = 0; // Crucial para la prioridad
    /**
     * Protocolo de entrada para lectores.
     * Si hay un escritor escribiendo O escritores esperando, el lector se bloquea.
     */
    private synchronized void iniciarLectura() throws InterruptedException {
        // PRIORIDAD ESCRITOR: Si hay alguien escribiendo O hay escritores en la fila
        while (escribiendo || escritoresEsperando > 0) {
            wait();
        }
        lectoresActivos++;
    }
    /**
     * Protocolo de salida para lectores.
     */
    private synchronized void terminarLectura() {
        lectoresActivos--;
        // Si soy el último lector, aviso a los hilos esperando (posibles escritores)
        if (lectoresActivos == 0) {
            notifyAll();
        }
    }
    /**
     * Protocolo de entrada para escritores.
     */
    private synchronized void iniciarEscritura() throws InterruptedException {
        // Me anoto como esperando para bloquear a nuevos lectores (Prioridad)
        escritoresEsperando++;
        
        try {
            // Mientras haya alguien leyendo o escribiendo, espero
            while (lectoresActivos > 0 || escribiendo) {
                wait();
            }
        } finally {
            // Ya voy a dejar de esperar y pasar a escribir (o si salta excepción)
             escritoresEsperando--;
        }
        
        escribiendo = true;
    }
    /**
     * Protocolo de salida para escritores.
     */
    private synchronized void terminarEscritura() {
        escribiendo = false;
        notifyAll(); // Despierto a todos (lectores o escritores) para que compitan
    }
    public void leer() {
        try {
            iniciarLectura();
            try {
                // SECCIÓN CRÍTICA (Lectura) - Fuera del synchronized para permitir concurrencia
                System.out.println(Thread.currentThread().getName() + 
                                 " - Leído: " + dato + " (Lectores activos: " + lectoresActivos + ")");
                // Simulamos tiempo de lectura
                 Thread.sleep((long) (Math.random()* 1000));
            } finally {
                terminarLectura();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void escribir(int valor) {
        try {
            iniciarEscritura();
            try {
                // SECCIÓN CRÍTICA (Escritura)
                this.dato = valor;
                System.out.println(Thread.currentThread().getName() + 
                                 " - Escribiendo: " + dato + " (Lectores: " + lectoresActivos + ", Escritores esp: " + escritoresEsperando + ")");
                 // Simulamos tiempo de escritura
                Thread.sleep((long) (Math.random() * 1500));
            } finally {
                terminarEscritura();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
public class Mle {
    public static void main(String[] args) {
        System.out.println("\n--- LECTOR-ESCRITOR CON MONITORES (PRIORIDAD ESCRITOR) ---\n");
        
        final int NUM_LECTORES = 5;
        final int NUM_ESCRITORES = 2;
        BaseDeDatosMonitor bd = new BaseDeDatosMonitor();

        // Hilos Lectores
        for (int i = 1; i <= NUM_LECTORES; i++) {
            new Thread(() -> {
                try {
                    while (true) {
                        Thread.sleep((long)(Math.random() * 1000));
                        bd.leer();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Lector-" + i).start();
        }

        // Hilos Escritores
        for (int i = 1; i <= NUM_ESCRITORES; i++) {
            new Thread(() -> {
                try {
                    while (true) {
                        Thread.sleep((long)(Math.random() * 2000));
                        int valorAleatorio = (int)(Math.random() * 100);
                        bd.escribir(valorAleatorio);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Escritor-" + i).start();
        }
    }
}
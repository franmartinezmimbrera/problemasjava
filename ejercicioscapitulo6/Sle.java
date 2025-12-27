import java.util.concurrent.Semaphore;

/**
 * Recurso compartido con control de acceso Lector-Escritor y Prioridad para Escritores.
 * * Lógica de Prioridad Escritor:
 * - Cuando un escritor quiere escribir, bloquea la entrada de nuevos lectores (usando entradaLectores).
 * - Los lectores que ya estaban leyendo terminan normalmente.
 * - Una vez que no quedan lectores ni escritores previos, el escritor accede.*/
class BaseDeDatos {

    private int dato = 0; // El recurso compartido
    
    // Semáforos y Contadores
    private int lectoresActivos = 0;
    private int escritoresEsperando = 0;    
    // Mutex para proteger las variables contadoras
    private final Semaphore mutexLectores = new Semaphore(1);
    private final Semaphore mutexEscritores = new Semaphore(1);    
    // Semáforo para acceso exclusivo al recurso (escritura)
    private final Semaphore recurso = new Semaphore(1);
    // Semáforo puerta para priorizar escritores (bloquea nuevos lectores)
    private final Semaphore entradaLectores = new Semaphore(1); 
    /**
     * Escribir: Prioridad alta. Cierra la puerta a nuevos lectores.
     */
    public void escribir(int nuevoValor) throws InterruptedException {
        // Anunciamos que hay un escritor esperando
        mutexEscritores.acquire();
        escritoresEsperando++;
        if (escritoresEsperando == 1) {
            // Si soy el primer escritor esperando, cierro la puerta a los lectores
            entradaLectores.acquire(); 
        }
        mutexEscritores.release();

        // Espero posesión exclusiva del recurso
        recurso.acquire();  
        try {
            // SECCIÓN CRÍTICA DE ESCRITURA
            this.dato = nuevoValor;
            System.out.println(Thread.currentThread().getName() + 
                             " -Escribiendo: " + dato + " (Lectores: " + lectoresActivos + ", Escritores esp: " + escritoresEsperando + ")");
        } finally {
            // Libero recurso
            recurso.release();
        }

        // Salida del escritor
        mutexEscritores.acquire();
        escritoresEsperando--;
        if (escritoresEsperando == 0) {
            // Si no quedan más escritores esperando, abro la puerta a los lectores
            entradaLectores.release(); 
        }
        mutexEscritores.release();
    }
    /**
     * Leer: Debe pedir permiso (entradaLectores) por si hay escritores.
     */
    public int leer() throws InterruptedException {
        // Pasar por la puerta (si hay escritores esperando, aquí se bloquea)
        entradaLectores.acquire();
        entradaLectores.release(); // Liberar inmediatamente para que pasen otros lectores (si no hay escritores)

        // Protocolo de entrada de lectores
        mutexLectores.acquire();
        lectoresActivos++;
        if (lectoresActivos == 1) {
            // El primer lector bloquea el recurso para que nadie escriba mientras leemos
            recurso.acquire(); 
        }
        mutexLectores.release();
        int lectura = -1;        
        try {
            // SECCIÓN CRÍTICA DE LECTURA (Técnicamente concurrente con otros lectores)
            lectura = this.dato;
            System.out.println(Thread.currentThread().getName() + 
                             " - Leído: " + lectura + " (Lectores activos: " + lectoresActivos + ")");
        } finally {
            // Protocolo de salida de lectores
            mutexLectores.acquire();
            lectoresActivos--;
            if (lectoresActivos == 0) {
                // El último lector libera el recurso para los escritores
                recurso.release();
            }
            mutexLectores.release();
        }
        return lectura;
    }
}
public class Sle {
    public static void main(String[] args) {
        System.out.println("\nPROBLEMA LECTOR-ESCRITOR (PRIORIDAD ESCRITOR)\n");
        
        final int NUM_LECTORES = 5;
        final int NUM_ESCRITORES = 2;
        BaseDeDatos bd = new BaseDeDatos();
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
                        Thread.sleep((long)(Math.random() * 2000)); // Escriben con menos frecuencia
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
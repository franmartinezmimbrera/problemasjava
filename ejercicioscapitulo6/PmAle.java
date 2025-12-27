// fichero PmAle.java 
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Queue;
import java.util.LinkedList;
//Este es el recurso compartido. La seguridad la garantiza el Controlador, que solo da permiso
// a los hilos para acceder en el momento correcto.
class FicheroSimulado {

    private String contenido = "[Contenido inicial del fichero]";

    public void leer(String nombre) {
        System.out.println(nombre + " está LEYENDO: " + this.contenido);
        try {
            Thread.sleep((long) (Math.random() * 500));
        } catch (InterruptedException e) {}
    }

    public void escribir(String nombre) {
        System.out.println(nombre + " está ESCRIBIENDO...");
        try {
            Thread.sleep((long) (Math.random() * 1500));
        } catch (InterruptedException e) {}
        this.contenido = "[Escrito por " + nombre + "]";
        System.out.println("... " + nombre + " terminó de escribir.");
    }
}
// Interfaz base para todos los mensajes 
interface Mensaje {}
// Mensajes de Lector
class PeticionAbrirLectura implements Mensaje {

    public final String nombre;
    public final BlockingQueue<Boolean> canalRespuesta;
    public PeticionAbrirLectura(String n, BlockingQueue<Boolean> c) { nombre = n; canalRespuesta = c; }
}
class PeticionCerrarLectura implements Mensaje {

    public final String nombre;
    public PeticionCerrarLectura(String n) { nombre = n; }
}
// Mensajes de Escritor
class PeticionAbrirEscritura implements Mensaje {

    public final String nombre;
    public final BlockingQueue<Boolean> canalRespuesta;
    public PeticionAbrirEscritura(String n, BlockingQueue<Boolean> c) { nombre = n; canalRespuesta = c; }
}
class PeticionCerrarEscritura implements Mensaje {

    public final String nombre;
    public PeticionCerrarEscritura(String n) { nombre = n; }
}
//Este es el "proceso Controlador". Gestiona el estado (nl) y las colas de peticiones.
class Controlador extends Thread {

    private final BlockingQueue<Mensaje> buzonPeticiones = new LinkedBlockingQueue<>();
    private int lectoresActivos = 0; 
    private boolean escritorActivo = false;
    private final Queue<PeticionAbrirLectura> lectoresEnEspera = new LinkedList<>();
    private final Queue<PeticionAbrirEscritura> escritoresEnEspera = new LinkedList<>();
    public Controlador() {
        this.start();
    }
    public void enviarPeticion(Mensaje msg) throws InterruptedException {
        buzonPeticiones.put(msg);
    }
    @Override
    public void run() {
        System.out.println("Controlador de bloqueos iniciado.");
        try {
            while (true) {
                Mensaje msg = buzonPeticiones.take();         
                if (msg instanceof PeticionAbrirLectura) {
                    procesarAbrirLectura((PeticionAbrirLectura) msg);
                } 
                else if (msg instanceof PeticionCerrarLectura) {
                    procesarCerrarLectura((PeticionCerrarLectura) msg);
                }
                else if (msg instanceof PeticionAbrirEscritura) {
                    procesarAbrirEscritura((PeticionAbrirEscritura) msg);
                }
                else if (msg instanceof PeticionCerrarEscritura) {
                    procesarCerrarEscritura((PeticionCerrarEscritura) msg);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Controlador interrumpido.");
        }
    }
    private void procesarAbrirLectura(PeticionAbrirLectura p) throws InterruptedException {

        if (escritoresEnEspera.size() > 0 || escritorActivo) {
            System.out.println("    Controlador: " + p.nombre + " en espera (Prioridad Escritor).");
            lectoresEnEspera.add(p);
        } else {
            lectoresActivos++; 
            p.canalRespuesta.put(true); 
        }
    }
    private void procesarCerrarLectura(PeticionCerrarLectura p) throws InterruptedException {

        lectoresActivos--; // nl := nl - 1
        if (lectoresActivos == 0) {
            intentarDespertarEscritor();
        }
    }
    private void procesarAbrirEscritura(PeticionAbrirEscritura p) throws InterruptedException {

        if (lectoresActivos > 0 || escritorActivo) {
            System.out.println("    Controlador: " + p.nombre + " en espera (Recurso ocupado).");
            escritoresEnEspera.add(p);
        } else {
            escritorActivo = true;
            p.canalRespuesta.put(true); 
        }
    }
    private void procesarCerrarEscritura(PeticionCerrarEscritura p) throws InterruptedException {

        escritorActivo = false;
        if (!intentarDespertarEscritor()) {
            intentarDespertarLectores();
        }
    }
    private boolean intentarDespertarEscritor() throws InterruptedException {

        if (escritoresEnEspera.size() > 0 && lectoresActivos == 0 && !escritorActivo) {
            PeticionAbrirEscritura p = escritoresEnEspera.remove();
            System.out.println("Controlador: Despertando a escritor " + p.nombre);
            escritorActivo = true;
            p.canalRespuesta.put(true);
            return true;
        }
        return false;
    }
    private void intentarDespertarLectores() throws InterruptedException {

        if (escritoresEnEspera.size() == 0 && !escritorActivo) {
            while (!lectoresEnEspera.isEmpty()) {
                PeticionAbrirLectura p = lectoresEnEspera.remove();
                System.out.println("Controlador: Despertando a lector " + p.nombre);
                lectoresActivos++;
                p.canalRespuesta.put(true);
            }
        }
    }
}
//Hilo Lector (Cliente)
class Lector extends Thread {

    private final FicheroSimulado fichero; 
    private final Controlador controlador; 
    private final String nombre;
    private final BlockingQueue<Boolean> miBuzon = new ArrayBlockingQueue<>(1);

    public Lector(FicheroSimulado f, Controlador c, String n) {

        this.fichero = f;
        this.controlador = c;
        this.nombre = n;
    }
    @Override
    public void run() {
        try {
            while (true) {

                System.out.println(nombre + " pide permiso para leer...");
                controlador.enviarPeticion(new PeticionAbrirLectura(nombre, miBuzon));
                miBuzon.take();
                System.out.println(nombre + " obtuvo permiso.");
                fichero.leer(nombre);
                controlador.enviarPeticion(new PeticionCerrarLectura(nombre));
                Thread.sleep((long) (Math.random() * 2000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}   
//Hilo Escritor (Cliente)
class Escritor extends Thread {
    
    private final FicheroSimulado fichero; 
    private final Controlador controlador; 
    private final String nombre;
    private final BlockingQueue<Boolean> miBuzon = new ArrayBlockingQueue<>(1);

    public Escritor(FicheroSimulado f, Controlador c, String n) {
        this.fichero = f;
        this.controlador = c;
        this.nombre = n;
    }
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(nombre + " pide permiso para escribir...");
                controlador.enviarPeticion(new PeticionAbrirEscritura(nombre, miBuzon));
                miBuzon.take();
                System.out.println(nombre + " obtuvo permiso.");
                fichero.escribir(nombre);
                controlador.enviarPeticion(new PeticionCerrarEscritura(nombre));
                Thread.sleep((long) (Math.random() * 3000)); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class PmAle {
    public static void main(String[] args) {
        FicheroSimulado fichero = new FicheroSimulado();
        Controlador controlador = new Controlador();
        System.out.println("Iniciando Simulación (Paso de Mensajes - Gestor de Bloqueos)");
        new Escritor(fichero, controlador, "Escritor A").start();
        new Escritor(fichero, controlador, "Escritor B").start();
        new Lector(fichero, controlador, "Lector 1").start();
        new Lector(fichero, controlador, "Lector 2").start();
        new Lector(fichero, controlador, "Lector 3").start();
        new Lector(fichero, controlador, "Lector 4").start();
    }
}
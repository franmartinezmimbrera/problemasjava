// fichero Saludo.java 
// Este programa hace un saludo personalizado
import java.io.*;

public class Saludo {
    public static void main(String[] args) {
        try {
            InputStreamReader leer = new InputStreamReader(System.in);
            BufferedReader buff = new BufferedReader(leer);

            System.out.println("¡Hola! ¿Cómo te llamas?");
            String nombre = buff.readLine();

            System.out.println("Qué tal estás, " + nombre + "?");
        } catch (IOException ioex) {
            System.err.println("Error al leer la entrada: " + ioex.getMessage());
        }
    }
}

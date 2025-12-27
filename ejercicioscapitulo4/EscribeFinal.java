// fichero EscribeFinal.java
// Ejercicio para escribir al final de un fichero de texto una nueva línea.
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EscribeFinal {

    public static void main(String[] args) {

        String nombreArchivo = "datos.txt";

        try (PrintWriter archivo = new PrintWriter(new FileWriter(nombreArchivo, true))) {

            archivo.println("Esta es la línea AÑADIDA al final (Append).");
            System.out.println("\nSe ha añadido una línea al final de '" + nombreArchivo + "'.");

        } catch (IOException e) {
            System.err.println("Error al abrir el archivo " + nombreArchivo + " en modo append: " + e.getMessage());
            System.exit(1); 
        }

    }
}

// fichero LeerFiche.java
//Ejemplo para leer ficheros de texto en Java.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeerFiche {

    public static void main(String[] args) {

        String linea; 
        try (BufferedReader archivo = new BufferedReader(new FileReader("datos.txt"))) {

            System.out.println("\nContenido de 'datos.txt':");

            while ((linea = archivo.readLine()) != null) {
                System.out.println(linea); 
            }

        } catch (IOException e) {
        
            System.err.println("Error al abrir el archivo datos.txt: " + e.getMessage());
            System.exit(1);
        }
        
    }
}
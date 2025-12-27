// fichero CreaFiche.java 
//Ejemplo para crear/escribir ficheros en Java

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreaFiche {
    public static void main(String[] args) {
        
        try (PrintWriter archivo = new PrintWriter(new FileWriter("datos.txt"))) {
            
            archivo.println("Esta es la primera línea.");
            
            archivo.printf("El número PI es aproximadamente %.4f%s", 3.14159, System.lineSeparator());
            
            archivo.println("Tercera línea de ejemplo.");

        } catch (IOException e) {
            System.err.println("Error al abrir/crear el archivo datos.txt: " + e.getMessage());
             System.exit(1); 
        }
    }
}
// fichero ContarCar.java
//Ejemplo para leer un fichero carácter a carácter y contar caracteres y palabras.
import java.io.FileReader;
import java.io.IOException;
public class ContarCaracteres {
    public static void main(String[] args) {
        int caracter; 
        int contador_caracteres = 0,contador_palabras = 0;
        boolean dentro_de_palabra = false; 
        try (FileReader archivo = new FileReader("datos.txt")) {
            while ((caracter = archivo.read()) != -1) {
                contador_caracteres++;
                if (Character.isWhitespace((char) caracter)) {
                    dentro_de_palabra = false;
                } else {
                    if (!dentro_de_palabra) { 
                        contador_palabras++;
                        dentro_de_palabra = true;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al abrir el archivo datos.txt: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("Total de caracteres leídos: " + contador_caracteres);
        System.out.println("Total de palabras: " + contador_palabras);
    }
}
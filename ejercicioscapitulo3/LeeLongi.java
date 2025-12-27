// fichero LeeLongi.java
// Lee una cadena, muestra su longitud y la concatena con ".txt"
import java.util.Scanner;

public class LeeLongi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cadena;
        System.out.println("Introduzca cadena:");
        while (true) {
            cadena = sc.nextLine();
            cadena = cadena.stripTrailing(); // elimina saltos de línea y espacios al final
            int len = cadena.length();
            if (len == 0) {
                break;
            }
            System.out.printf("La cadena contiene %d caracteres.%n", len);
            String cadenaResultado = cadena + ".txt";
            System.out.printf("Concatenación: %s%n", cadenaResultado);
            System.out.println("Introduzca cadena (o solo Enter para terminar):");
        }
        sc.close();
    }
}

// fichero Conteo.java
// Lee varias frases, las convierte a mayúsculas y cuenta las vocales.
import java.util.Scanner;
public class Conteo {

    private static final int NUM_FRASES = 4;
    private static final int MAX_LONGITUD = 200;

    public static String convertirAMayusculas(String s) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            resultado.append(Character.toUpperCase(c));
        }
        return resultado.toString();
    }
    // Cuenta las vocales (A, E, I, O, U) en una cadena ya en mayúsculas
    public static int contarVocales(String s) {
        int contador = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    contador++;
                    break;
                default:
                    break;
            }
        }
        return contador;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] listaFrases = new String[NUM_FRASES];
        int totalVocales = 0;
        System.out.printf("Introduce %d frases/líneas de texto:%n", NUM_FRASES);
        for (int i = 0; i < NUM_FRASES; i++) {
            System.out.printf("Frase %d: ", i + 1);
            String linea = sc.nextLine();
            if (linea.length() > MAX_LONGITUD) {
                System.out.printf("Advertencia: se truncará a %d caracteres.%n", MAX_LONGITUD);
                linea = linea.substring(0, MAX_LONGITUD);
            }
            listaFrases[i] = linea;
        }
        for (int i = 0; i < NUM_FRASES; i++) {
            String frase = listaFrases[i];
            String mayus = convertirAMayusculas(frase);
            int vocales = contarVocales(mayus);
            totalVocales += vocales;
            System.out.printf("Frase %d (MAYÚS): '%s' -> Vocales contadas: %d%n",
                    i + 1, mayus, vocales);
        }
        System.out.printf("El número total de vocales en todas las frases es: %d%n", totalVocales);
        sc.close();
    }
}

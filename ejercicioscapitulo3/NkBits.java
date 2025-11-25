// fichero NkBits.java
// Muestra los últimos k bits de un número entero 
import java.util.Scanner;
import java.util.InputMismatchException;

public class NkBits{
    public static void mostrarUltimosKBits(long numero, int k) {
        final int TAM_LONG = Long.SIZE; 
        if (k <= 0) {
            System.out.println("Error: k debe ser mayor que 0.");
            return;
        }
        if (k > TAM_LONG) {
            System.out.printf("Advertencia: k fue limitado al tamaño del tipo long (%d bits).%n", TAM_LONG);
            k = TAM_LONG; 
        }
        System.out.printf("Los últimos %d bits de %d son: ", k, numero);
        for (int i = k - 1; i >= 0; i--) {            
            long bit = (numero >>> i) & 1; 
            System.out.print(bit);
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Introduce un número entero largo (long): ");
            long num = sc.nextLong();
            System.out.print("Introduce cuántos bits mostrar (k): ");
            int k = sc.nextInt();
            mostrarUltimosKBits(num, k);
        } catch (InputMismatchException e) {
            System.err.println("Error: Entrada no válida. Debe introducir valores numéricos válidos.");
        } catch (Exception e) {
             System.err.println("Error inesperado: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
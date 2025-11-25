// fichero Mascara.java
// Invierte (cambia 0?1 y 1?0) los últimos k bits de un número entero 
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mascara {
    public static long invertirUltimosKBits(long numero, int k) {
        final int TAM_LONG = Long.SIZE; // 64 bits
        if (k <= 0) {
            System.out.println("Error: k debe ser mayor que 0.");
            return numero;
        }
        if (k >= TAM_LONG) {
            return ~numero; 
        }
        long mascara = (1L << k) - 1;
        return numero ^ mascara;
    }
    public static void mostrarUltimosKBits(long numero, int k) {
        final int TAM_LONG = Long.SIZE;
        if (k <= 0) return;
        if (k > TAM_LONG) k = TAM_LONG;
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
            System.out.print("Introduce cuántos bits invertir (k): ");
            int k = sc.nextInt();
            long resultado = invertirUltimosKBits(num, k);
            System.out.printf("Número resultante: %d%n", resultado);
            System.out.print("Bits invertidos  (últimos " + k + "): ");
            mostrarUltimosKBits(resultado, k);

        } catch (InputMismatchException e) {
            System.err.println("Error: Entrada no válida. Debe introducir valores numéricos válidos.");
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

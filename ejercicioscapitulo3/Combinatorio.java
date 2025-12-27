// fichero Combinatorio.java
// Calcula el número combinatorio C(n, r) o "n sobre r" usando factorial iterativo
import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Combinatorio {

    // Función factorial iterativa
    public static BigInteger factorial(BigInteger n) {
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
            return BigInteger.ONE;
        BigInteger resultado = BigInteger.ONE;
        for (BigInteger i = BigInteger.TWO;
             i.compareTo(n) <= 0;
             i = i.add(BigInteger.ONE)) {
            resultado = resultado.multiply(i);
        }
        return resultado;
    }

    // Cálculo de C(n, r) = n! / (r! * (n - r)!)
    public static BigInteger combinatorio(int n, int r) {
        if (r < 0 || r > n) return BigInteger.ZERO;
        BigInteger bigN = BigInteger.valueOf(n);
        BigInteger bigR = BigInteger.valueOf(r);
        BigInteger factN = factorial(bigN);
        BigInteger factR = factorial(bigR);
        BigInteger factNR = factorial(bigN.subtract(bigR));
        return factN.divide(factR.multiply(factNR));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0, r = 0;
        try {
            System.out.print("Introduce un número entero no negativo para n: ");
            n = scanner.nextInt();
            if (n < 0) {
                System.err.println("Error: n debe ser no negativo.");
                return;
            }
            System.out.print("Introduce un número entero no negativo para r: ");
            r = scanner.nextInt();
            if (r < 0) {
                System.err.println("Error: r debe ser no negativo.");
                return;
            }
        } catch (InputMismatchException e) {
            System.err.println("Error: Entrada no válida. Debe introducir un número entero.");
            return;
        } finally {
            scanner.close();
        }
        if (n > 1000) {
            System.out.println("Advertencia: calcular factoriales grandes puede tardar un poco...");
        }
        BigInteger resultado = combinatorio(n, r);
        System.out.printf("%nC(%d, %d) = %s%n", n, r, resultado.toString());
    }
}

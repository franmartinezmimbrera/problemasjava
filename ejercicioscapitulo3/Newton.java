// fichero Newtong.java
// Calcula la expansión del binomio de Newton (a + b)^n
import java.util.Scanner;
import java.math.BigInteger;

public class Newton {
    // Factorial iterativo
    public static BigInteger factorial(int n) {
        if (n == 0 || n == 1)
            return BigInteger.ONE;

        BigInteger resultado = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        return resultado;
    }

    // Cálculo de C(n, r) = n! / (r! * (n - r)!)
    public static BigInteger nCr(int n, int r) {
        if (r < 0 || r > n)
            return BigInteger.ZERO;

        BigInteger factN = factorial(n);
        BigInteger factR = factorial(r);
        BigInteger factNR = factorial(n - r);

        return factN.divide(factR.multiply(factNR));
    }

    // Expansión del binomio de Newton: (a + b)^n
    public static void binomioDeNewton(BigInteger a, BigInteger b, int n) {
        if (n < 0) {
            System.out.println("Error: El exponente N debe ser no negativo.");
            return;
        }
        System.out.printf("Expansión de (%s + %s)^%d:%n", a.toString(), b.toString(), n);
        BigInteger sumaTotal = BigInteger.ZERO;
        for (int k = 0; k <= n; k++) {
            BigInteger coef = nCr(n, k);
            BigInteger potA = a.pow(n - k);
            BigInteger potB = b.pow(k);
            BigInteger termino = coef.multiply(potA).multiply(potB);
            sumaTotal = sumaTotal.add(termino);

            System.out.printf("  + (%s * %s^%d * %s^%d) = %s%n",
                    coef.toString(), a.toString(), n - k, b.toString(), k, termino.toString());
        }

        System.out.printf("Valor Total (por suma de términos) = %s%n", sumaTotal.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Introduce un número entero para a: ");
            BigInteger a = sc.nextBigInteger();

            System.out.print("Introduce un número entero para b: ");
            BigInteger b = sc.nextBigInteger();

            System.out.print("Introduce un número entero no negativo para n: ");
            int n = sc.nextInt();

            if (n < 0) {
                System.out.println("Error: n debe ser no negativo.");
                return;
            }
            if (n > 1000) {
                System.out.println("Advertencia: El cálculo puede tardar mucho tiempo para n > 1000.");
            }

            binomioDeNewton(a, b, n);

        } catch (Exception e) {
            System.out.println("Error: Entrada no válida.");
        } finally {
            sc.close();
        }
    }
}

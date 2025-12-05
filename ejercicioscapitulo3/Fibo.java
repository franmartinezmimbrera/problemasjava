// fichero Fibo.java
// Calcula el n-enesimo número de Fibonacci.
import java.util.Scanner;
import java.math.BigInteger;

public class Fibo {

    // Función iterativa para calcular el n-ésimo número de Fibonacci.
    public static BigInteger fibonacciIterativo(int n) {      
     
        if (n < 0) {
            throw new IllegalArgumentException("El índice N debe ser no negativo.");
        }
     
        if (n == 0) {
            return BigInteger.ZERO;
        }
     
        if (n == 1) {
            return BigInteger.ONE;
        }
     
        BigInteger a = BigInteger.ZERO; 
        BigInteger b = BigInteger.ONE;  
        BigInteger resultado = BigInteger.ZERO; 
      
        for (int i = 2; i <= n; i++) {
            resultado = a.add(b); 
            a = b;             
            b = resultado;     
        }
        return resultado;
    }
    public static void main(String[] args) {   
     
        Scanner scanner = new Scanner(System.in);
        int n;

        System.out.print("Introduce el índice N: ");
      
        try {
            n = scanner.nextInt();
        } catch (Exception e) {
            System.err.println("Error: Entrada no válida. Debe introducir un número entero.");
            scanner.close();
            return;
        }
        scanner.close();

        if (n < 0) {
            System.err.println("Error: El índice N debe ser no negativo.");
            return;
        }
        
        if (n > 10000) {
             System.out.printf("Advertencia: El cálculo para N=%d puede tardar considerablemente.%n", n);
        }
        BigInteger resultado = fibonacciIterativo(n);      
        System.out.printf("%nEl %d-ésimo número de Fibonacci (iterativo BigInteger) es:%n%s%n", 
                          n, resultado.toString());
    }
}
//fichero FactorialR.java
import java.util.InputMismatchException;
import java.util.Scanner;
import java.math.BigInteger;

public class FactorialR {

    // Función recursiva que calcula el factorial recursivo
    public static BigInteger factorialBigRecursivo(int n) {
        if (n == 0 || n == 1) {return BigInteger.ONE;}
        return BigInteger.valueOf(n).multiply(factorialBigRecursivo(n - 1));
    }
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int numero;
        System.out.print("Introduce un número entero no negativo: ");
        try {
            numero = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Error: Entrada no válida. Debe introducir un número entero.");
            scanner.close();
            return;
        } finally {
            scanner.close(); 
        }
        if (numero < 0) {
            System.err.println("Error: El factorial solo está definido para números no negativos.");
            return;
        }
        if (numero > 1500) { 
            System.out.println("Advertencia: Para números muy grandes, la recursividad podría fallar por desbordamiento de pila.");
        }
        try {
            BigInteger resultado = factorialBigRecursivo(numero);
            System.out.printf("%nEl factorial de %d es:%n%n%s%n", numero, resultado.toString());
            
        } catch (StackOverflowError e) {
            System.err.println("FATAL ERROR: Desbordamiento de la pila (StackOverflowError).");
            System.err.printf("El número %d es demasiado grande para calcularse de forma recursiva.%n", numero);
            System.err.println("Intente usar un número menor o la implementación iterativa.");
        }
    }
}
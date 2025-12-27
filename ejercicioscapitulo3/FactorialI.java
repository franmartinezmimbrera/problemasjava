//fichero FactorialI.java
import java.util.InputMismatchException;
import java.util.Scanner;
import java.math.BigInteger;

public class FactorialI {

    // Función que calcula el factorial 
    public static BigInteger factorialBigIterativo(int n) {
        // Caso base: 0! y 1! es 1
        if (n == 0 || n == 1) {
            return BigInteger.ONE; 
        }
        BigInteger resultado = BigInteger.ONE; 
        for (int i = 2; i <= n; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        return resultado;
    }
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        System.out.print("Introduce un número entero no negativo: ");
        try {
            numero = scanner.nextInt();          
        } catch (InputMismatchException e) {
            System.err.println("Error: Entrada no válida. Debe introducir un número entero.");
            scanner.close();
            return;
        }
        scanner.close();       
        if (numero < 0) {
            System.err.println("Error: El factorial solo está definido para números no negativos.");
            return;
        }  
        if (numero > 1500) {
            System.out.println("?? Advertencia: El cálculo de factoriales de números muy grandes puede tomar un tiempo considerable.");
        }
        BigInteger resultado = factorialBigIterativo(numero);
        System.out.printf("El factorial de %d es:%n%s%n", numero, resultado.toString());        
    }
}
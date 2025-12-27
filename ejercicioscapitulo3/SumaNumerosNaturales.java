// fichero SumaNumerosNaturales.java
// Este programa calcula la suma de los "n" primeros números naturales
import java.util.Scanner;
import java.util.InputMismatchException;

public class SumaNumerosNaturales {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Introduzca número de números naturales a sumar: ");
            long numero = sc.nextLong();
            if (numero <= 0) {
                System.out.println("Error: debe introducir un número positivo.");
            } else {
                long suma = 0;
                for (long i = 1; i <= numero; i++) {
                    suma += i;
                }
                System.out.println("La suma de los " + numero + " primeros números naturales es: " + suma);
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: debe introducir un número entero válido.");
        } finally {sc.close();}
    }
}


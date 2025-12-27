// fichero SumaNNumerosnaturales.java
// Este programa calcula la suma de los "n" primeros números naturales 
import java.util.InputMismatchException;
import java.util.Scanner;
public class SumaNNumerosNaturales {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = 0; boolean entradaValida = false;
        try {
       System.out.print("Introduzca número de términos (n) a sumar: ");
            n = sc.nextLong(); 
            entradaValida = true;
        } catch (InputMismatchException e) {
            System.err.println("Error: Debe ser un número entero >0");
        }
        if (entradaValida) {   
            if (n <= 0) {
                System.err.println("Error: El número de términos (n) debe ser mayor que cero.");
            } else {
                long suma = n * (n + 1) / 2;
                System.out.println("La suma de los " + n + " primeros números naturales es: " + suma);
            }
        }
        sc.close();
    }
}
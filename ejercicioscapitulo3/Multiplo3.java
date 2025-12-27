// fichero Multiplo3.java
// Este programa dice si un número es múltiplo de 3 y par
import java.util.Scanner;

public class Multiplo3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Introduzca un número: ");
            long numero = sc.nextLong(); 
            if (numero % 3 == 0 && numero % 2 == 0) {
                System.out.println("El número " + numero + " es múltiplo de 3 y también es par (Múltiplo de 6).");
            } else if (numero % 3 == 0) {
                System.out.println("El número " + numero + " es múltiplo de 3.");
            } else {
                System.out.println("El número " + numero + " NO es múltiplo de 3.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: debe introducir un número entero.");
        } finally {
            sc.close(); 
        }
    }
}

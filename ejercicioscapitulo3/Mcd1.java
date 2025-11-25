// fichero Mcd1.java
// Este programa calcula el MCD dados 2 números mediante una función

import java.util.Scanner;
import java.util.InputMismatchException;



public class Mcd1 {

    public static long MCD(long a, long b) {
        long temporal;
        a = Math.abs(a);
        b = Math.abs(b);

        while (a != 0) {
            temporal = a;
            a = b % a;
            b = temporal;
        }
        return b;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            
            System.out.print("Introduzca valor de a: ");
            long a = sc.nextLong();          
            System.out.print("Introduzca valor de b: ");
            long b = sc.nextLong();
            long resultado = MCD(a, b);
            System.out.println("\nEL M.C.D de " + a + " y " + b + " es: " + resultado);

        } catch (InputMismatchException e) {
            System.out.println("Error: Debe introducir números enteros válidos.");
        } finally {
            sc.close();
        }
    }
}
// fichero Mcd.java
// Este programa calcula el MCD dados 2 números
import java.util.Scanner;

public class Mcd {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Introduzca el valor de a: ");
            long a = sc.nextLong();

            System.out.print("Introduzca el valor de b: ");
            long b = sc.nextLong();

            long a2 = a; 
            long b2 = b;

            //Algoritmo de Euclides
            while (a != 0) {
                long temporal = a;
                a = b % a;
                b = temporal;
            }

            System.out.println("El M.C.D. de " + a2 + " y " + b2 + " es: " + b);

        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: debe introducir números enteros válidos.");
        } finally {
            sc.close();
        }
    }
}
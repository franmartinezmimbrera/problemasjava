// fichero AreaCircunferencia.java
// Este programa calcula el área de una circunferencia dado un radio válido

import java.util.Scanner;
import java.util.InputMismatchException;

public class AreaCircunferencia {

    public static double AreaCircunferencia_(double r) {
        return Math.PI * Math.pow(r, 2);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {
            double radio;

            System.out.print("Introduce el radio de la circunferencia: ");
            radio = sc.nextDouble();

            while (radio <= 0) {
                System.out.println("ERROR: El radio debe ser mayor que cero.\n");
                System.out.print("Introduzca radio: ");
                radio = sc.nextDouble();
            }
            System.out.printf("El área de la circunferencia es: %.2f\n", AreaCircunferencia_(radio));
        } catch (InputMismatchException e) {
            System.out.println("Error: debes introducir un valor numérico válido para el radio.");
        } finally {
            sc.close();
        }
    }
}

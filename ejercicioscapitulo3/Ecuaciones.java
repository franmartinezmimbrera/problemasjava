// fichero Ecuaciones.java
// Este programa calcula ecuaciones de segundo grados con railes reales
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ecuaciones {
    // Procedimiento que calcula y muestra las raíces reales de la ecuación
    static void resolverEcuacion(double a, double b, double c) {
        double d = b * b - 4 * a * c; 
        System.out.printf("Discriminante (d) = %.4f%n", d);
        if (d > 0) {
            double x1 = (-b + Math.sqrt(d)) / (2 * a);
            double x2 = (-b - Math.sqrt(d)) / (2 * a);
            System.out.println("La ecuación tiene dos soluciones reales distintas:");
            System.out.printf("x1 = %.4f%n", x1);
            System.out.printf("x2 = %.4f%n", x2);
        } else if (Math.abs(d) < 1e-10) {
            double x = -b / (2 * a);
            System.out.println("La ecuación tiene una única solución real (raíz doble):");
            System.out.printf("x = %.4f%n", x);
        } else {
            System.out.println("La ecuación no tiene soluciones reales (el discriminante es negativo).");
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = 0, b = 0, c = 0;
        while (true) {
            try {
                System.out.print("Ingrese coeficiente a: ");
                a = scanner.nextDouble();
                if (Math.abs(a) < 1e-10) {
                    System.out.println("Error:El coeficiente 'a' no puede ser cero (no sería una ecuación cuadrática).");
                } else {
                    break; 
                }
            } catch (InputMismatchException e) {
                System.err.println("Error: debe introducir un número válido.");
                scanner.next(); 
            }
        }
        try {
            System.out.print("Ingrese coeficiente b: ");
            b = scanner.nextDouble();

            System.out.print("Ingrese coeficiente c: ");
            c = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.err.println("Error: la entrada no es válida para b o c.");
            scanner.close();
            return;
        }
        resolverEcuacion(a, b, c);
        scanner.close();
    }
}

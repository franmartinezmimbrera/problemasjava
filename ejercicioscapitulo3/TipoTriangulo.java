// fichero TipoTriangulo.java
// Este programa calcula el tipo de triángulo en función de los lados

import java.util.Scanner;
import java.util.InputMismatchException;

public class TipoTriangulo {

    // Tolerancia para comparar doubles
    private static final double EPSILON = 1e-9;
    public static boolean esTrianguloValido(double a, double b, double c) {
        return (a > 0 && b > 0 && c > 0) &&
               (a + b > c) && 
               (a + c > b) && 
               (b + c > a);
    }
    // Función para comparar doubles con tolerancia
    public static boolean iguales(double x, double y) {
        return Math.abs(x - y) < EPSILON;
    }
    public static double leerLado(Scanner sc, String mensaje) {
        double lado = -1;
        while (true) {
            try {
                System.out.print(mensaje);
                lado = sc.nextDouble();
                if (lado > 0) {
                    break;
                } else {
                    System.out.println("Error: el valor debe ser mayor que 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: debes introducir un número válido.");
                sc.next(); 
            }
        }
        return lado;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double l1, l2, l3;

        l1 = leerLado(sc, "Introduce la longitud del primer lado (l1): ");
        l2 = leerLado(sc, "Introduce la longitud del segundo lado (l2): ");
        l3 = leerLado(sc, "Introduce la longitud del tercer lado (l3): ");
        if (!esTrianguloValido(l1, l2, l3)) {
            System.out.println("\nError: los lados introducidos NO forman un triángulo válido.");            
            return;
        }
        if (iguales(l1, l2) && iguales(l2, l3)) {
            System.out.println("El Triángulo es: EQUILÁTERO (Tres lados iguales)");
        } else if (iguales(l1, l2) || iguales(l1, l3) || iguales(l2, l3)) {
            System.out.println("El Triángulo es: ISÓSCELES (Dos lados iguales)");
        } else {
            System.out.println("El Triángulo es: ESCALENO (Todos los lados diferentes)");
        }
        sc.close();
    }
}

// fichero CambioGrados.java 
// Este programa cambia grados centígrados por fahrenheit
import java.util.Scanner;
import java.util.InputMismatchException;
public class CambioGrados {
    private static final double FACTOR_MULTIPLICACION = 9.0 / 5.0;
    private static final double SUMA_FINAL = 32.0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double centigrados = 0;
        boolean entradaValida = false;       
        try {
            System.out.print("Introducir valor en grados Centígrados: ");
            centigrados = sc.nextDouble();
            entradaValida = true;
        } catch (InputMismatchException e) {
            System.err.println("Error de entrada: Debe introducir un valor numérico válido.");
        }
        if (entradaValida) {   
            // Cálculo usando la fórmula: F = (C * 9/5) + 32
            double fahrenheit = (centigrados * FACTOR_MULTIPLICACION) + SUMA_FINAL;
            System.out.printf("El resultado en grados Fahrenheit es: %.1f°F%n", fahrenheit);
        }
        sc.close();
    }
}
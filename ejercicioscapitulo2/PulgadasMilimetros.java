// fichero PulgadasMilimetros.java 
// Este programa cambia pulgadas por milímetros
import java.util.Scanner;

public class PulgadasMilimetros {
    private static final double FACTOR_CONVERSION = 25.4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Introduzca valor en pulgadas: ");
            double pulgadas = sc.nextDouble();
            if (pulgadas < 0) {
                System.err.println("Error: el valor no puede ser negativo.");
            } else {
                double milimetros = FACTOR_CONVERSION * pulgadas;
                System.out.printf("El resultado en milímetros es: %.2f%n", milimetros);
            }
        } 
        catch (Exception e) {
            System.err.println("Error: Debe introducir un número válido.");
        } 
        finally {
            sc.close();
        }
    }
}

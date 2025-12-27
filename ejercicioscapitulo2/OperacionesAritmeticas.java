// fichero OperacionesAritmeticas.java 
import java.util.InputMismatchException;
import java.util.Scanner;
public class OperacionesAritmeticas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double valor1 = 0; double valor2 = 0;
        boolean entradaValida = false;
        try {
            System.out.print("Introduzca el valor primero: ");
            valor1 = sc.nextDouble();
            System.out.print("Introduzca el valor segundo: ");
            valor2 = sc.nextDouble();
            entradaValida = true; 
        } 
        catch (InputMismatchException e) {
  System.err.println("Entrada inválida:deben ser valores numéricos");
        }
        if (entradaValida) {
            System.out.printf("Suma: %.2f%n", (valor1 + valor2));
            System.out.printf("Resta: %.2f%n", (valor1 - valor2));
        System.out.printf("Multiplicación: %.2f%n", (valor1 * valor2));
            if (valor2 == 0) {
                System.out.println("No es posible dividir por 0");
            } else {
                System.out.printf("División: %.2f%n", (valor1 / valor2));
            }
        }
        sc.close();
    }
}
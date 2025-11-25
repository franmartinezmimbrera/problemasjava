// fichero RaizEnesima.java
// Este programa calcula la raíz n-ésima de un número
import java.util.Scanner;
import java.util.InputMismatchException;
public class RaizEnesima {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.print("Introduce el número al que deseas calcular la raíz: ");
      double numero = scanner.nextDouble();
      System.out.print("Introduce el exponente de la raíz (n): ");
      double exponente = scanner.nextDouble();
      if (exponente == 0) {
        System.err.println("Error: El exponente no puede ser cero.");
        return;
      }
      if (numero < 0 && exponente % 2 == 0) {
        System.err.println("No existe raíz par real de un número <0");
        return;
      }
      double resultado = Math.pow(numero, 1.0 / exponente);
      System.out.printf("La raíz %.0f-ésima de %.4f es: %.6f%n", exponente, numero, resultado);
    } catch (InputMismatchException e) {
        System.err.println("Error: Por favor, introduce valores numéricos válidos.");
    } catch (Exception e) {
        System.err.println("Ha ocurrido un error inesperado.");
        e.printStackTrace();
    }
  }
}

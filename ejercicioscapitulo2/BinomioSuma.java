// fichero BinomioSuma.java
// Este programa calcula el binomio de suma (a+b)^2

import java.util.Scanner;
import java.util.InputMismatchException;

public class BinomioSuma {

  public static void main(String[] args) {

    try (Scanner scanner = new Scanner(System.in)) {

      System.out.print("Introduce el valor de a: ");
      double a = scanner.nextDouble();

      System.out.print("Introduce el valor de b: ");
      double b = scanner.nextDouble();

      double resultado = Math.pow(a, 2) + 2 * a * b + Math.pow(b, 2);

      System.out.printf("El resultado de (a+b)^2 es: %.4f%n", resultado);

    } catch (InputMismatchException e) {
      System.err.println("Error: Por favor, introduce valores numéricos válidos.");
    } catch (Exception e) {
      System.err.println("Ha ocurrido un error inesperado.");
      e.printStackTrace();
    }
  }
}

// fichero PerimetroCir.java
// Este programa calcula el perímetro de una circunferencia
import java.util.Scanner;
import java.util.InputMismatchException;

public class PerimetroCir {
  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    try {
      System.out.print("Introduzca el radio: ");
      double radio = scanner.nextDouble();
      if (radio <= 0) {
        System.err.println("Error: El radio debe ser un número positivo");
        return;
      }
      double perimetro = 2 * Math.PI * radio;
      System.out.printf("El perímetro de la circunferencia es: %.4f%n", perimetro);
    } catch (InputMismatchException e) {
          System.err.println("Error: Por favor, introduce un valor numérico válido");
    } catch (Exception e) {
          System.err.println("Ha ocurrido un error inesperado.");
          e.printStackTrace();
    } finally {
        scanner.close();
    }
  }
}

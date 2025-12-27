// fichero AreaTriangulo.java
// Este programa calcula el área de un triángulo a partir de sus lados mediante la fórmula de Herón
import java.util.Scanner;
import java.util.InputMismatchException;

public class AreaTriangulo {

  public static void main(String[] args) {

    try (Scanner scanner = new Scanner(System.in)) {

      System.out.print("Introduce la longitud del primer lado: ");
      double l1 = scanner.nextDouble();
      
      System.out.print("Introduce la longitud del segundo lado: ");
      double l2 = scanner.nextDouble();
      
      System.out.print("Introduce la longitud del tercer lado: ");
      double l3 = scanner.nextDouble();
      
      if (l1 <= 0 || l2 <= 0 || l3 <= 0) {
        System.err.println("Error: Los lados del triángulo deben ser positivos.");
        return;
      }

      if (l1 + l2 <= l3 || l1 + l3 <= l2 || l2 + l3 <= l1) {
        System.err.println("Error: Los lados introducidos no forman un triángulo válido.");
        return;
      }

      double sp = (l1 + l2 + l3) / 2.0;
      double area = Math.sqrt(sp * (sp - l1) * (sp - l2) * (sp - l3));
      System.out.printf("El área del triángulo es: %.4f%n", area);

    } catch (InputMismatchException e) {
          System.err.println("Error: Por favor, introduce valores numéricos válidos.");
    } catch (Exception e) {
          System.err.println("Ha ocurrido un error inesperado.");
          e.printStackTrace();
    }
  }
}

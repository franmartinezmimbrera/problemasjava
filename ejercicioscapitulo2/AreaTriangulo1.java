// fichero AreaTriangulo1.java
// Este programa calcula el área de un triángulo rectángulo a partir de la base y la altura
import java.util.Scanner;
import java.util.InputMismatchException;

public class AreaTriangulo1 {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.print("Introduce la base del triángulo rectángulo: ");
      double base = scanner.nextDouble();
      System.out.print("Introduce la altura del triángulo rectángulo: ");
      double altura = scanner.nextDouble();
      if (base <= 0 || altura <= 0) {
        System.err.println("Error: La base y la altura deben ser valores positivos.");
        return;
      }
      double area = (base * altura) / 2.0;
      System.out.printf("El área del triángulo rectángulo es: %.4f%n", area);
    } catch (InputMismatchException e) {
        System.err.println("Error: Por favor, introduce valores numéricos válidos.");
    } catch (Exception e) {
        System.err.println("Ha ocurrido un error inesperado.");
        e.printStackTrace();
    }
  }
}


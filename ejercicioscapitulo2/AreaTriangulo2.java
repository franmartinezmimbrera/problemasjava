// fichero AreaTriangulo2.java
// Este programa calcula el área de un triángulo equilátero a partir de uno de sus lados
import java.util.Scanner;
import java.util.InputMismatchException;

public class AreaTriangulo2 {

  public static void main(String[] args) {
        
    try (Scanner scanner = new Scanner(System.in)) {
      
      System.out.print("Introduce un lado del triángulo equilátero: ");
      double lado = scanner.nextDouble();
      if (lado <= 0) {
          System.err.println("Error: El lado del triángulo debe ser un número positivo.");
          return;
      }
      double area = (Math.sqrt(3) / 4) * lado * lado;
      System.out.printf("El área del triángulo equilátero de lado %.4f es: %.6f%n", lado, area);
    } catch (InputMismatchException e) {
        System.err.println("Error: Por favor, introduce un valor numérico válido para el lado.");
    } catch (Exception e) {
        System.err.println("Ha ocurrido un error inesperado.");
        e.printStackTrace();
    }
  }
}
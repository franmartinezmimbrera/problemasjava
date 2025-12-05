// fichero AlturaTriangulo.java
// Este programa calcula la altura de un triángulo equilátero
import java.util.Scanner;
import java.util.InputMismatchException;
public class AlturaTriangulo {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
    
      System.out.print("Introduzca el lado de un triángulo equilátero: ");
      double lado = scanner.nextDouble();
    
      if (lado <= 0) {
        System.err.println("Error: El lado debe ser un valor positivo.");
        return;
      }
      double altura = (Math.sqrt(3) * lado) / 2.0;
      System.out.printf("La altura de un triángulo equilátero de lado %.4f es: %.4f%n", lado, altura);
    
    } catch (InputMismatchException e) {
        System.err.println("Error: Por favor, introduzca un valor numérico válido.");
    } catch (Exception e) {
        System.err.println("Ha ocurrido un error inesperado.");
        e.printStackTrace();
    }
  }
}

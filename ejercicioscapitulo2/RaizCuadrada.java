// fichero RaizCuadrada.java
// Este programa calcula la raíz cuadrada de un número
import java.util.Scanner;
import java.util.InputMismatchException;

public class RaizCuadrada {

  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.print("Introduce el número a calcular la raíz cuadrada: ");
      double numero = scanner.nextDouble(); 
      if (numero < 0) {
          System.err.println("Error: No se puede calcular la raíz cuadrada real de un número negativo.");
          return;
      }
      double resultado = Math.sqrt(numero);
      System.out.printf("La raíz cuadrada de %.2f es: %.4f%n", numero, resultado);
    } catch (InputMismatchException e) {
        System.err.println("Error: Por favor, introduce un valor numérico válido.");
      
    } catch (Exception e) {
        System.err.println("Ha ocurrido un error inesperado.");
        e.printStackTrace();
    } 
  }
}
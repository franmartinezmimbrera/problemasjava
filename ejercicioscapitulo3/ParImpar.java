// fichero ParImpar.java
// Este programa indica si un número entero es par o impar
import java.util.Scanner;
import java.util.InputMismatchException;

public class ParImpar {

  public static void main(String[] args) {

    try (Scanner scanner = new Scanner(System.in)) {

      System.out.print("Introduzca un número entero: ");
      int numero = scanner.nextInt();
      if (numero % 2 == 0) {
        System.out.println("El número " + numero + " es PAR");
      } else {
        System.out.println("El número " + numero + " es IMPAR");
      }
    } catch (InputMismatchException e) {
        System.err.println("Error: Por favor, introduzca un número entero válido.");
    } catch (Exception e) {
        System.err.println("Ha ocurrido un error inesperado.");
        e.printStackTrace();
    }
  }
}

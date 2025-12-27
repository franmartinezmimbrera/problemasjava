// fichero LadoTriangulo.java 
// Este programa calcula el valor del lado a de un triangulo rectángulo usando el valor del lado b y la hipotenusa h 
import java.util.Scanner;
import java.util.InputMismatchException;
public class LadoTriangulo {
  public static void main(String[] args) {     
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.print("Introduzca el valor del lado 'b': ");
      double b = scanner.nextDouble();
      System.out.print("Introduzca el valor de la hipotenusa 'h': ");
      double h = scanner.nextDouble();
      if (b <= 0 || h <= 0) {
          System.err.println("Error:Los lados deben ser positivas.");
          return;
      }
      if (b >= h) {
          System.err.println("Error: La hipotenusa debe ser >b.");
          return;
      }      
      double a = Math.sqrt(Math.pow(h, 2) - Math.pow(b, 2));
      System.out.printf("El valor del lado 'a' es: %.4f%n", a);      
    } catch (InputMismatchException e) {
       System.err.println("Introduzca valores numéricos válidos");
    } catch (Exception e) {
        System.err.println("Ha ocurrido un error inesperado.");
        e.printStackTrace();
    }
  }
}
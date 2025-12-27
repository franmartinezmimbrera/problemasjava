// fichero VolumenCilindro.java
// Este programa calcula el volumen de un cilindro
import java.util.Scanner;
import java.util.InputMismatchException;

public class VolumenCilindro {

  public static void main(String[] args) {
        
    try (Scanner scanner = new Scanner(System.in)) {
      
      System.out.print("Introduzca el diámetro, en metros: ");
      double diametro = scanner.nextDouble(); 
      System.out.print("Introduzca la altura, en metros: ");
      double altura = scanner.nextDouble(); 
      
      if (diametro <= 0 || altura <= 0) {
          System.err.println("Error: El diámetro y la altura deben ser valores positivos.");
          return;
      }
      
      double radio = diametro / 2.0;    
      double volumen = Math.PI * Math.pow(radio, 2) * altura;
      System.out.printf("El volumen del cilindro es de %.4f metros cúbicos%n", volumen);

    } catch (InputMismatchException e) {
        System.err.println("Error: Por favor, introduzca valores numéricos válidos.");
      
    } catch (Exception e) {
        System.err.println("Ha ocurrido un error inesperado.");
        e.printStackTrace();
    }
  }
}
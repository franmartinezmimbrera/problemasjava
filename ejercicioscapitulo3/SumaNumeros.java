// fichero SumaNumeros.java
// Calcula la suma de números introducidos por teclado hasta que se introduce el número -50,
import java.util.Scanner;
import java.util.InputMismatchException;

public class SumaNumeros {
 
    public static void main(String[] args) {
    
      Scanner sc = new Scanner(System.in);
      double suma = 0;
      double numero = 0;
    
      System.out.println("Introduzca números para sumar. Escriba -50 para ver el resultado.");
      do {
          try {
              System.out.print("Introduzca número: ");
              if (sc.hasNextDouble()) {
                  numero = sc.nextDouble(); 
                  if (numero != -50) {
                      suma += numero;
                  }
              } else {
                  String entradaErronea = sc.next(); 
                  System.out.println("Error: '" + entradaErronea + "' no es un número. Inténtelo de nuevo.");
                  numero = 0; 
              }
          } catch (Exception e) {
              System.out.println("Ha ocurrido un error inesperado. Terminando programa.");
              break; 
          }
      } while (numero != -50);
      System.out.printf("La suma total de los números introducidos es: %.2f\n", suma); 
      sc.close();
    }
}
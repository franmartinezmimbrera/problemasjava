// fichero AreaTriangulo2.java
// Este programa calcula el área de un triáungulo a partir de sus lados mediante la fórmula de Herón

import java.util.Scanner;
import java.util.InputMismatchException;

public class AreaTriangulo2 {
 
    public static double AreaTrianguloHeron(double l1, double l2, double l3) {
        double sp = (l1 + l2 + l3) / 2;
        return Math.sqrt((sp * (sp - l1) * (sp - l2) * (sp - l3)));
    }
    
    public static boolean esTrianguloValido(double a, double b, double c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }
    public static void main(String[] args) {
      
      Scanner sc = new Scanner(System.in);
      try {       
        double l1x, l2x, l3x;
        System.out.print("Introduce la longitud del primer lado: ");
        l1x = sc.nextDouble();
        System.out.print("Introduce la longitud del segundo lado: ");
        l2x = sc.nextDouble();
        System.out.print("Introduce la longitud del tercer lado: ");
        l3x = sc.nextDouble();            
        if (esTrianguloValido(l1x, l2x, l3x)) {
            double area = AreaTrianguloHeron(l1x, l2x, l3x);
            System.out.printf("\nEl área del triángulo con lados %.2f, %.2f y %.2f es: %.2f\n", l1x, l2x, l3x, area);
        } else {
            System.out.println("\nError: Las longitudes ingresadas NO pueden formar un triángulo (Fallo de la Desigualdad Triangular).");
        }
        
      } catch (InputMismatchException e) {
          System.out.println("\nError de entrada: Debes introducir un valor numérico válido para las longitudes.");
      } finally {
          sc.close();
      }
    }
}
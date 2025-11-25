// fichero AreaCuadrado.java
// Este programa calcula el área de un cuadrado con de uno de sus lados
import java.util.Scanner;

public class AreaCuadrado {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Introduce el valor del lado de un cuadrado: ");
            double lado = sc.nextDouble();
            if (lado <= 0) {
                System.err.println("Error: el lado debe ser un número positivo.");
                return;
            }
            double area = lado * lado;
            System.out.printf("El área del cuadrado es: %.2f%n", area);
        } 
        catch (java.util.InputMismatchException e) {
           System.err.println("Error: Debes introducir un valor numérico válido.");
        }
        catch (Exception e) { 
            System.err.println("Ocurrió un error inesperado.");
        }
        finally {
            sc.close();
        }
    }
}

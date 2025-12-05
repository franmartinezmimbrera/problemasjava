// fichero PesetasEuros.java 
import java.util.InputMismatchException;
import java.util.Scanner;

public class PesetasEuros {

    private static final double TASA_CONVERSION = 166.386; 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Introduzca su valor en pesetas: ");
            double pesetas = sc.nextDouble(); 
            double euros = pesetas / TASA_CONVERSION;
            System.out.printf("Su valor en euros es: %.2f%n", euros);                        
        } 
        catch (InputMismatchException e) {
            System.err.println("Error de entrada: Debe introducir un valor numérico válido.");
        }
        catch (Exception e) {
            System.err.println("Ocurrió un error inesperado durante la conversión.");
        }
        finally {
            sc.close();
        }
    }
}
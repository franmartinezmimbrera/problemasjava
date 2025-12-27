// fichero CosteCoche.java 
// Este programa calcula el precio total de un coche
import java.util.Scanner;
import java.util.InputMismatchException;
public class CosteCoche {
  static final double GANANCIA_EMPRESA = 1.15,IMPUESTO_IVA = 1.21;     
  public static void main(String[] args) {       
    Scanner scanner = new Scanner(System.in);
    double costeCoche, pvAntesImpuestos, precioTotal;
    System.out.println("Introduce el coste del vehículo: ");
    try {
      costeCoche = scanner.nextDouble();
      if (costeCoche <= 0) {
        System.err.println("Error: El coste del vehículo debe ser >0");
        return; 
      }
      pvAntesImpuestos = costeCoche * GANANCIA_EMPRESA;
      precioTotal = pvAntesImpuestos * IMPUESTO_IVA;
      System.out.printf("El precio total del automóvil es: %.2f%n", precioTotal);
    } catch (InputMismatchException e) {
        System.err.println("Error: Por favor, introduce un valor numérico válido.");
    } catch (Exception e) {
        System.err.println("Ha ocurrido un error inesperado.");
        e.printStackTrace();
    } finally {
        scanner.close();
    }
  }
}
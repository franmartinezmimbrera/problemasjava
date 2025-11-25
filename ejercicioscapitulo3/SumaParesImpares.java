//fichero sumaparesimpares.java 
//Calcula la suma de 10 números pasados por teclado distinguiendo entre los pares e impares
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Arrays;

public class SumaParesImpares{
    private static final int TAMANO_ARRAY = 10;
    public static void main(String[] args) {       
        long[] numeros = new long[TAMANO_ARRAY];
        long sumaPares = 0;
        long sumaImpares = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Por favor, introduce %d números enteros:%n", TAMANO_ARRAY);
        for (int i = 0; i < TAMANO_ARRAY; i++) {
            while (true) {
                System.out.printf("Número %d: ", i + 1);
                try {
                    numeros[i] = scanner.nextLong();
                    break;                     
                } catch (InputMismatchException e) {                
                    System.err.println("Error: Entrada no válida. Debe ser un entero. Intenta de nuevo.");
                    scanner.next(); 
                }
            }
        }
        //Bucle for java para colecciones
        for (long numeroActual : numeros) { 
            if (numeroActual % 2 == 0) {
                sumaPares += numeroActual;                
            } else {
                sumaImpares += numeroActual;
            }
        }
        System.out.println("\nNúmeros ingresados: " + Arrays.toString(numeros));         
        System.out.printf("Suma total de los números PARES: %d%n", sumaPares);
        System.out.printf("Suma total de los números IMPARES: %d%n", sumaImpares);
        scanner.close();
    }
}
// fichero Mayor.java
// Este programa calcula cuál es el número mayor de 10 introducidos por teclado 

import java.util.Scanner;
import java.util.InputMismatchException;

public class Mayor {

    public static long mayor(int[] numeros) {
        long max = numeros[0];
        for (long i = 1; i < numeros.length; i++) {
            if (numeros[i] > max) {
                max = numeros[i]; 
            }
        }
        return max;
    }

    public static void main(String[] args) {
        
        final int NUM_ELEMENTOS = 10;
        Scanner sc = new Scanner(System.in);
        long[] numerosin = new long[NUM_ELEMENTOS];        
        
        try {
                       
            for (int i = 0; i < NUM_ELEMENTOS; i++) {
                System.out.printf("Introduzca número %d de %d: ", i + 1, NUM_ELEMENTOS);                
                numerosin[i] = sc.nextLong(); 
            }
        
            long maximo = mayor(numerosin);
            System.out.println("\nEl número mayor de todos los introducidos es: " + maximo);            
        
          } catch (InputMismatchException e) {
            System.out.println("\nError de entrada: Debe introducir un número entero válido.");
        } catch (Exception e) {
            System.out.println("\n Ha ocurrido un error inesperado: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
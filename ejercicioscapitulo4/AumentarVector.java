//fichero AumentarVector.java 
import java.util.Arrays;

public class AumentarVector {
    public static void main(String[] args) {

        int N_inicial = 3;
        int N_nuevo = 5;
        int[] vector = null;

        try {
            vector = new int[N_inicial];
        } catch (OutOfMemoryError e) {
            System.out.println("Error: No hay suficiente memoria para crear el vector.");
            System.exit(1);
        }
        for (int i = 0; i < N_inicial; i++) {
            vector[i] = i + 10;
        }

        System.out.println("\nVector Inicial (" + N_inicial + " elementos):");
        for (int val : vector) {
            System.out.print(val + " ");
        }
        System.out.println();

        try {
            vector = Arrays.copyOf(vector, N_nuevo);
        } catch (OutOfMemoryError e) {
            System.out.println("Error: No hay suficiente memoria para redimensionar el vector.");
            System.exit(1);
        }

        for (int i = N_inicial; i < N_nuevo; i++) {
            vector[i] = i + 100;
        }

        System.out.println("Vector Redimensionado (" + N_nuevo + " elementos):");
        for (int val : vector) {
            System.out.print(val + " ");
        }
        System.out.println();
        System.out.println("Memoria liberada automáticamente por el Garbage Collector.");
    }
}

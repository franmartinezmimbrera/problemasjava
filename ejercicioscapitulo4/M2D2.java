// fichero M2D2.java 
import java.util.ArrayList;

public class M2D2 {
    public static void main(String[] args) {

        int M = 3;
        int N = 4;
        ArrayList<ArrayList<Integer>> matriz = new ArrayList<>();

        try {
            for (int i = 0; i < M; i++) {
                ArrayList<Integer> fila = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    fila.add(i * N + j + 1);
                }
                matriz.add(fila);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Error: No hay suficiente memoria para crear la matriz.");
            System.exit(1);
        }

        System.out.println("\nMatriz Dinámica con ArrayList " + M + "x" + N);
        for (ArrayList<Integer> fila : matriz) {
            for (Integer valor : fila) {
                System.out.printf("%3d ", valor);
            }
            System.out.println();
        }
    }
}

// fichero M2D.java 

public class M2D {
    public static void main(String[] args) {

        int M = 3;
        int N = 4;
        int[][] matriz = null;
        try {
            matriz = new int[M][N];
        } catch (OutOfMemoryError e) {
            System.out.println("Error: No hay suficiente memoria para crear la matriz.");
            System.exit(1);
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                matriz[i][j] = i * N + j + 1;
            }
        }
        System.out.println("\nMatriz Dinámica " + M + "x" + N);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%3d ", matriz[i][j]);
            }
            System.out.println();
        }

        System.out.println("Memoria de la matriz liberada automáticamente por el Garbage Collector.");
    }
}

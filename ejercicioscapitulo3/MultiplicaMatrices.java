// fichero MultiplicaMatrices.java
public class MultiplicaMatrices {

    public static int[][] multiplicarMatricesCuadradas(int[][] A, int[][] B) {
        
        int N = A.length;
        if (N == 0 || A[0].length != N || B.length != N || B[0].length != N) {
            System.err.println("Error: Las matrices deben ser cuadradas (N x N) y tener el mismo tamaño.");
            return null;
        }
        int[][] C = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                C[i][j] = 0; 
                for (int k = 0; k < N; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
    public static void imprimirMatriz(int[][] matriz) {
        if (matriz == null) {
            System.out.println("[Matriz nula]");
            return;
        }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.printf("%5d ", matriz[i][j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
      
        int[][] A = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        int[][] B = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1} 
        };
        int N_actual = A.length;
        System.out.printf("Multiplicación de Matrices Cuadradas (%dx%d x %dx%d) ---%n", 
                          N_actual, N_actual, N_actual, N_actual);
        System.out.println("\nMatriz A:");
        imprimirMatriz(A);

        System.out.println("\nMatriz B (Identidad):");
        imprimirMatriz(B);

        int[][] C = multiplicarMatricesCuadradas(A, B);
        
        if (C != null) {
            System.out.println("\nMatriz Resultado C = A * B:");
            imprimirMatriz(C); 
        }
    }
}
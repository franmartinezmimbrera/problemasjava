// fichero Trasposicion.java
// Trasposición de una matriz de enteros
public class Trasposicion {

    private static final int R_FILAS = 3;
    private static final int C_COLUMNAS = 4;
    // Función que traspone una matriz de tamaño filas_orig x cols_orig
    public static void trasponerMatriz(int filas_orig, int cols_orig, int[][] original, int[][] transpuesta) {
        for (int i = 0; i < filas_orig; i++) {
            for (int j = 0; j < cols_orig; j++) {
                transpuesta[j][i] = original[i][j];
            }
        }
    }
    public static void imprimirMatriz(int filas, int columnas, int[][] matriz) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.printf("%4d", matriz[i][j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {

        int[][] A = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        int[][] AT = new int[C_COLUMNAS][R_FILAS];

        System.out.printf("Matriz Original A (%d x %d)%n", R_FILAS, C_COLUMNAS);
        imprimirMatriz(R_FILAS, C_COLUMNAS, A);
        trasponerMatriz(R_FILAS, C_COLUMNAS, A, AT);
        System.out.printf("%nMatriz Transpuesta AT (%d x %d)%n", C_COLUMNAS, R_FILAS);
        imprimirMatriz(C_COLUMNAS, R_FILAS, AT);
    }
}

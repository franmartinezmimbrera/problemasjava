// fichero BInterpola.java 
public class BInterpola {
    //Realiza la Búsqueda por Interpolación en un array ordenado uniformemente distribuido.
    public static int busquedaInterpolacion(int[] arr, int n, int objetivo) {
        int bajo = 0;
        int alto = n - 1;
        int pos;
        while (bajo <= alto && objetivo >= arr[bajo] && objetivo <= arr[alto]) {
            if (bajo == alto) {
                if (arr[bajo] == objetivo) {
                    return bajo;
                }
                return -1;
            }
            if (arr[alto] == arr[bajo]) {
                if (arr[bajo] == objetivo)
                    return bajo;
                else
                    return -1;
            }
            // Fórmula de interpolación
            pos = bajo + (int)(((double)(alto - bajo) / (arr[alto] - arr[bajo])) * (objetivo - arr[bajo]));
            if (pos < 0 || pos >= n) {
                return -1; 
            }
            if (arr[pos] == objetivo) {
                return pos;
            }
            if (arr[pos] < objetivo) {
                bajo = pos + 1;
            } else {
                alto = pos - 1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] datosOrdenados = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int n = datosOrdenados.length;
        int objetivo1 = 70;
System.out.println("Conjunto de datos (Ordenado):{10,20,30,...,100}");
        int indice = busquedaInterpolacion(datosOrdenados, n, objetivo1);
        if (indice != -1) {
            System.out.printf("Resultado para %d: Encontrado en el índice %d.%n", objetivo1, indice);
        } else {
            System.out.printf("Resultado para %d: No encontrado.%n", objetivo1);
        }
    }
}

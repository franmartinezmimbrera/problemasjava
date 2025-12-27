// fichero ShellSort.java 
public class ShellSort {
    //Implementación del algoritmo Shellsort usando la secuencia de Knuth (3*h + 1). 
    public static void shellsortKnuth(int[] arr) {
        int n = arr.length,h = 1;
        while (h < n / 3) {
            h = 3 * h + 1; 
        }
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= h && arr[j - h] > temp) {
                    arr[j] = arr[j - h];
                    j -= h;
                }
                arr[j] = temp;
            }
            h = h / 3;
        }
    }
    public static void main(String[] args) {
        int[] datos = {90, 8, 70, 6, 50, 4, 30, 2, 10, 0, 85, 15, 65, 35};
        System.out.println("Array original desordenado:");
        for (int val : datos) {System.out.print(val + " ");}
        System.out.println();
        shellsortKnuth(datos);
        System.out.println("\nArray ordenado con Shellsort (Knuth):");
        for (int val : datos) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
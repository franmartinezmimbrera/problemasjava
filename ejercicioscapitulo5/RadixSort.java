// fichero RadixSort.java 
import java.util.Arrays; 

public class RadixSort {
    //Función auxiliar: obtiene el máximo valor del array.
    public static int getMax(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0; 
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    // Algoritmo Counting Sort estable (Ordenación por Conteo).
    public static void countingSort(int[] arr, int exp) {
        int n = arr.length;int[] output = new int[n];int[] count = new int[10];
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
    public static void radixSort(int[] arr) {
        int n = arr.length;
        if (n == 0) return;
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }
    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("Array original:");
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
        radixSort(arr);
        System.out.println("\nArray ordenado:");
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
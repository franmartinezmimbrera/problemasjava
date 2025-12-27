// fichero HeapSort.java 
public class HeapSort {
    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    // Función Heapify: restaura la propiedad de Max-Heap en un subárbol
    //con raíz en 'i' dentro de un array de tamaño 'n'.
    public static void heapify(int[] arr, int n, int i) {
        
        int largest = i;      
        int left = 2 * i + 1;  
        int right = 2 * i + 2; 
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
    public static void heapSort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("Array original:");
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
        heapSort(arr);
        System.out.println("Array ordenado con Heapsort:");
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
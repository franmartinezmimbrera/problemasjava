// fichero QuickSort.java

public class QSort {

    // Función de intercambio (swap)
    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    // Función de partición
    public static int particion(int[] arr, int bajo, int alto) {
        int pivote = arr[alto];
        int i = bajo - 1;

        for (int j = bajo; j <= alto - 1; j++) {
            if (arr[j] <= pivote) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, alto);
        return i + 1;
    }
    // Función recursiva Quicksort
    public static void quicksort(int[] arr, int bajo, int alto) {
        if (bajo < alto) {
            int pi = particion(arr, bajo, alto);
            quicksort(arr, bajo, pi - 1);
            quicksort(arr, pi + 1, alto);
        }
    }
    public static void main(String[] args) {

        int[] datos = {10, 70, 8, 90, 1000, 5};

        System.out.print("Vector original: ");
        for (int val : datos) {
            System.out.print(val + " ");
        }
        System.out.println();
        quicksort(datos, 0, datos.length - 1);
        System.out.print("Vector ordenado con Quicksort: ");
        for (int val : datos) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

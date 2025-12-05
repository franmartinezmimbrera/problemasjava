// fichero MergeSort.java 
public class MergeSort {
    //Función auxiliar para combinar (merge) dos sub-arrays ordenados.
    //Esta función es la parte "Combinar" del "Divide y Vencerás".
    public static void merge(int[] arr, int inicio, int medio, int fin) {
       
        int n1 = medio - inicio + 1;
        int n2 = fin - medio;
        int total_temp = n1 + n2;
        int[] temp_arr = new int[total_temp];

        for (int i = 0; i < n1; i++) {
            temp_arr[i] = arr[inicio + i];
        }
        for (int j = 0; j < n2; j++) {
            temp_arr[n1 + j] = arr[medio + 1 + j];
        }
        int i = 0;     
        int j = n1;    
        int k = inicio; 
        while (i < n1 && j < total_temp) {
            if (temp_arr[i] <= temp_arr[j]) {
                arr[k] = temp_arr[i];
                i++;
            } else {
                arr[k] = temp_arr[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = temp_arr[i];
            i++;
            k++;
        }
        while (j < total_temp) {
            arr[k] = temp_arr[j];
            j++;
            k++;
        }
    }
     //Función recursiva principal de MergeSort.
    public static void mergeSort(int[] arr, int inicio, int fin) {
        if (inicio < fin) {
            int medio = inicio + (fin - inicio) / 2;
            mergeSort(arr, inicio, medio);
            mergeSort(arr, medio + 1, fin);            
            merge(arr, inicio, medio, fin);
        }
    }
    public static void main(String[] args) {
        int[] datos = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Vector original desordenado:");
        for (int val : datos) {
            System.out.print(val + " ");
        }
        System.out.println();
        mergeSort(datos, 0, datos.length - 1);
        System.out.println("\nVector ordenado con MergeSort:");
        for (int val : datos) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
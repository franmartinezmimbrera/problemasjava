// fichero BusquedaBinaria.java/
public class BusquedaBinaria {
    public static int busquedaBinaria(int[] arr, int objetivo) {
        int bajo = 0;
        int alto = arr.length - 1;

        while (bajo <= alto) {
            int medio = bajo + (alto - bajo) / 2;
            if (arr[medio] == objetivo) {
                return medio;
            } else if (arr[medio] < objetivo) {
                bajo = medio + 1; 
            } else {
                alto = medio - 1; 
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] datosOrdenados = {5, 8, 12, 15, 20, 30, 40};
        int objetivo1 = 20;
        int objetivo2 = 25;

        System.out.println("Conjunto de datos (Ordenado): {5, 8, 12, 15, 20, 30, 40}");
        int indice = busquedaBinaria(datosOrdenados, objetivo1);
        if (indice != -1) {
            System.out.printf("Resultado para %d: Encontrado en el índice %d.%n", objetivo1, indice);
        } else {
            System.out.printf("Resultado para %d: No encontrado.%n", objetivo1);
        }
        indice = busquedaBinaria(datosOrdenados, objetivo2);
        if (indice != -1) {
            System.out.printf("Resultado para %d: Encontrado en el índice %d.%n", objetivo2, indice);
        } else {
            System.out.printf("Resultado para %d: No encontrado.%n", objetivo2);
        }
    }
}

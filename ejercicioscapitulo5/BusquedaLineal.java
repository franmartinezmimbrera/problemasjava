// fichero BusquedaLineal.java 
public class BusquedaLineal {
    public static int busquedaLineal(int[] arr, int n, int objetivo) {
        for (int i = 0; i < n; i++) {
            if (arr[i] == objetivo) {return i;}
        }
        return -1; 
    }
    public static void main(String[] args) {
        int[] datos = {10, 5, 20, 15, 8, 30};
        int n = datos.length;
        int objetivo1 = 15;
        int indice;
       System.out.println("Conjunto de datos: {10, 5, 20, 15, 8, 30}");
        indice = busquedaLineal(datos, n, objetivo1);
        if (indice != -1) {
            System.out.printf("Resultado para %d: Encontrado en el índice %d.\n", objetivo1, indice);
        } else {
            System.out.printf("Resultado para %d: No encontrado.\n", objetivo1);
        }
    }
}
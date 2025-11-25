// fichero Burbuja.java
public class Burbuja {

    public static void ordenarBurbuja(int[] arr) {
        int n = arr.length;
        int temp;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {

        int[] numeros = {10, 7, 8, 9, 1, 5};

        System.out.print("\nConjunto original: ");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println();
        ordenarBurbuja(numeros);
        System.out.print("Conjunto ordenado (Bubble Sort): ");
        for (int num : numeros) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

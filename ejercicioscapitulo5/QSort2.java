/* Fichero QSort2.java */
import java.util.Arrays; 

public class QSort2 {

    public static void main(String[] args) {

        int[] numeros = {50, 10, 8, 20, 40};
        
        System.out.print("Vector antes de sort: ");
        for (int val : numeros) {
            System.out.print(val + " ");
        }
        System.out.println();

        Arrays.sort(numeros);

        System.out.print("Vector después de sort: ");
        for (int val : numeros) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
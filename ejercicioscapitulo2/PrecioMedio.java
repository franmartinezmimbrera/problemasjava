// fichero PrecioMedio.java 
// Este programa calcula el precio medio dados 3 precios
import java.util.Scanner;
import java.util.InputMismatchException;

public class PrecioMedio {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        double precio1 = 0, precio2 = 0, precio3 = 0, media;
        boolean entradaValida = false;     

        try {
            System.out.print("Introduzca el precio en establecimiento 1, en euros: ");
            precio1 = sc.nextDouble();
            
            System.out.print("Introduzca el precio en establecimiento 2, en euros: ");
            precio2 = sc.nextDouble();
            
            System.out.print("Introduzca el precio en establecimiento 3, en euros: ");
            precio3 = sc.nextDouble();

            entradaValida = true;

        } catch (InputMismatchException e) {
            System.err.println("Error de entrada: Todos los precios deben ser valores numéricos.");
        }
        
        if (entradaValida) {
            if (precio1 < 0 || precio2 < 0 || precio3 < 0) {
                System.err.println("Error: Los precios no pueden ser valores negativos.");
            } else {
                media = (precio1 + precio2 + precio3) / 3;
                System.out.printf("El precio medio del producto es de %.2f euros%n", media);
            }
        }
 
        sc.close();
    }
}
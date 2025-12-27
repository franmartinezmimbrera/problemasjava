// fichero Bisiesto.java
// Este programa dice si un año es bisiesto o no
import java.util.Scanner;

public class Bisiesto {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Introduzca un año: ");
            int anio = sc.nextInt(); 
            if ((anio % 4 == 0 && anio % 100 != 0) || (anio % 400 == 0))
                System.out.println("El año " + anio + " ES BISIESTO");
            else
                System.out.println("El año " + anio + " NO ES BISIESTO");

        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: debe introducir un número entero (por ejemplo, 2024).");
        } finally {
            sc.close();
        }
    }
}

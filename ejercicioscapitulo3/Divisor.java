// fichero Divisor.java
// Este programa calcula si un número a es divisor de otro número b
import java.util.Scanner;
public class Divisor {
    public static boolean divisible(long a, long b) {
        return b != 0 && a % b == 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Introduzca valor de a: ");
            long a = sc.nextLong();
            System.out.print("Introduzca valor de b: ");
            long b = sc.nextLong();
            if (divisible(a, b)) {
                System.out.println(a + " es divisible entre " + b);
            } else if (b == 0) {
                System.out.println("Error: no se puede dividir entre 0.");
            } else {
                System.out.println(a + " NO es divisible entre " + b);
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: debe introducir números enteros.");
        } finally {
            sc.close();
        }
    }
}

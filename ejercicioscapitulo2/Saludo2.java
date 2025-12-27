// fichero Saludo2.java 
// Este programa hace un saludo personalizado
import java.util.Scanner;

public class Saludo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("¡Hola! ¿Cómo te llamas?");
        String nombre = sc.nextLine();
        System.out.println("Qué tal estás " + nombre);
        sc.close();
        
    }
}

// fichero Capturar.java
// Este programa hace preguntas y con ello hace respuestas 
import java.util.Scanner;

public class Capturar {
      public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Dinos tu edad,peso y color favorito:");
            System.out.print("Edad: ");
            int edad = sc.nextInt();
            System.out.print("Peso (en Kg): ");
            float peso = sc.nextFloat();
            sc.nextLine(); 
            System.out.print("Color favorito: ");
            String color = sc.nextLine();
            System.out.println("¡El " + color + "!!!");
            System.out.printf("¿Cómo puede gustarte el %s con %d años y pesando %.2f Kg.?%n",
                              color, edad, peso);
        } 
        catch (Exception e) {
            System.err.println("Entrada no válida. Asegúrate de introducir los datos correctamente.");
        } 
        finally {
            sc.close();
        }
    }
}

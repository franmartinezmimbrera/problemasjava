// fichero Invierte.java
// Invierte una cadena de texto sin usar métodos del lenguaje

import java.util.Scanner;

public class Invierte {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce una cadena de texto: ");
        String texto = sc.nextLine();

        char[] caracteres = texto.toCharArray();

        int inicio = 0;
        int fin = caracteres.length - 1;
        char temp;

        while (inicio < fin) {
            temp = caracteres[inicio];
            caracteres[inicio] = caracteres[fin];
            caracteres[fin] = temp;

            inicio++;
            fin--;
        }

        String invertida = new String(caracteres);

        System.out.println("Cadena original:  " + texto);

        System.out.println("Cadena invertida: " + invertida);
        
        sc.close();

    }
}

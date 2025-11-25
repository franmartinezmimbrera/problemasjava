// fichero AsignaLibera.java 
public class AsignaLibera {

    public static void main(String[] args) {

        int N = 5;
        int[] vector=null;
        try{
            vector = new int[N];     
        }catch (OutOfMemoryError e) {
            System.out.println("Error: No hay suficiente memoria para crear el array.");
            System.exit(1);
        }
        System.out.println("Vector Dinámico de " + N + " Elementos");
        for (int i = 0; i < N; i++) {
            vector[i] = i * 10;
            System.out.print(vector[i] + " ");
        }
        System.out.println();
        System.out.println("Memoria liberada automáticamente por el Garbage Collector.");
    }
}

// fichero CrearLeerBinario.java
//Programa que crea y lee un archivo binario con un registro de alumno.

import java.io.*;

public class CrearLeerBinario {

    static class RegistroAlumno implements Serializable {
        
        int id;
        String nombre;
        float nota;
        
        RegistroAlumno(int id, String nombre, float nota) {
            this.id = id;
            this.nombre = nombre;
            this.nota = nota;
        }
    }

    private static final String NOMBRE_ARCHIVO = "registro.dat";

    static void crearBinario() {
        
        RegistroAlumno alumno = new RegistroAlumno(101, "Sofia Perez", 9.5f);

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(NOMBRE_ARCHIVO))) {

            salida.writeObject(alumno);
            System.out.println("Registro guardado correctamente en '" + NOMBRE_ARCHIVO + "'.");

        } catch (IOException e) {
            System.err.println("Error al escribir el archivo binario: " + e.getMessage());
        }
    }

    static void leerBinario() {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(NOMBRE_ARCHIVO))) {

            RegistroAlumno alumnoLeido = (RegistroAlumno) entrada.readObject();
            System.out.printf("Datos cargados del archivo: ID=%d, Nombre=%s, Nota=%.2f%n",
                    alumnoLeido.id, alumnoLeido.nombre, alumnoLeido.nota);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el archivo binario: " + e.getMessage());
            System.exit(1);
        }
    }
    public static void main(String[] args) {
        crearBinario();
        leerBinario();
    }
}

// fichero Estadisticas.java
// Este programa calcula estadísticas sobre alumnos
import java.util.Scanner;
import java.util.InputMismatchException;
public class Estadisticas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Introduce total de suspensos: ");
            long suspensos = sc.nextLong();
            System.out.print("Introduce total de aprobados: ");
            long aprobados = sc.nextLong();
            System.out.print("Introduce total de notables: ");
            long notables = sc.nextLong();
            System.out.print("Introduce total de sobresalientes: ");
            long sobresalientes = sc.nextLong();
            long totalAlumnos = suspensos + aprobados + notables + sobresalientes;
            if (totalAlumnos == 0) {
                System.err.println("Error: El total de alumnos no puede ser cero.");
                return;
            }
            if (suspensos < 0 || aprobados < 0 || notables < 0 || sobresalientes < 0) {
                System.err.println("Error: Los valores no pueden ser negativos.");
                return;
            }
            double porcentajeSuspensos = (suspensos / (double) totalAlumnos) * 100;
            double porcentajeAprobados = (aprobados / (double) totalAlumnos) * 100;
            double porcentajeNotables = (notables / (double) totalAlumnos) * 100;
            double porcentajeSobresalientes = (sobresalientes / (double) totalAlumnos) * 100;
            double porcentajeSuperan = ((aprobados + notables + sobresalientes) / (double) totalAlumnos) * 100;
            System.out.printf("Total de alumnos: %d%n", totalAlumnos);
            System.out.printf("Porcentaje que superan la asignatura: %.2f%%%n", porcentajeSuperan);
            System.out.printf("Porcentaje de suspensos: %.2f%%%n", porcentajeSuspensos);
            System.out.printf("Porcentaje de aprobados: %.2f%%%n", porcentajeAprobados);
            System.out.printf("Porcentaje de notables: %.2f%%%n", porcentajeNotables);
            System.out.printf("Porcentaje de sobresalientes: %.2f%%%n", porcentajeSobresalientes);
        } catch (InputMismatchException e) {
            System.err.println("Error: Introduce solo números enteros válidos.");
        } finally {
            sc.close();
        }
    }
}

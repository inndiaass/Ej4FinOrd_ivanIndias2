

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GestionNotasAlumnos {

  private static List<Alumno> listaAlumnos = new ArrayList<>();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    int opcion;

    menu();

    scanner.close();
  }

  private static void menu() {
    int opcion;
    do {
      System.out.println("----- MENÚ DE GESTIÓN DE NOTAS -----");
      System.out.println("1. Dar de Alta Alumno");
      System.out.println("2. Dar de Baja Alumno");
      System.out.println("3. Modificar Alumno (nombre o notas)");
      System.out.println("4. Consultar Alumnos");
      System.out.println("5. Salir");
      System.out.print("Ingrese su opción: ");
      opcion = -1;
      try {
        opcion = scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Entrada inválida. Por favor, ingrese un número.");
      } finally {
        scanner.nextLine(); // Consumir el salto de línea pendiente
      }

      switch (opcion) {
        case 1:
          DAOAlumno.darDeAltaAlumno();
          break;
        case 2:
          darDeBajaAlumno();
          break;
        case 3:
          modificarAlumno();
          break;
        case 4:
          consultarAlumnos();
          break;
        case 5:
          System.out.println("Saliendo de la aplicación. ¡Hasta pronto!");
          break;
        default:
          System.out.println("Opción no válida. Por favor, intente de nuevo.");
      }
      System.out.println("\nPresione Enter para continuar...");
      scanner.nextLine(); // Consumir el salto de línea pendiente
    } while (opcion != 5);
  }


  private static void darDeBajaAlumno() {
    System.out.println("\n--- DAR DE BAJA ALUMNO ---");
    if (listaAlumnos.isEmpty()) {
      System.out.println("No hay alumnos para dar de baja.");
      return;
    }

    System.out.print("Ingrese el nombre del alumno a dar de baja: ");
    String nombre = scanner.nextLine();

    Alumno alumnoABorrar = DAOAlumno.buscarAlumnoPorNombre(nombre);

    if (alumnoABorrar != null) {
      listaAlumnos.remove(alumnoABorrar);
      System.out.println("Alumno " + nombre + " dado de baja exitosamente.");
    } else {
      System.out.println("No se encontró ningún alumno con el nombre '" + nombre + "'.");
    }
  }

  private static void modificarAlumno() {
    System.out.println("\n--- MODIFICAR ALUMNO ---");
    if (listaAlumnos.isEmpty()) {
      System.out.println("No hay alumnos para modificar.");
      return;
    }

    System.out.print("Ingrese el nombre del alumno a modificar: ");
    String nombre = scanner.nextLine();

    Alumno alumnoAModificar = DAOAlumno.buscarAlumnoPorNombre(nombre);

    if (alumnoAModificar != null) {
      System.out.println("Alumno encontrado: " + alumnoAModificar);
      System.out.println("¿Qué desea modificar?");
      System.out.println("1. Nombre");
      System.out.println("2. Notas");
      System.out.print("Ingrese su opción: ");

      int opcionModificacion = -1;
      try {
        opcionModificacion = scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Entrada inválida. Por favor, ingrese un número.");
      } finally {
        scanner.nextLine(); // Consumir el salto de línea pendiente
      }


      switch (opcionModificacion) {
        case 1:
          System.out.print("Ingrese el nuevo nombre para " + alumnoAModificar.getNombre() + ": ");
          String nuevoNombre = scanner.nextLine();
          // Verificar que el nuevo nombre no exista ya
          if (DAOAlumno.buscarAlumnoPorNombre(nuevoNombre) != null && !nuevoNombre.equalsIgnoreCase(alumnoAModificar.getNombre())) {
            System.out.println("Error: Ya existe un alumno con ese nombre.");
          } else {
            alumnoAModificar.setNombre(nuevoNombre);
            System.out.println("Nombre del alumno modificado a: " + nuevoNombre);
          }
          break;
        case 2:
          modificarNotasAlumno(alumnoAModificar);
          break;
        default:
          System.out.println("Opción de modificación no válida.");
      }
    } else {
      System.out.println("No se encontró ningún alumno con el nombre '" + nombre + "'.");
    }
  }

  private static void modificarNotasAlumno(Alumno alumno) {
    System.out.println("\n--- MODIFICAR NOTAS DE " + alumno.getNombre() + " ---");
    System.out.println("Notas actuales: " + alumno.getNotas());

    int opcionNotas;
    do {
      System.out.println("\n1. Añadir nota");
      System.out.println("2. Eliminar nota");
      System.out.println("3. Volver al menú anterior");
      System.out.print("Ingrese su opción: ");
      opcionNotas = -1;
      try {
        opcionNotas = scanner.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("Entrada inválida. Por favor, ingrese un número.");
      } finally {
        scanner.nextLine(); // Consumir el salto de línea pendiente
      }

      switch (opcionNotas) {
        case 1:
          try {
            System.out.print("Ingrese la nueva nota (0-10) a añadir: ");
            int nuevaNota = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            if (nuevaNota >= 0 && nuevaNota <= 10) {
              alumno.agregarNota(nuevaNota);
              System.out.println("Nota añadida exitosamente.");
            } else {
              System.out.println("La nota debe estar entre 0 y 10.");
            }
          } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.nextLine(); // Consumir la entrada errónea
          }
          break;
        case 2:
          if (alumno.getNotas().isEmpty()) {
            System.out.println("El alumno no tiene notas para eliminar.");
            break;
          }
          try {
            System.out.print("Ingrese la nota a eliminar: ");
            int notaAEliminar = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            if (alumno.getNotas().remove(Integer.valueOf(notaAEliminar))) {
              System.out.println("Nota eliminada exitosamente.");
            } else {
              System.out.println("La nota especificada no se encontró.");
            }
          } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.nextLine(); // Consumir la entrada errónea
          }
          break;
        case 3:
          System.out.println("Volviendo al menú de modificación.");
          break;
        default:
          System.out.println("Opción no válida.");
      }
      System.out.println("Notas actuales de " + alumno.getNombre() + ": " + alumno.getNotas());
    } while (opcionNotas != 3);
  }


  private static void consultarAlumnos() {
    System.out.println("\n--- CONSULTA DE ALUMNOS ---");
    if (listaAlumnos.isEmpty()) {
      System.out.println("No hay alumnos registrados.");
      return;
    }

    for (Alumno alumno : listaAlumnos) {
      System.out.println(alumno);
      if (!alumno.getNotas().isEmpty()) {
        double sumaNotas = 0;
        for (int nota : alumno.getNotas()) {
          sumaNotas += nota;
        }
        double promedio = sumaNotas / alumno.getNotas().size();
        System.out.printf("  Promedio de notas: %.2f%n", promedio);
      } else {
        System.out.println("  No tiene notas registradas.");
      }
      System.out.println("--------------------");
    }
  }

}

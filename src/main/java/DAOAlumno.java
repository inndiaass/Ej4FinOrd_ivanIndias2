import java.util.InputMismatchException;

public class DAOAlumno {

    static void darDeAltaAlumno() {
      System.out.println("\n--- DAR DE ALTA ALUMNO ---");
      System.out.print("Ingrese el nombre del alumno: ");
      String nombre = GestionNotasAlumnos.scanner.nextLine();

      // Verificar si el alumno ya existe
      if (GestionNotasAlumnos.buscarAlumnoPorNombre(nombre) != null) {
        System.out.println("Error: Ya existe un alumno con ese nombre.");
        return;
      }

      Alumno nuevoAlumno = new Alumno(nombre);
      GestionNotasAlumnos.listaAlumnos.add(nuevoAlumno);

      boolean agregarMasNotas = true;
      while (agregarMasNotas) {
        System.out.print("¿Desea agregar una nota para " + nombre + "? (s/n): ");
        String respuesta = GestionNotasAlumnos.scanner.nextLine().toLowerCase();
        if (respuesta.equals("s")) {
          try {
            System.out.print("Ingrese la nota (0-10): ");
            int nota = GestionNotasAlumnos.scanner.nextInt();
            GestionNotasAlumnos.scanner.nextLine(); // Consumir el salto de línea
            if (nota >= 0 && nota <= 10) {
              nuevoAlumno.agregarNota(nota);
            } else {
              System.out.println("La nota debe estar entre 0 y 10.");
            }
          } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número para la nota.");
            GestionNotasAlumnos.scanner.nextLine(); // Consumir la entrada errónea
          }
        } else {
          agregarMasNotas = false;
        }
      }

      System.out.println("Alumno " + nombre + " dado de alta exitosamente.");
    }

    // Método auxiliar para buscar un alumno por nombre (ignora mayúsculas/minúsculas)
    static Alumno buscarAlumnoPorNombre(String nombre) {
      for (Alumno alumno : GestionNotasAlumnos.listaAlumnos) {
        if (alumno.getNombre().equalsIgnoreCase(nombre)) {
          return alumno;
        }
      }
      return null;
    }
}

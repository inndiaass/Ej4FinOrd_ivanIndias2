
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Clase Alumno
class Alumno {
  private String nombre;
  private List<Integer> notas;

  public Alumno(String nombre) {
    this.nombre = nombre;
    this.notas = new ArrayList<>();
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public List<Integer> getNotas() {
    return notas;
  }

  public void agregarNota(int nota) {
    if (nota >= 0 && nota <= 10) {
      this.notas.add(nota);
    } else {
      System.out.println("La nota debe estar entre 0 y 10.");
    }
  }

  public void setNotas(List<Integer> notas) {
    this.notas = notas;
  }

  @Override
  public String toString() {
    return "Nombre: " + nombre + ", Notas: " + notas;
  }
}


package gestion;

import modelo.Libro;
import java.util.ArrayList;
import java.util.List;

public class InventarioLibros {

    private ArrayList<Libro> libros;

    public InventarioLibros() {
        libros = Persistencia.cargarLibros();

        if (libros == null) {
            libros = new ArrayList<>();
        }
    }

    public void agregarLibro(Libro libro) {
        if (libro == null) {
            System.out.println("El libro no puede estar vacío.");
            return;
        }

        if (libro.getIdLibro() == null
                || libro.getIdLibro().trim().isEmpty()) {
            System.out.println("El ID del libro es obligatorio.");
            return;
        }

        if (buscarLibro(libro.getIdLibro()) != null) {
            System.out.println("Ya existe un libro con ese ID.");
            return;
        }

        libros.add(libro);
        Persistencia.guardarLibros(libros);

        System.out.println("Libro agregado correctamente.");
    }

    public void eliminarLibro(Libro libro) {
        if (libro == null || libro.getIdLibro() == null) {
            System.out.println("El libro no es válido.");
            return;
        }

        Libro encontrado = buscarLibro(libro.getIdLibro());

        if (encontrado == null) {
            System.out.println("El libro no existe.");
            return;
        }

        if (!encontrado.isDisponible()) {
            System.out.println(
                "No se puede eliminar un libro prestado."
            );
            return;
        }

        libros.remove(encontrado);
        Persistencia.guardarLibros(libros);

        System.out.println("Libro eliminado correctamente.");
    }

    public void actualizarLibro(Libro libro) {
        if (libro == null || libro.getIdLibro() == null) {
            System.out.println("El libro no es válido.");
            return;
        }

        for (int i = 0; i < libros.size(); i++) {
            Libro actual = libros.get(i);

            if (actual.getIdLibro().equals(libro.getIdLibro())) {

                // Conserva el estado de disponibilidad actual.
                libro.setDisponible(actual.isDisponible());

                libros.set(i, libro);
                Persistencia.guardarLibros(libros);

                System.out.println("Libro actualizado correctamente.");
                return;
            }
        }

        System.out.println("No se encontró el libro para actualizar.");
    }

    public Libro buscarLibro(String idLibro) {
        if (idLibro == null || idLibro.trim().isEmpty()) {
            return null;
        }

        for (Libro libro : libros) {
            if (libro != null
                    && libro.getIdLibro() != null
                    && libro.getIdLibro().equals(idLibro)) {
                return libro;
            }
        }

        return null;
    }

    public List<Libro> getLibros() {
        return new ArrayList<>(libros);
    }

    public void mostrarInventario() {
        if (libros.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }

        System.out.println("\n--- INVENTARIO DE LA BIBLIOTECA ---");

        for (Libro libro : libros) {
            if (libro == null) {
                continue;
            }

            String estado = libro.isDisponible()
                    ? "Disponible"
                    : "Prestado";

            System.out.println(
                "ID: " + libro.getIdLibro()
                + " | Título: " + libro.getTituloLibro()
                + " | Estado: " + estado
            );
        }
    }
}



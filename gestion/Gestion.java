package gestion;

import modelo.Libro;

import java.util.ArrayList;
import java.util.List;

public class InventarioLibros {
	private ArrayList<Libro> libros;

	public InventarioLibros() {
		libros = new ArrayList<>();
	}

	public void agregarLibro(Libro libro) {
		libros.add(libro);
	}

	public void eliminarLibro(Libro libro) {
		libros.remove(libro);
	}

	public void actualizarLibro(Libro libro) {
		int index = libros.indexOf(libro);
		if (index != -1) {
			libros.set(index, libro);
		}
	}
	public Libro buscarLibro(String idLibro) {
		for (Libro libro : libros) {
			if (libro.getIdLibro().equals(idLibro)) {
				return libro;
			}
		}
		return null;
	}

	public List<Libro> getLibros() {
		return libros;
	}
	
}

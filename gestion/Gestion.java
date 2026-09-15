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

	public List<Libro> getLibros() {
		return libros;
	}
	
}

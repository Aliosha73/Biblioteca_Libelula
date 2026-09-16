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

	public void buscarLibro( libro libro){
		
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void mostrarInventario(){
		if (listaLibros.isempty()){
			System.out.println("el inventario esta vacio");
			return;


		}
		System.out.println("\n-- INVENTARIO BIBLIOTECA");
		for (libro libro : listaLibros){
			String estado = libro.isDisponible() ?"Disponible" : "prestado";
			System.out.println("ID LIBRO:" + libro.getIdLibro()+ "TITULO:" + libro.getTituloLibro + "ESTADO:" estado);

		}
	}
	
}

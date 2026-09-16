package modelo;

import java.time.LocalDate;
import java.util.Objects;

/** Representa un préstamo de un libro a un cliente. */
public class Prestamo {
	private final String libro;
	private final String cliente;
	private final LocalDate fechaPrestamo;
	private final LocalDate fechaVencimiento;
	private LocalDate fechaDevolucion;

	public Prestamo(String libro, String cliente, LocalDate fechaPrestamo,
					LocalDate fechaVencimiento) {
		this.libro = textoObligatorio(libro, "El libro es obligatorio");
		this.cliente = textoObligatorio(cliente, "El cliente es obligatorio");
		this.fechaPrestamo = Objects.requireNonNull(fechaPrestamo,
				"La fecha de préstamo es obligatoria");
		this.fechaVencimiento = Objects.requireNonNull(fechaVencimiento,
				"La fecha de vencimiento es obligatoria");
		if (fechaVencimiento.isBefore(fechaPrestamo)) {
			throw new IllegalArgumentException("La fecha de vencimiento no puede ser anterior al préstamo");
		}
	}

	public void devolver() {
		devolver(LocalDate.now());
	}

	public void devolver(LocalDate fecha) {
		Objects.requireNonNull(fecha, "La fecha de devolución es obligatoria");
		if (estaDevuelto()) {
			throw new IllegalStateException("El préstamo ya fue devuelto");
		}
		if (fecha.isBefore(fechaPrestamo)) {
			throw new IllegalArgumentException("La devolución no puede ser anterior al préstamo");
		}
		fechaDevolucion = fecha;
	}

	public boolean estaDevuelto() {
		return fechaDevolucion != null;
	}

	public boolean estaVencido() {
		return !estaDevuelto() && LocalDate.now().isAfter(fechaVencimiento);
	}

	public String getLibro() { return libro; }
	public String getCliente() { return cliente; }
	public LocalDate getFechaPrestamo() { return fechaPrestamo; }
	public LocalDate getFechaVencimiento() { return fechaVencimiento; }
	public LocalDate getFechaDevolucion() { return fechaDevolucion; }

	private static String textoObligatorio(String valor, String mensaje) {
		if (valor == null || valor.trim().isEmpty()) {
			throw new IllegalArgumentException(mensaje);
		}
		return valor.trim();
	}
}

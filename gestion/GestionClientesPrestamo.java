
package gestion;

import modelo.Cliente;
import modelo.Libro;
import modelo.Prestamo;

import java.time.LocalDate;
import java.util.ArrayList;

public class GestionClientesPrestamos {

    private ArrayList<Cliente> clientes;
    private ArrayList<Prestamo> prestamos;
    private InventarioLibros inventario;

    public GestionClientesPrestamos() {
        this(new InventarioLibros());
    }

    public GestionClientesPrestamos(InventarioLibros inventario) {
        this.inventario = inventario;
        clientes = Persistencia.cargarClientes();
        prestamos = Persistencia.cargarPrestamos();
    }

    // REGISTRAR CLIENTES

    public void registrarCliente(Cliente cliente) {
        if (cliente == null) {
            System.out.println("El cliente no puede estar vacío.");
            return;
        }

        if (cliente.getNombre() == null
                || cliente.getNombre().trim().isEmpty()) {
            System.out.println("El nombre es obligatorio.");
            return;
        }

        if (buscarCliente(cliente.getId()) != null) {
            System.out.println("Ya existe un cliente con ese ID.");
            return;
        }

        clientes.add(cliente);
        Persistencia.guardarClientes(clientes);

        System.out.println("Cliente registrado correctamente.");
    }

    public Cliente buscarCliente(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }

        return null;
    }

    public void eliminarCliente(int id) {
        Cliente cliente = buscarCliente(id);

        if (cliente == null) {
            System.out.println("El cliente no existe.");
            return;
        }

        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCliente().equals(String.valueOf(id))
                    && !prestamo.estaDevuelto()) {
                System.out.println(
                    "No se puede eliminar: el cliente tiene un préstamo activo."
                );
                return;
            }
        }

        clientes.remove(cliente);
        Persistencia.guardarClientes(clientes);

        System.out.println("Cliente eliminado correctamente.");
    }

    public void mostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE CLIENTES ---");

        for (Cliente cliente : clientes) {
            System.out.println(
                "ID: " + cliente.getId()
                + " | Nombre: " + cliente.getNombre()
                + " | Teléfono: " + cliente.getTelefono()
            );
        }
    }

    // REGISTRAR PRÉSTAMOS

    public void registrarPrestamo(int idCliente, String idLibro) {
        registrarPrestamo(idCliente, idLibro, 14);
    }

    public void registrarPrestamo(
            int idCliente, String idLibro, int diasPrestamo) {

        Cliente cliente = buscarCliente(idCliente);
        Libro libro = inventario.buscarLibro(idLibro);

        if (cliente == null) {
            System.out.println("El cliente no existe.");
            return;
        }

        if (libro == null) {
            System.out.println("El libro no existe.");
            return;
        }

        if (!libro.isDisponible()) {
            System.out.println("El libro no está disponible.");
            return;
        }

        if (diasPrestamo <= 0) {
            System.out.println(
                "Los días del préstamo deben ser mayores que cero."
            );
            return;
        }

        // Un cliente solo puede tener un libro prestado a la vez.
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCliente().equals(String.valueOf(idCliente))
                    && !prestamo.estaDevuelto()) {
                System.out.println(
                    "El cliente ya tiene un préstamo activo."
                );
                return;
            }
        }

        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaVencimiento =
                fechaPrestamo.plusDays(diasPrestamo);

        Prestamo nuevoPrestamo = new Prestamo(
            libro.getIdLibro(),
            String.valueOf(cliente.getId()),
            fechaPrestamo,
            fechaVencimiento
        );

        if (libro.prestar()) {
            cliente.setLibroPrestado(libro);
            prestamos.add(nuevoPrestamo);

            Persistencia.guardarLibros(
                new ArrayList<>(inventario.getLibros())
            );
            Persistencia.guardarClientes(clientes);
            Persistencia.guardarPrestamos(prestamos);

            System.out.println("Préstamo registrado correctamente.");
            System.out.println(
                "Fecha de vencimiento: " + fechaVencimiento
            );
        }
    }

    // DEVOLVER LIBROS

    public void devolverLibro(int idCliente, String idLibro) {
        Prestamo prestamoActivo = null;

        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCliente().equals(String.valueOf(idCliente))
                    && prestamo.getLibro().equals(idLibro)
                    && !prestamo.estaDevuelto()) {
                prestamoActivo = prestamo;
                break;
            }
        }

        if (prestamoActivo == null) {
            System.out.println(
                "No existe un préstamo activo para ese cliente y libro."
            );
            return;
        }

        Libro libro = inventario.buscarLibro(idLibro);
        Cliente cliente = buscarCliente(idCliente);

        if (libro == null || cliente == null) {
            System.out.println(
                "No se encontraron los datos del libro o del cliente."
            );
            return;
        }

        prestamoActivo.devolver();
        libro.devolver();
        cliente.setLibroPrestado(null);

        Persistencia.guardarLibros(
            new ArrayList<>(inventario.getLibros())
        );
        Persistencia.guardarClientes(clientes);
        Persistencia.guardarPrestamos(prestamos);

        System.out.println("Libro devuelto correctamente.");
    }

    // MOSTRAR PRÉSTAMOS

    public void mostrarPrestamos() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE PRÉSTAMOS ---");

        for (Prestamo prestamo : prestamos) {
            String estado;

            if (prestamo.estaDevuelto()) {
                estado = "Devuelto";
            } else if (prestamo.estaVencido()) {
                estado = "Vencido";
            } else {
                estado = "Activo";
            }

            System.out.println(
                "Libro: " + prestamo.getLibro()
                + " | Cliente ID: " + prestamo.getCliente()
                + " | Fecha préstamo: " + prestamo.getFechaPrestamo()
                + " | Vencimiento: " + prestamo.getFechaVencimiento()
                + " | Devolución: "
                + (prestamo.getFechaDevolucion() == null
                    ? "Pendiente"
                    : prestamo.getFechaDevolucion())
                + " | Estado: " + estado
            );
        }
    }
}


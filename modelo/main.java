
import gestion.InventarioLibros;
import gestion.GestionClientesPrestamos;

public class Main {

    public static void main(String[] args) {

        InventarioLibros inventario = new InventarioLibros();

        GestionClientesPrestamos gestion =
                new GestionClientesPrestamos(inventario);

        System.out.println("=== SISTEMA DE BIBLIOTECA ===");

        System.out.println("\n--- LIBROS ---");
        inventario.mostrarInventario();

        System.out.println("\n--- CLIENTES ---");
        gestion.mostrarClientes();

        System.out.println("\n--- PRÉSTAMOS ---");
        gestion.mostrarPrestamos();

        System.out.println("\nSistema iniciado correctamente.");
    }
}


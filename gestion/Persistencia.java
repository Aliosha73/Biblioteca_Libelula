
package gestion;

import modelo.Libro;
import modelo.Cliente;
import modelo.Prestamo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Persistencia {

    private static final String CARPETA = "datos/";

    //libros

    public static void guardarLibros(ArrayList<Libro> libros) {
        guardar(libros, CARPETA + "libros.dat");
    }

    public static ArrayList<Libro> cargarLibros() {
        return cargar(CARPETA + "libros.dat", Libro.class);
    }

    //clientes 

    public static void guardarClientes(ArrayList<Cliente> clientes) {
        guardar(clientes, CARPETA + "clientes.dat");
    }

    public static ArrayList<Cliente> cargarClientes() {
        return cargar(CARPETA + "clientes.dat", Cliente.class);
    }

    //prestamos 

    public static void guardarPrestamos(ArrayList<Prestamo> prestamos) {
        guardar(prestamos, CARPETA + "prestamos.dat");
    }

    public static ArrayList<Prestamo> cargarPrestamos() {
        return cargar(CARPETA + "prestamos.dat", Prestamo.class);
    }

    // guarda los fatos 

    private static void guardar(Object datos, String archivo) {
        File carpeta = new File(CARPETA);

        if (!carpeta.exists() && !carpeta.mkdirs()) {
            System.out.println("No se pudo crear la carpeta de datos.");
            return;
        }

        try (ObjectOutputStream salida = new ObjectOutputStream(
                new FileOutputStream(archivo))) {

            salida.writeObject(datos);

        } catch (IOException e) {
            System.out.println(
                "Error al guardar los datos: " + e.getMessage()
            );
        }
    }

    //cargar los datos 

    private static <T> ArrayList<T> cargar(
            String archivo, Class<T> tipo) {

        File fichero = new File(archivo);

        if (!fichero.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream entrada = new ObjectInputStream(
                new FileInputStream(fichero))) {

            Object datos = entrada.readObject();

            if (!(datos instanceof ArrayList<?>)) {
                System.out.println(
                    "El archivo no contiene una lista válida: " + archivo
                );
                return new ArrayList<>();
            }

            ArrayList<?> lista = (ArrayList<?>) datos;
            ArrayList<T> resultado = new ArrayList<>();

            for (Object elemento : lista) {
                if (!tipo.isInstance(elemento)) {
                    System.out.println(
                        "Se encontraron datos incompatibles en: " + archivo
                    );
                    return new ArrayList<>();
                }

                resultado.add(tipo.cast(elemento));
            }

            return resultado;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(
                "Error al cargar los datos: " + e.getMessage()
            );

            return new ArrayList<>();
        }
    }
}




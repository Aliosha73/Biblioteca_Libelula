
package modelo;

import java.io.Serializable;

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private int id;
    private String direccion;
    private String telefono;
    private Libro libroPrestado;

    public Cliente(String nombre, int id,
            String direccion, String telefono) {

        this.nombre = validarTexto(nombre, "El nombre es obligatorio.");

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID del cliente debe ser mayor que cero.");
        }

        this.id = id;
        this.direccion = validarTexto(
                direccion, "La dirección es obligatoria.");
        this.telefono = validarTexto(
                telefono, "El teléfono es obligatorio.");
        this.libroPrestado = null;
    }

    private String validarTexto(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }

        return valor.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = validarTexto(
                nombre, "El nombre es obligatorio.");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "El ID del cliente debe ser mayor que cero.");
        }

        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = validarTexto(
                direccion, "La dirección es obligatoria.");
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = validarTexto(
                telefono, "El teléfono es obligatorio.");
    }

    public Libro getLibroPrestado() {
        return libroPrestado;
    }

    public void setLibroPrestado(Libro libroPrestado) {
        this.libroPrestado = libroPrestado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", libroPrestado=" + libroPrestado +
                '}';
    }
}




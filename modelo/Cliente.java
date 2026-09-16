public class Cliente {
    private String nombre;
    int id;
    String direccion;
    private String telefono;
    Libro libroPrestado;

    public Cliente(String nombre, int id, String direccion, String telefono) {
        this.nombre = nombre;
        this.id = id;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Libro getLibroPrestado() {
        return libro;
    }

    public void setLibroPrestado(Libro libroPrestado) {
        this.libro = libro;
    }
}


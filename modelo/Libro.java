
package modelo;

import java.io.Serializable;

public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idLibro;
    private String tituloLibro;
    private String autorLibro;
    private String editorialLibro;
    private String anioPublicacionLibro;
    private String generolibro;
    private CategoriaLibro categoriaLibro;
    private boolean disponible;

    public Libro(String idLibro, String tituloLibro,
            String autorLibro, String editorialLibro,
            String anioPublicacionLibro, String generolibro,
            CategoriaLibro categoriaLibro, boolean disponible) {

        this.idLibro = validarTexto(idLibro, "El ID del libro es obligatorio.");
        this.tituloLibro = validarTexto(tituloLibro, "El título es obligatorio.");
        this.autorLibro = validarTexto(autorLibro, "El autor es obligatorio.");
        this.editorialLibro = validarTexto(editorialLibro, "La editorial es obligatoria.");
        this.anioPublicacionLibro = validarTexto(
                anioPublicacionLibro, "El año de publicación es obligatorio.");
        this.generolibro = validarTexto(generolibro, "El género es obligatorio.");

        if (categoriaLibro == null) {
            throw new IllegalArgumentException(
                    "La categoría del libro es obligatoria.");
        }

        this.categoriaLibro = categoriaLibro;
        this.disponible = disponible;
    }

    private String validarTexto(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }

        return valor.trim();
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = validarTexto(
                idLibro, "El ID del libro es obligatorio.");
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = validarTexto(
                tituloLibro, "El título es obligatorio.");
    }

    public String getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(String autorLibro) {
        this.autorLibro = validarTexto(
                autorLibro, "El autor es obligatorio.");
    }

    public String getEditorialLibro() {
        return editorialLibro;
    }

    public void setEditorialLibro(String editorialLibro) {
        this.editorialLibro = validarTexto(
                editorialLibro, "La editorial es obligatoria.");
    }

    public String getAnioPublicacionLibro() {
        return anioPublicacionLibro;
    }

    public void setAnioPublicacionLibro(String anioPublicacionLibro) {
        this.anioPublicacionLibro = validarTexto(
                anioPublicacionLibro,
                "El año de publicación es obligatorio.");
    }

    public String getGenerolibro() {
        return generolibro;
    }

    public void setGenerolibro(String generolibro) {
        this.generolibro = validarTexto(
                generolibro, "El género es obligatorio.");
    }

    public CategoriaLibro getCategoriaLibro() {
        return categoriaLibro;
    }

    public void setCategoriaLibro(CategoriaLibro categoriaLibro) {
        if (categoriaLibro == null) {
            throw new IllegalArgumentException(
                    "La categoría del libro es obligatoria.");
        }

        this.categoriaLibro = categoriaLibro;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean prestar() {
        if (!disponible) {
            return false;
        }

        disponible = false;
        return true;
    }

    public void devolver() {
        disponible = true;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro='" + idLibro + '\'' +
                ", tituloLibro='" + tituloLibro + '\'' +
                ", autorLibro='" + autorLibro + '\'' +
                ", editorialLibro='" + editorialLibro + '\'' +
                ", anioPublicacionLibro='" + anioPublicacionLibro + '\'' +
                ", generolibro='" + generolibro + '\'' +
                ", categoriaLibro=" + categoriaLibro +
                ", disponible=" + disponible +
                '}';
    }
}

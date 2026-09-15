public class Libro{
    private String idLibro;
    private String tituloLibro;
    private String autorLibro;
    private String autorLibro;
    private String editorialLibro;
    private String anioPublicacionLibro;
    private String generolibro;
    private CategoriaLibro categoriaLibro;
    private boolean disponible;





Public Libro(String idLibro, String tituloLibro, String autorLibro, String editorialLibro, String anioPublicacionLibro, String generolibro, CategoriaLibro categoriaLibro, boolean disponible) {
        this.idLibro = idLibro;
        this.tituloLibro = tituloLibro;
        this.autorLibro = autorLibro;
        this.editorialLibro = editorialLibro;
        this.anioPublicacionLibro = anioPublicacionLibro;
        this.generolibro = generolibro;
        this.categoriaLibro = categoriaLibro;
        this.disponible = disponible;
        
    }

    public String getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(String idLibro) {
        this.idLibro = idLibro;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public String getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(String autorLibro) {
        this.autorLibro = autorLibro;
    }

    public String getEditorialLibro() {
        return editorialLibro;
    }

    public void setEditorialLibro(String editorialLibro) {
        this.editorialLibro = editorialLibro;
    }

    public String getAnioPublicacionLibro() {
        return anioPublicacionLibro;
    }

    public void setAnioPublicacionLibro(String anioPublicacionLibro) {
        this.anioPublicacionLibro = anioPublicacionLibro;
    }

    public String getGenerolibro() {
        return generolibro;
    }

    public void setGenerolibro(String generolibro) {
        this.generolibro = generolibro;
    }

    public CategoriaLibro getCategoriaLibro() {
        return categoriaLibro;
    }

    public void setCategoriaLibro(CategoriaLibro categoriaLibro) {
        this.categoriaLibro = categoriaLibro;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    public boolean prestar() {
        if (disponible) {
            disponible = false;
            return true;
        }
        return false;
    }
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
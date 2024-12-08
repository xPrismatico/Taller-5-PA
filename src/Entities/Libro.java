package Entities;

public class Libro {
    private String codigoIsbn;
    private String nombre;
    private String autor;
    private String categoria;
    private int cantidadPaginas;
    private int stock;

    public Libro(String codigoIsbn, String nombre, String autor, String categoria, int cantidadPaginas, int stock) {
        this.codigoIsbn = codigoIsbn;
        this.nombre = nombre;
        this.autor = autor;
        this.categoria = categoria;
        this.cantidadPaginas = cantidadPaginas;
        this.stock = stock;
    }

    public String getCodigoIsbn() {
        return codigoIsbn;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantidadPaginas() {
        return cantidadPaginas;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) { this.stock = stock; }

    public String toString(){

        return "ISBN: " + this.codigoIsbn + "\n Titulo: " + this.nombre+ "\n Autor: " + this.autor + "\n Categoria: " + this.categoria + "\n Paginas: " + this.cantidadPaginas + "\n Stock: " + this.stock;
    }
}

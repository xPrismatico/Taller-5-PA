package Entities;

public class Transaccion {
    private String rut;
    private String nombreVendedor;
    private String apellido;
    private String isbn;
    private String nombreLibro;
    private String tipoTransaccion;

    public Transaccion(String rut, String nombreVendedor, String apellido, String isbn, String nombreLibro, String tipoTransaccion) {
        this.rut = rut;
        this.nombreVendedor = nombreVendedor;
        this.apellido = apellido;
        this.isbn = isbn;
        this.nombreLibro = nombreLibro;
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getRut() {
        return rut;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public String getApellido() {
        return apellido;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }
}

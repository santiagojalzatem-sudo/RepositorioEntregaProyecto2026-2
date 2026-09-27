package co.uniquindio.academiaparcial.Model;

// Profesor.java
public class Profesor {
    private String identificacion;
    private String nombre;
    private Idioma idioma;
    private String telefono;
    private double tarifaPorSesion;

    public Profesor(String identificacion, String nombre, Idioma idioma, String telefono, double tarifaPorSesion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.idioma = idioma;
        this.telefono = telefono;
        this.tarifaPorSesion = tarifaPorSesion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public String getTelefono() {
        return telefono;
    }

    public double getTarifaPorSesion() {
        return tarifaPorSesion;
    }
}

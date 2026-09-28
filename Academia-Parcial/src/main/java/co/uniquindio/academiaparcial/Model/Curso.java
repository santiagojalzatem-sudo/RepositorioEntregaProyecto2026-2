package co.uniquindio.academiaparcial.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Curso {
    private String codigo;
    private String nombre;
    private Idioma idioma;
    private EstadoCurso estado;
    private String descripcion;
    private int duracionMeses;
    private double valorMensualidad;
    private List<String> listBeneficios;

    public Curso(String codigo, String nombre, Idioma idioma, EstadoCurso estado, String descripcion, int duracionMeses, double valorMensualidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.idioma = idioma;
        this.estado = estado;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensualidad = valorMensualidad;
        this.listBeneficios = new ArrayList<String>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public EstadoCurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoCurso estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public double getValorMensualidad() {
        return valorMensualidad;
    }

    public void setValorMensualidad(double valorMensualidad) {
        this.valorMensualidad = valorMensualidad;
    }

    public List<String> getListBeneficios() {
        return listBeneficios;
    }

    public void setListBeneficios(List<String> listBeneficios) {
        this.listBeneficios = listBeneficios;
    }

    public void agregarBeneficio(String beneficio) {
        this.listBeneficios.add(beneficio);
    }

    public abstract double calcularCostoBase();

    public abstract Curso clonar();

    public String toString() {
        return codigo + " - " + nombre + " (" + idioma + ")";
    }
}

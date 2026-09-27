package co.uniquindio.academiaparcial.Model;

// CursoIntensivo.java
public class CursoIntensivo extends Curso {

    public CursoIntensivo(String codigo, String nombre, Idioma idioma, EstadoCurso estado, String descripcion, int duracionMeses, double valorMensualidad) {
        super(codigo, nombre, idioma, estado, descripcion, duracionMeses, valorMensualidad);
    }

    @Override
    public double calcularCostoBase() {
        return (getDuracionMeses() * getValorMensualidad()) * 1.15;
    }
}
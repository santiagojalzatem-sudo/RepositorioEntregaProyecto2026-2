package co.uniquindio.academiaparcial.Model;

// CursoRegular.java
public class CursoRegular extends Curso {


    public CursoRegular(String codigo, String nombre, Idioma idioma, EstadoCurso estado, String descripcion, int duracionMeses, double valorMensualidad) {
        super(codigo, nombre, idioma, estado, descripcion, duracionMeses, valorMensualidad);
    }

    @Override
    public double calcularCostoBase() {
        return getDuracionMeses() * getValorMensualidad();
    }
}
package co.uniquindio.academiaparcial.Model;

// CursoPersonalizado.java
public class CursoPersonalizado extends Curso {
    private int numeroSesiones;
    private double tarifaReferenciaSesion;
    private NivelReferencia nivelReferencia;
    private String objetivos;

    public CursoPersonalizado(String codigo, String nombre, Idioma idioma, EstadoCurso estado, String descripcion, int duracionMeses, double valorMensualidad, int numeroSesiones, double tarifaReferenciaSesion, NivelReferencia nivelReferencia, String objetivos) {
        super(codigo, nombre, idioma, estado, descripcion, duracionMeses, valorMensualidad);
        this.numeroSesiones = numeroSesiones;
        this.tarifaReferenciaSesion = tarifaReferenciaSesion;
        this.nivelReferencia = nivelReferencia;
        this.objetivos = objetivos;
    }

    public int getNumeroSesiones() {
        return numeroSesiones;
    }

    public double getTarifaReferenciaSesion() {
        return tarifaReferenciaSesion;
    }

    public NivelReferencia getNivelReferencia() {
        return nivelReferencia;
    }

    public String getObjetivos() {
        return objetivos;
    }

    @Override
    public double calcularCostoBase() {
        return (getDuracionMeses() * getValorMensualidad()) + (numeroSesiones * tarifaReferenciaSesion);
    }
}

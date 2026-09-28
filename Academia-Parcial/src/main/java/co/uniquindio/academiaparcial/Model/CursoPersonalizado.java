package co.uniquindio.academiaparcial.Model;

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

    public void setNumeroSesiones(int numeroSesiones) {
        this.numeroSesiones = numeroSesiones;
    }

    public double getTarifaReferenciaSesion() {
        return tarifaReferenciaSesion;
    }

    public void setTarifaReferenciaSesion(double tarifaReferenciaSesion) {
        this.tarifaReferenciaSesion = tarifaReferenciaSesion;
    }

    public NivelReferencia getNivelReferencia() {
        return nivelReferencia;
    }

    public void setNivelReferencia(NivelReferencia nivelReferencia) {
        this.nivelReferencia = nivelReferencia;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public double calcularCostoBase() {
        double base = getDuracionMeses() * getValorMensualidad();
        double sesiones = numeroSesiones * tarifaReferenciaSesion;
        double total = base + sesiones;
        return total;
    }

    public Curso clonar() {
        CursoPersonalizado copia = new CursoPersonalizado(getCodigo(), getNombre(), getIdioma(), getEstado(), getDescripcion(), getDuracionMeses(), getValorMensualidad(), numeroSesiones, tarifaReferenciaSesion, nivelReferencia, objetivos);
        for (int i = 0; i < getListBeneficios().size(); i++) {
            copia.agregarBeneficio(getListBeneficios().get(i));
        }
        return copia;
    }
}

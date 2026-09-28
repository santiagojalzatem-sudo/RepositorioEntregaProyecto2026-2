package co.uniquindio.academiaparcial.Model;

public class CursoIntensivo extends Curso {

    public CursoIntensivo(String codigo, String nombre, Idioma idioma, EstadoCurso estado, String descripcion, int duracionMeses, double valorMensualidad) {
        super(codigo, nombre, idioma, estado, descripcion, duracionMeses, valorMensualidad);
    }

    public double calcularCostoBase() {
        double base = getDuracionMeses() * getValorMensualidad();
        double total = base * 1.15;
        return total;
    }

    public Curso clonar() {
        CursoIntensivo copia = new CursoIntensivo(getCodigo(), getNombre(), getIdioma(), getEstado(), getDescripcion(), getDuracionMeses(), getValorMensualidad());
        for (int i = 0; i < getListBeneficios().size(); i++) {
            copia.agregarBeneficio(getListBeneficios().get(i));
        }
        return copia;
    }
}

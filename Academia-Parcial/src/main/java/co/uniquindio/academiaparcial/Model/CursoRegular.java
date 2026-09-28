package co.uniquindio.academiaparcial.Model;

public class CursoRegular extends Curso {

    public CursoRegular(String codigo, String nombre, Idioma idioma, EstadoCurso estado, String descripcion, int duracionMeses, double valorMensualidad) {
        super(codigo, nombre, idioma, estado, descripcion, duracionMeses, valorMensualidad);
    }

    public double calcularCostoBase() {
        double total = getDuracionMeses() * getValorMensualidad();
        return total;
    }

    public Curso clonar() {
        CursoRegular copia = new CursoRegular(getCodigo(), getNombre(), getIdioma(), getEstado(), getDescripcion(), getDuracionMeses(), getValorMensualidad());
        for (int i = 0; i < getListBeneficios().size(); i++) {
            copia.agregarBeneficio(getListBeneficios().get(i));
        }
        return copia;
    }
}

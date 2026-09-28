package co.uniquindio.academiaparcial.Factory;

import co.uniquindio.academiaparcial.Model.Curso;
import co.uniquindio.academiaparcial.Model.EstadoCurso;
import co.uniquindio.academiaparcial.Model.Idioma;
import co.uniquindio.academiaparcial.Model.NivelReferencia;
import co.uniquindio.academiaparcial.Model.ServicioAdicional;
import co.uniquindio.academiaparcial.Model.TipoCurso;

public class InglesFactory implements AcademiaAbstractFactory {

    public Curso crearCurso(TipoCurso tipo, String codigo, String nombre, Idioma idioma, EstadoCurso estado, String descripcion, int duracionMeses, double valorMensualidad, int sesiones, double tarifaSesion, NivelReferencia nivel, String objetivos) {
        Curso curso = CursoFactory.crearCurso(tipo, codigo, nombre, Idioma.INGLES, estado, descripcion, duracionMeses, valorMensualidad, sesiones, tarifaSesion, nivel, objetivos);
        return curso;
    }

    public ServicioAdicional crearServicio(String codigo, String nombre, String descripcion, double precio, boolean disponible) {
        ServicioAdicional servicio = new ServicioAdicional(codigo, nombre + " (Ingles)", descripcion, precio, disponible);
        return servicio;
    }
}

package co.uniquindio.academiaparcial.Factory;

import co.uniquindio.academiaparcial.Model.Curso;
import co.uniquindio.academiaparcial.Model.CursoIntensivo;
import co.uniquindio.academiaparcial.Model.CursoPersonalizado;
import co.uniquindio.academiaparcial.Model.CursoRegular;
import co.uniquindio.academiaparcial.Model.EstadoCurso;
import co.uniquindio.academiaparcial.Model.Idioma;
import co.uniquindio.academiaparcial.Model.NivelReferencia;
import co.uniquindio.academiaparcial.Model.TipoCurso;

public class CursoFactory {

    public static Curso crearCurso(TipoCurso tipo, String codigo, String nombre, Idioma idioma, EstadoCurso estado, String descripcion, int duracionMeses, double valorMensualidad, int sesiones, double tarifaSesion, NivelReferencia nivel, String objetivos) {
        Curso curso = null;
        if (tipo == TipoCurso.REGULAR) {
            curso = new CursoRegular(codigo, nombre, idioma, estado, descripcion, duracionMeses, valorMensualidad);
            curso.agregarBeneficio("Plataforma virtual");
            curso.agregarBeneficio("Material didactico");
        } else if (tipo == TipoCurso.INTENSIVO) {
            curso = new CursoIntensivo(codigo, nombre, idioma, estado, descripcion, duracionMeses, valorMensualidad);
            curso.agregarBeneficio("Plataforma virtual");
            curso.agregarBeneficio("Club de conversacion");
        } else if (tipo == TipoCurso.PERSONALIZADO) {
            curso = new CursoPersonalizado(codigo, nombre, idioma, estado, descripcion, duracionMeses, valorMensualidad, sesiones, tarifaSesion, nivel, objetivos);
            curso.agregarBeneficio("Tutor personal");
            curso.agregarBeneficio("Club de conversacion");
        }
        return curso;
    }
}

package co.uniquindio.academiaparcial.Factory;

import co.uniquindio.academiaparcial.Model.*;

// CursoFactory.java (Patrón Factory Method)
public class CursoFactory {

    public static Curso crearCurso(TipoCurso tipo, String codigo, String nombre, Idioma idioma, EstadoCurso estado, String descripcion, int duracionMeses, double valorMensualidad, int sesiones, double tarifaSesion, NivelReferencia nivel, String objetivos) {
        if (tipo == TipoCurso.REGULAR) {
            return new CursoRegular(codigo, nombre, idioma, estado, descripcion, duracionMeses, valorMensualidad);
        } else if (tipo == TipoCurso.INTENSIVO) {
            return new CursoIntensivo(codigo, nombre, idioma, estado, descripcion, duracionMeses, valorMensualidad);
        } else if (tipo == TipoCurso.PERSONALIZADO) {
            return new CursoPersonalizado(codigo, nombre, idioma, estado, descripcion, duracionMeses, valorMensualidad, sesiones, tarifaSesion, nivel, objetivos);
        }
        return null;
    }
}

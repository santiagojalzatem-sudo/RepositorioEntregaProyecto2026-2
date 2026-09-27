package co.uniquindio.academiaparcial.Controller;

import co.uniquindio.academiaparcial.Factory.CursoFactory;
import co.uniquindio.academiaparcial.Model.*;
import co.uniquindio.academiaparcial.Singleton.AcademiaHolder;

import java.time.LocalDate;
import java.util.List;

public class AcademiaController {

    private Academia academia;

    public AcademiaController() {
        // Obtención de la instancia global única (Patrón Singleton)
        this.academia = AcademiaHolder.getInstance().getAcademia();
    }

    public void registrarEstudiante(String doc, String nombre, String tel, String correo, int edad) {
        Estudiante nuevo = new Estudiante(doc, nombre, tel, correo, edad, LocalDate.now());
        academia.registrarEstudiante(nuevo);
    }

    public void registrarProfesor(String id, String nombre, Idioma idioma, String tel, double tarifa) {
        Profesor nuevo = new Profesor(id, nombre, idioma, tel, tarifa);
        academia.registrarProfesor(nuevo);
    }

    public void registrarServicio(String codigo, String nombre, String desc, double precio, boolean disponible) {
        ServicioAdicional nuevo = new ServicioAdicional(codigo, nombre, desc, precio, disponible);
        academia.registrarServicio(nuevo);
    }

    public void crearYRegistrarCurso(TipoCurso tipo, String codigo, String nombre, Idioma idioma, EstadoCurso estado, String desc, int meses, double valorMes, int sesiones, double tarifaSesion, NivelReferencia nivel, String objetivos) {
        // Uso del Patrón Factory para la instanciación limpia de subclases
        Curso nuevoCurso = CursoFactory.crearCurso(tipo, codigo, nombre, idioma, estado, desc, meses, valorMes, sesiones, tarifaSesion, nivel, objetivos);
        if (nuevoCurso != null) {
            academia.registrarCurso(nuevoCurso);
        }
    }

    public List<Estudiante> obtenerEstudiantes() {
        return academia.getListEstudiantes();
    }

    public List<Profesor> obtenerProfesores() {
        return academia.getListProfesores();
    }

    public List<Curso> obtenerCursos() {
        return academia.getListCursos();
    }

    public List<ServicioAdicional> obtenerServicios() {
        return academia.getListServicios();
    }
}

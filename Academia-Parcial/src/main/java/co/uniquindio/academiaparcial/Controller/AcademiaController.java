package co.uniquindio.academiaparcial.Controller;

import co.uniquindio.academiaparcial.Factory.AcademiaAbstractFactory;
import co.uniquindio.academiaparcial.Factory.FrancesFactory;
import co.uniquindio.academiaparcial.Factory.InglesFactory;
import co.uniquindio.academiaparcial.Factory.PortuguesFactory;
import co.uniquindio.academiaparcial.Model.Academia;
import co.uniquindio.academiaparcial.Model.Curso;
import co.uniquindio.academiaparcial.Model.EstadoCurso;
import co.uniquindio.academiaparcial.Model.Estudiante;
import co.uniquindio.academiaparcial.Model.Idioma;
import co.uniquindio.academiaparcial.Model.NivelReferencia;
import co.uniquindio.academiaparcial.Model.Profesor;
import co.uniquindio.academiaparcial.Model.ServicioAdicional;
import co.uniquindio.academiaparcial.Model.TipoCurso;
import co.uniquindio.academiaparcial.Singleton.AcademiaHolder;
import java.time.LocalDate;
import java.util.List;

public class AcademiaController {

    private Academia academia;

    public AcademiaController() {
        this.academia = AcademiaHolder.getInstance().getAcademia();
    }

    public String registrarEstudiante(String doc, String nombre, String tel, String correo, int edad) {
        if (doc == null || doc.trim().equals("")) {
            return "El documento es obligatorio";
        }
        if (nombre == null || nombre.trim().equals("")) {
            return "El nombre es obligatorio";
        }
        if (edad < 5) {
            return "Edad no valida";
        }
        Estudiante nuevo = new Estudiante(doc, nombre, tel, correo, edad, LocalDate.now());
        boolean guardo = academia.registrarEstudiante(nuevo);
        if (guardo) {
            return "LISTO";
        } else {
            return "Ya existe un estudiante con ese documento";
        }
    }

    public String registrarProfesor(String id, String nombre, Idioma idioma, String tel, double tarifa) {
        if (id == null || id.trim().equals("")) {
            return "La identificacion es obligatoria";
        }
        if (nombre == null || nombre.trim().equals("")) {
            return "El nombre es obligatorio";
        }
        Profesor nuevo = new Profesor(id, nombre, idioma, tel, tarifa);
        boolean guardo = academia.registrarProfesor(nuevo);
        if (guardo) {
            return "LISTO";
        } else {
            return "Ya existe un profesor con esa identificacion";
        }
    }

    public String registrarServicio(String codigo, String nombre, String desc, double precio, boolean disponible) {
        if (codigo == null || codigo.trim().equals("")) {
            return "El codigo es obligatorio";
        }
        ServicioAdicional nuevo = new ServicioAdicional(codigo, nombre, desc, precio, disponible);
        boolean guardo = academia.registrarServicio(nuevo);
        if (guardo) {
            return "LISTO";
        } else {
            return "Ya existe un servicio con ese codigo";
        }
    }

    public String crearYRegistrarCurso(TipoCurso tipo, String codigo, String nombre, Idioma idioma, EstadoCurso estado, String desc, int meses, double valorMes, int sesiones, double tarifaSesion, NivelReferencia nivel, String objetivos) {
        if (codigo == null || codigo.trim().equals("")) {
            return "El codigo es obligatorio";
        }
        if (meses <= 0) {
            return "La duracion debe ser mayor a cero";
        }
        AcademiaAbstractFactory fabrica = getFabrica(idioma);
        Curso nuevoCurso = fabrica.crearCurso(tipo, codigo, nombre, idioma, estado, desc, meses, valorMes, sesiones, tarifaSesion, nivel, objetivos);
        if (nuevoCurso == null) {
            return "No se pudo crear el curso";
        }
        boolean guardo = academia.registrarCurso(nuevoCurso);
        if (guardo) {
            return "LISTO";
        } else {
            return "Ya existe un curso con ese codigo";
        }
    }

    private AcademiaAbstractFactory getFabrica(Idioma idioma) {
        if (idioma == Idioma.FRANCES) {
            return new FrancesFactory();
        }
        if (idioma == Idioma.PORTUGUES) {
            return new PortuguesFactory();
        }
        return new InglesFactory();
    }

    public Estudiante buscarEstudiante(String documento) {
        return academia.buscarEstudiantePorDocumento(documento);
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

    public Academia getAcademia() {
        return academia;
    }
}

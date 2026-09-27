package co.uniquindio.academiaparcial.Model;

import co.uniquindio.academiaparcial.Factory.CursoFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Academia implements ICalculadoraIngresos {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correo;
    private String sitioWeb;

    private List<Estudiante> listEstudiantes;
    private List<Profesor> listProfesores;
    private List<Curso> listCursos;
    private List<ServicioAdicional> listServicios;
    private List<Matricula> listMatriculas;

    public Academia(String nombreComercial, String nit, String direccion, String telefono, String correo, String sitioWeb) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.sitioWeb = sitioWeb;

        this.listEstudiantes = new ArrayList<>();
        this.listProfesores = new ArrayList<>();
        this.listCursos = new ArrayList<>();
        this.listServicios = new ArrayList<>();
        this.listMatriculas = new ArrayList<>();
    }

    // --- GETTERS DE ATRIBUTOS Y LISTAS ---

    public String getNombreComercial() { return nombreComercial; }
    public String getNit() { return nit; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getSitioWeb() { return sitioWeb; }

    public List<Estudiante> getListEstudiantes() { return listEstudiantes; }
    public List<Profesor> getListProfesores() { return listProfesores; }
    public List<Curso> getListCursos() { return listCursos; }
    public List<ServicioAdicional> getListServicios() { return listServicios; }
    public List<Matricula> getListMatriculas() { return listMatriculas; }

    // --- MÉTODOS DE REGISTRO (ACEPTAN OBJETOS Y TAMBIÉN PARÁMETROS INDIVIDUALES) ---

    // 1. Registro de Estudiantes
    public void registrarEstudiante(Estudiante estudiante) {
        this.listEstudiantes.add(estudiante);
    }

    public void registrarEstudiante(String doc, String nombre, String tel, String correo, int edad) {
        Estudiante nuevo = new Estudiante(doc, nombre, tel, correo, edad, LocalDate.now());
        this.listEstudiantes.add(nuevo);
    }

    // 2. Registro de Profesores
    public void registrarProfesor(Profesor profesor) {
        this.listProfesores.add(profesor);
    }

    public void registrarProfesor(String id, String nombre, Idioma idioma, String tel, double tarifa) {
        Profesor nuevo = new Profesor(id, nombre, idioma, tel, tarifa);
        this.listProfesores.add(nuevo);
    }

    // 3. Registro de Servicios Adicionales
    public void registrarServicio(ServicioAdicional servicio) {
        this.listServicios.add(servicio);
    }

    public void registrarServicio(String codigo, String nombre, String desc, double precio, boolean disponible) {
        ServicioAdicional nuevo = new ServicioAdicional(codigo, nombre, desc, precio, disponible);
        this.listServicios.add(nuevo);
    }

    // 4. Registro de Cursos
    public void registrarCurso(Curso curso) {
        this.listCursos.add(curso);
    }

    public void crearYRegistrarCurso(TipoCurso tipo, String codigo, String nombre, Idioma idioma, EstadoCurso estado, String desc, int meses, double valorMes, int sesiones, double tarifaSesion, NivelReferencia nivel, String objetivos) {
        Curso nuevoCurso = CursoFactory.crearCurso(tipo, codigo, nombre, idioma, estado, desc, meses, valorMes, sesiones, tarifaSesion, nivel, objetivos);
        if (nuevoCurso != null) {
            this.listCursos.add(nuevoCurso);
        }
    }

    // 5. Registro de Matrículas
    public void registrarMatricula(Matricula matricula) {
        this.listMatriculas.add(matricula);
    }

    // --- MÉTODOS DE BÚSQUEDA ---

    public Estudiante buscarEstudiantePorDocumento(String documento) {
        for (Estudiante e : listEstudiantes) {
            if (e.getDocumento().equals(documento)) {
                return e;
            }
        }
        return null;
    }

    public Profesor buscarProfesorPorIdentificacion(String identificacion) {
        for (Profesor p : listProfesores) {
            if (p.getIdentificacion().equals(identificacion)) {
                return p;
            }
        }
        return null;
    }

    public Curso buscarCursoPorCodigo(String codigo) {
        for (Curso c : listCursos) {
            if (c.getCodigo().equals(codigo)) {
                return c;
            }
        }
        return null;
    }

    public ServicioAdicional buscarServicioPorCodigo(String codigo) {
        for (ServicioAdicional s : listServicios) {
            if (s.getCodigo().equals(codigo)) {
                return s;
            }
        }
        return null;
    }

    // --- CÁLCULO DE INGRESOS (INTERFAZ ICalculadoraIngresos) ---


    public  double calcularIngresosEntre(LocalDate fInicio, LocalDate fFin) {
        double totalIngresos = 0;
        for (Matricula m : listMatriculas) {
            LocalDate fecha = m.getFechaMatricula();
            if ((fecha.isEqual(fInicio) || fecha.isAfter(fInicio)) && (fecha.isEqual(fFin) || fecha.isBefore(fFin))) {
                totalIngresos += m.calcularValorTotal();
            }
        }
        return totalIngresos;
    }
}
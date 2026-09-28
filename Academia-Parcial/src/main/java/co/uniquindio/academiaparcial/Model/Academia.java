package co.uniquindio.academiaparcial.Model;

import co.uniquindio.academiaparcial.Factory.CursoFactory;
import co.uniquindio.academiaparcial.Factory.ServicioFactory;
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
        this.listEstudiantes = new ArrayList<Estudiante>();
        this.listProfesores = new ArrayList<Profesor>();
        this.listCursos = new ArrayList<Curso>();
        this.listServicios = new ArrayList<ServicioAdicional>();
        this.listMatriculas = new ArrayList<Matricula>();
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public List<Estudiante> getListEstudiantes() {
        return listEstudiantes;
    }

    public List<Profesor> getListProfesores() {
        return listProfesores;
    }

    public List<Curso> getListCursos() {
        return listCursos;
    }

    public List<ServicioAdicional> getListServicios() {
        return listServicios;
    }

    public List<Matricula> getListMatriculas() {
        return listMatriculas;
    }

    public void cargarDatosIniciales() {
        Estudiante e1 = new Estudiante("1001", "Ana Gomez", "3101112233", "ana@mail.com", 20, LocalDate.now());
        Estudiante e2 = new Estudiante("1002", "Luis Perez", "3104445566", "luis@mail.com", 25, LocalDate.now());
        listEstudiantes.add(e1);
        listEstudiantes.add(e2);

        Profesor p1 = new Profesor("P01", "Marta Ruiz", Idioma.INGLES, "3201112233", 40000);
        Profesor p2 = new Profesor("P02", "Jean Paul", Idioma.FRANCES, "3204445566", 45000);
        Profesor p3 = new Profesor("P03", "Carlos Silva", Idioma.PORTUGUES, "3207778899", 38000);
        listProfesores.add(p1);
        listProfesores.add(p2);
        listProfesores.add(p3);

        Curso c1 = CursoFactory.crearCurso(TipoCurso.REGULAR, "ING-R1", "Ingles Basico", Idioma.INGLES, EstadoCurso.ACTIVO, "Curso regular de ingles", 4, 120000, 0, 0, NivelReferencia.A1, "");
        Curso c2 = CursoFactory.crearCurso(TipoCurso.INTENSIVO, "ING-I1", "Ingles Intensivo", Idioma.INGLES, EstadoCurso.ACTIVO, "Curso intensivo", 2, 180000, 0, 0, NivelReferencia.A2, "");
        Curso c3 = CursoFactory.crearCurso(TipoCurso.PERSONALIZADO, "FRA-P1", "Frances a medida", Idioma.FRANCES, EstadoCurso.ACTIVO, "Curso personalizado", 3, 150000, 10, 40000, NivelReferencia.B1, "Viajar a Francia");
        listCursos.add(c1);
        listCursos.add(c2);
        listCursos.add(c3);

        listServicios.add(ServicioFactory.crearSimulacro("S01", 80000));
        listServicios.add(ServicioFactory.crearTutoria("S02", 50000));
        listServicios.add(ServicioFactory.crearMaterial("S03", 30000));
        listServicios.add(ServicioFactory.crearTaller("S04", 60000));
    }

    public boolean registrarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            return false;
        }
        if (buscarEstudiantePorDocumento(estudiante.getDocumento()) != null) {
            return false;
        }
        listEstudiantes.add(estudiante);
        return true;
    }

    public void registrarEstudiante(String doc, String nombre, String tel, String correo, int edad) {
        Estudiante nuevo = new Estudiante(doc, nombre, tel, correo, edad, LocalDate.now());
        registrarEstudiante(nuevo);
    }

    public boolean registrarProfesor(Profesor profesor) {
        if (profesor == null) {
            return false;
        }
        if (buscarProfesorPorIdentificacion(profesor.getIdentificacion()) != null) {
            return false;
        }
        listProfesores.add(profesor);
        return true;
    }

    public void registrarProfesor(String id, String nombre, Idioma idioma, String tel, double tarifa) {
        Profesor nuevo = new Profesor(id, nombre, idioma, tel, tarifa);
        registrarProfesor(nuevo);
    }

    public boolean registrarServicio(ServicioAdicional servicio) {
        if (servicio == null) {
            return false;
        }
        if (buscarServicioPorCodigo(servicio.getCodigo()) != null) {
            return false;
        }
        listServicios.add(servicio);
        return true;
    }

    public void registrarServicio(String codigo, String nombre, String desc, double precio, boolean disponible) {
        ServicioAdicional nuevo = new ServicioAdicional(codigo, nombre, desc, precio, disponible);
        registrarServicio(nuevo);
    }

    public boolean registrarCurso(Curso curso) {
        if (curso == null) {
            return false;
        }
        if (buscarCursoPorCodigo(curso.getCodigo()) != null) {
            return false;
        }
        listCursos.add(curso);
        return true;
    }

    public void crearYRegistrarCurso(TipoCurso tipo, String codigo, String nombre, Idioma idioma, EstadoCurso estado, String desc, int meses, double valorMes, int sesiones, double tarifaSesion, NivelReferencia nivel, String objetivos) {
        Curso nuevoCurso = CursoFactory.crearCurso(tipo, codigo, nombre, idioma, estado, desc, meses, valorMes, sesiones, tarifaSesion, nivel, objetivos);
        if (nuevoCurso != null) {
            registrarCurso(nuevoCurso);
        }
    }

    public boolean registrarMatricula(Matricula matricula) {
        if (matricula == null) {
            return false;
        }
        listMatriculas.add(matricula);
        return true;
    }

    public Estudiante buscarEstudiantePorDocumento(String documento) {
        Estudiante encontrado = null;
        for (int i = 0; i < listEstudiantes.size(); i++) {
            Estudiante e = listEstudiantes.get(i);
            if (e.getDocumento().equals(documento)) {
                encontrado = e;
            }
        }
        return encontrado;
    }

    public Profesor buscarProfesorPorIdentificacion(String identificacion) {
        Profesor encontrado = null;
        for (int i = 0; i < listProfesores.size(); i++) {
            Profesor p = listProfesores.get(i);
            if (p.getIdentificacion().equals(identificacion)) {
                encontrado = p;
            }
        }
        return encontrado;
    }

    public Curso buscarCursoPorCodigo(String codigo) {
        Curso encontrado = null;
        for (int i = 0; i < listCursos.size(); i++) {
            Curso c = listCursos.get(i);
            if (c.getCodigo().equals(codigo)) {
                encontrado = c;
            }
        }
        return encontrado;
    }

    public ServicioAdicional buscarServicioPorCodigo(String codigo) {
        ServicioAdicional encontrado = null;
        for (int i = 0; i < listServicios.size(); i++) {
            ServicioAdicional s = listServicios.get(i);
            if (s.getCodigo().equals(codigo)) {
                encontrado = s;
            }
        }
        return encontrado;
    }

    public double calcularIngresosEntre(LocalDate fechaInicio, LocalDate fechaFin) {
        double totalIngresos = 0;
        for (int i = 0; i < listMatriculas.size(); i++) {
            Matricula m = listMatriculas.get(i);
            LocalDate fecha = m.getFechaMatricula();
            boolean despuesDeInicio = fecha.isEqual(fechaInicio) || fecha.isAfter(fechaInicio);
            boolean antesDeFin = fecha.isEqual(fechaFin) || fecha.isBefore(fechaFin);
            if (despuesDeInicio && antesDeFin) {
                totalIngresos = totalIngresos + m.calcularValorTotal();
            }
        }
        return totalIngresos;
    }
}

package co.uniquindio.academiaparcial.Model;

import java.util.ArrayList;
import java.util.List;

public class Academia {
    private String nombreComercial;
    private String nit;
    private String direccion;
    private String telefono;
    private String correo;
    private String sitioWeb;

    private  List<Estudiante> listestudiantes;
    private  List<Profesor> listprofesores;
    private  List<Curso> listcursos;
    private  List<ServicioAdicional> listservicios;
    private List<Matricula> listmatriculas;

    public Academia(String nombreComercial, String nit, String direccion,
                    String telefono, String correo, String sitioWeb) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.sitioWeb = sitioWeb;
        this.listestudiantes = new ArrayList<>();
        this.listprofesores = new ArrayList<>();
        this.listcursos = new ArrayList<>();
        this.listservicios = new ArrayList<>();
        this.listmatriculas = new ArrayList<>();
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public List<Estudiante> getEstudiantes() {
        return listestudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.listestudiantes = estudiantes;
    }

    public List<Profesor> getProfesores() {
        return listprofesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.listprofesores = profesores;
    }

    public List<Curso> getCursos() {
        return listcursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.listcursos = cursos;
    }

    public List<ServicioAdicional> getServicios() {
        return listservicios;
    }

    public void setServicios(List<ServicioAdicional> servicios) {
        this.listservicios = servicios;
    }

    public List<Matricula> getMatriculas() {
        return listmatriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.listmatriculas = matriculas;
    }
}

package co.uniquindio.academiaparcial.Model;

// Matricula.java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Matricula {
    private String id;
    private LocalDate fechaMatricula;
    private double porcentajeDescuento;
    private Estudiante estudiante;
    private Profesor profesor;
    private Curso curso;
    private List<ServicioAdicional> listServicios;

    public Matricula(String id, LocalDate fechaMatricula, double porcentajeDescuento, Estudiante estudiante, Profesor profesor, Curso curso) {
        this.id = id;
        this.fechaMatricula = fechaMatricula;
        this.porcentajeDescuento = porcentajeDescuento;
        this.estudiante = estudiante;
        this.profesor = profesor;
        this.curso = curso;
        this.listServicios = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public Curso getCurso() {
        return curso;
    }

    public List<ServicioAdicional> getListServicios() {
        return listServicios;
    }

    public void agregarServicio(ServicioAdicional servicio) {
        this.listServicios.add(servicio);
    }

    public double calcularValorTotal() {
        double total = curso.calcularCostoBase();

        for (ServicioAdicional s : listServicios) {
            total += s.getPrecio();
        }

        if (porcentajeDescuento > 0) {
            total = total - (total * (porcentajeDescuento / 100));
        }

        return total;
    }
}
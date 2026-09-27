package co.uniquindio.academiaparcial.Builder;

// MatriculaBuilder.java (Patrón Builder)
import co.uniquindio.academiaparcial.Model.Curso;
import co.uniquindio.academiaparcial.Model.Estudiante;
import co.uniquindio.academiaparcial.Model.Matricula;
import co.uniquindio.academiaparcial.Model.Profesor;

import java.time.LocalDate;

public class MatriculaBuilder {
    private String id;
    private LocalDate fechaMatricula;
    private double porcentajeDescuento;
    private Estudiante estudiante;
    private Profesor profesor;
    private Curso curso;

    public MatriculaBuilder conId(String id) {
        this.id = id;
        return this;
    }

    public MatriculaBuilder conFecha(LocalDate fecha) {
        this.fechaMatricula = fecha;
        return this;
    }

    public MatriculaBuilder conDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
        return this;
    }

    public MatriculaBuilder conEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        return this;
    }

    public MatriculaBuilder conProfesor(Profesor profesor) {
        this.profesor = profesor;
        return this;
    }

    public MatriculaBuilder conCurso(Curso curso) {
        this.curso = curso;
        return this;
    }

    public Matricula build() {
        return new Matricula(id, fechaMatricula, porcentajeDescuento, estudiante, profesor, curso);
    }
}
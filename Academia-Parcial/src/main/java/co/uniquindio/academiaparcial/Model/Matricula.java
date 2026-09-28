package co.uniquindio.academiaparcial.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Matricula {

    private final String id;
    private final LocalDate fechaMatricula;
    private final double descuento;
    private final Estudiante estudiante;
    private final Profesor profesor;
    private final Curso curso;
    private final List<ServicioAdicional> serviciosAdicionales;

    private Matricula(Builder builder) {
        this.id = builder.id;
        this.fechaMatricula = builder.fechaMatricula;
        this.descuento = builder.descuento;
        this.estudiante = builder.estudiante;
        this.profesor = builder.profesor;
        this.curso = builder.curso;
        this.serviciosAdicionales = builder.serviciosAdicionales;
    }

    public String getId() {
        return id;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public double getDescuento() {
        return descuento;
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

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public double calcularValorTotal() {
        double total = 0;
        if (curso != null) {
            total = curso.calcularCostoBase();
        }
        if (serviciosAdicionales != null) {
            for (int i = 0; i < serviciosAdicionales.size(); i++) {
                ServicioAdicional s = serviciosAdicionales.get(i);
                if (s != null && s.isDisponible()) {
                    total = total + s.getPrecio();
                }
            }
        }
        if (descuento > 0) {
            if (descuento <= 100) {
                double valorDescuento = total * (descuento / 100.0);
                total = total - valorDescuento;
            } else {
                total = total - descuento;
            }
        }
        if (total < 0) {
            total = 0;
        }
        return total;
    }

    public String toString() {
        String nombreEst = "Sin estudiante";
        if (estudiante != null) {
            nombreEst = estudiante.getNombreCompleto();
        }
        String nombreCurso = "Sin curso";
        if (curso != null) {
            nombreCurso = curso.getNombre();
        }
        return "Matricula " + id + " | " + nombreEst + " | " + nombreCurso + " | $" + calcularValorTotal();
    }

    public static class Builder {
        private String id;
        private LocalDate fechaMatricula = LocalDate.now();
        private double descuento;
        private Estudiante estudiante;
        private Profesor profesor;
        private Curso curso;
        private List<ServicioAdicional> serviciosAdicionales = new ArrayList<ServicioAdicional>();

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder fechaMatricula(LocalDate fechaMatricula) {
            this.fechaMatricula = fechaMatricula;
            return this;
        }

        public Builder descuento(double descuento) {
            this.descuento = descuento;
            return this;
        }

        public Builder estudiante(Estudiante estudiante) {
            this.estudiante = estudiante;
            return this;
        }

        public Builder profesor(Profesor profesor) {
            this.profesor = profesor;
            return this;
        }

        public Builder curso(Curso curso) {
            this.curso = curso;
            return this;
        }

        public Builder agregarServicio(ServicioAdicional servicio) {
            if (this.serviciosAdicionales == null) {
                this.serviciosAdicionales = new ArrayList<ServicioAdicional>();
            }
            this.serviciosAdicionales.add(servicio);
            return this;
        }

        public Matricula build() {
            return new Matricula(this);
        }
    }
}

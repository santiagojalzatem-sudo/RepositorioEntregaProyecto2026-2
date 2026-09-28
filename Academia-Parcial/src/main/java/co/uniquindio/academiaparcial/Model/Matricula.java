package co.uniquindio.academiaparcial.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Matricula {

    // 1. Atributos inmutables de la Matrícula
    private final String id;
    private final LocalDate fechaMatricula;
    private final double descuento;
    private final Estudiante estudiante;
    private final Profesor profesor;
    private final Curso curso;
    private final List<ServicioAdicional> serviciosAdicionales;

    // 2. Constructor PRIVADO: solo la clase interna Builder puede llamarlo
    private Matricula(Builder builder) {
        this.id = builder.id;
        this.fechaMatricula = builder.fechaMatricula;
        this.descuento = builder.descuento;
        this.estudiante = builder.estudiante;
        this.profesor = builder.profesor;
        this.curso = builder.curso;
        this.serviciosAdicionales = builder.serviciosAdicionales;
    }

    // Getters y métodos de negocio (como calcularValorTotal)
    public String getId() { return id; }
    public double getDescuento() { return descuento; }
    // ... otros getters ...

    // 3. CLASE INTERNA ESTÁTICA BUILDER (Exactamente como en Casa)
    public static class Builder {
        private String id;
        private LocalDate fechaMatricula = LocalDate.now();
        private double descuento;
        private Estudiante estudiante;
        private Profesor profesor;
        private Curso curso;
        private List<ServicioAdicional> serviciosAdicionales = new ArrayList<>();

        // Métodos de encadenamiento que retornan 'this'
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
                this.serviciosAdicionales = new ArrayList<>();
            }
            this.serviciosAdicionales.add(servicio);
            return this;
        }

        // Método final que entrega el objeto Matricula construido
        public Matricula build() {
            return new Matricula(this);
        }
    }
}
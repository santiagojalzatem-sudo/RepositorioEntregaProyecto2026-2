package co.uniquindio.academiaparcial.Controller;

import co.uniquindio.academiaparcial.Builder.MatriculaBuilder;
import co.uniquindio.academiaparcial.Model.*;
import co.uniquindio.academiaparcial.Singleton.AcademiaHolder;

import java.time.LocalDate;
import java.util.List;

public class MatriculaController {
    private Academia academia;

    public MatriculaController() {
        this.academia = AcademiaHolder.getInstance().getAcademia();
    }

    public boolean realizarMatricula(String idMatricula, double descuento, String docEstudiante, String idProfesor, String codCurso, List<String> codServicios) {
        Estudiante e = academia.buscarEstudiantePorDocumento(docEstudiante);
        Profesor p = academia.buscarProfesorPorIdentificacion(idProfesor);
        Curso c = academia.buscarCursoPorCodigo(codCurso);

        // Validación de existencia de componentes
        if (e == null || c == null) {
            return false;
        }

        // Construcción de la matrícula con el Patrón Builder
        Matricula nuevaMatricula = new MatriculaBuilder()
                .conId(idMatricula)
                .conFecha(LocalDate.now())
                .conDescuento(descuento)
                .conEstudiante(e)
                .conProfesor(p)
                .conCurso(c)
                .build();

        // Agregar los servicios adicionales seleccionados
        if (codServicios != null) {
            for (String codServ : codServicios) {
                ServicioAdicional s = academia.buscarServicioPorCodigo(codServ);
                if (s != null) {
                    nuevaMatricula.agregarServicio(s);
                }
            }
        }

        academia.registrarMatricula(nuevaMatricula);
        return true;
    }

    public double consultarIngresos(LocalDate fechaInicio, LocalDate fechaFin) {
        // Implementación directa del contrato de la interfaz ICalculadoraIngresos
        return academia.calcularIngresosEntre(fechaInicio, fechaFin);
    }

    public List<Matricula> obtenerMatriculas() {
        return academia.getListMatriculas();
    }
}


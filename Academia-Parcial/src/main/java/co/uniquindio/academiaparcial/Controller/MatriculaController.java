package co.uniquindio.academiaparcial.Controller;


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

        // Validación de existencia de componentes obligatorios
        if (e == null || c == null) {
            return false;
        }

        // 1. Instanciamos e iniciamos la configuración del Builder
        Matricula.Builder builder = new Matricula.Builder()
                .id(idMatricula)
                .fechaMatricula(LocalDate.now())
                .descuento(descuento)
                .estudiante(e)
                .profesor(p)
                .curso(c);

        // 2. Agregamos los servicios adicionales AL BUILDER antes de construir la matrícula
        if (codServicios != null) {
            for (String codServ : codServicios) {
                ServicioAdicional s = academia.buscarServicioPorCodigo(codServ);
                if (s != null) {
                    builder.agregarServicio(s); // Método ejecutado sobre el Builder
                }
            }
        }

        // 3. Construimos el objeto Matricula final
        Matricula nuevaMatricula = builder.build();

        // 4. Registramos la matrícula en la academia
        academia.registrarMatricula(nuevaMatricula);
        return true;
    }
}
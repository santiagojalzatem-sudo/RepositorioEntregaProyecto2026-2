package co.uniquindio.academiaparcial.Controller;

import co.uniquindio.academiaparcial.Model.Academia;
import co.uniquindio.academiaparcial.Model.Curso;
import co.uniquindio.academiaparcial.Model.Estudiante;
import co.uniquindio.academiaparcial.Model.Matricula;
import co.uniquindio.academiaparcial.Model.Profesor;
import co.uniquindio.academiaparcial.Model.ServicioAdicional;
import co.uniquindio.academiaparcial.Singleton.AcademiaHolder;
import java.time.LocalDate;
import java.util.List;

public class MatriculaController {

    private Academia academia;

    public MatriculaController() {
        this.academia = AcademiaHolder.getInstance().getAcademia();
    }

    public String realizarMatricula(String idMatricula, double descuento, String docEstudiante, String idProfesor, String codCurso, List<String> codServicios) {
        if (idMatricula == null || idMatricula.trim().equals("")) {
            return "El id de la matricula es obligatorio";
        }
        Estudiante e = academia.buscarEstudiantePorDocumento(docEstudiante);
        if (e == null) {
            return "No se encontro el estudiante";
        }
        Curso c = academia.buscarCursoPorCodigo(codCurso);
        if (c == null) {
            return "No se encontro el curso";
        }
        Profesor p = null;
        if (idProfesor != null && !idProfesor.trim().equals("")) {
            p = academia.buscarProfesorPorIdentificacion(idProfesor);
        }

        Matricula.Builder builder = new Matricula.Builder();
        builder.id(idMatricula);
        builder.fechaMatricula(LocalDate.now());
        builder.descuento(descuento);
        builder.estudiante(e);
        builder.profesor(p);
        builder.curso(c.clonar());

        if (codServicios != null) {
            for (int i = 0; i < codServicios.size(); i++) {
                String cod = codServicios.get(i);
                ServicioAdicional s = academia.buscarServicioPorCodigo(cod);
                if (s != null) {
                    builder.agregarServicio(s.clonar());
                }
            }
        }

        Matricula nueva = builder.build();
        academia.registrarMatricula(nueva);
        return "LISTO";
    }

    public double consultarIngresos(LocalDate inicio, LocalDate fin) {
        double total = academia.calcularIngresosEntre(inicio, fin);
        return total;
    }

    public List<Matricula> obtenerMatriculas() {
        return academia.getListMatriculas();
    }
}

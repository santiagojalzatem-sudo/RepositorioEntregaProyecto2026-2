package co.uniquindio.academiaparcial.Controller;

import co.uniquindio.academiaparcial.Model.Academia;
import co.uniquindio.academiaparcial.Model.Estudiante;
import co.uniquindio.academiaparcial.Model.Matricula;
import co.uniquindio.academiaparcial.Singleton.AcademiaHolder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReporteController {

    private Academia academia;

    public ReporteController() {
        this.academia = AcademiaHolder.getInstance().getAcademia();
    }

    public Estudiante buscarEstudiantePorDocumento(String documento) {
        if (documento == null) {
            return null;
        }
        return academia.buscarEstudiantePorDocumento(documento);
    }

    public double calcularIngresos(LocalDate inicio, LocalDate fin) {
        if (inicio == null || fin == null) {
            return 0;
        }
        return academia.calcularIngresosEntre(inicio, fin);
    }

    public List<Matricula> matriculasEntre(LocalDate inicio, LocalDate fin) {
        List<Matricula> resultado = new ArrayList<Matricula>();
        List<Matricula> todas = academia.getListMatriculas();
        for (int i = 0; i < todas.size(); i++) {
            Matricula m = todas.get(i);
            LocalDate fecha = m.getFechaMatricula();
            boolean cumpleInicio = fecha.isEqual(inicio) || fecha.isAfter(inicio);
            boolean cumpleFin = fecha.isEqual(fin) || fecha.isBefore(fin);
            if (cumpleInicio && cumpleFin) {
                resultado.add(m);
            }
        }
        return resultado;
    }
}

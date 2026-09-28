package co.uniquindio.academiaparcial.Model;

import java.time.LocalDate;

public interface ICalculadoraIngresos {

    double calcularIngresosEntre(LocalDate fechaInicio, LocalDate fechaFin);
}

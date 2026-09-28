package co.uniquindio.academiaparcial.Factory;

import co.uniquindio.academiaparcial.Model.ServicioAdicional;

public class ServicioFactory {

    public static ServicioAdicional crearServicio(String codigo, String nombre, String descripcion, double precio) {
        ServicioAdicional servicio = new ServicioAdicional(codigo, nombre, descripcion, precio, true);
        return servicio;
    }

    public static ServicioAdicional crearSimulacro(String codigo, double precio) {
        return new ServicioAdicional(codigo, "Simulacro de examen", "Simulacro de certificacion", precio, true);
    }

    public static ServicioAdicional crearTutoria(String codigo, double precio) {
        return new ServicioAdicional(codigo, "Tutoria de refuerzo", "Tutoria personal con profesor", precio, true);
    }

    public static ServicioAdicional crearMaterial(String codigo, double precio) {
        return new ServicioAdicional(codigo, "Material impreso", "Cartilla y guias impresas", precio, true);
    }

    public static ServicioAdicional crearTaller(String codigo, double precio) {
        return new ServicioAdicional(codigo, "Taller de conversacion", "Taller grupal de conversacion", precio, true);
    }
}

package co.uniquindio.academiaparcial.Singleton;

import co.uniquindio.academiaparcial.Model.Academia;

public class AcademiaHolder {

    private static AcademiaHolder instance;
    private Academia academia;

    private AcademiaHolder() {
        academia = new Academia("LenguajeCafetero", "800123456-1", "Calle 10 #15-20 Armenia", "3001234567", "info@lenguajecafetero.com", "www.lenguajecafetero.com");
        academia.cargarDatosIniciales();
    }

    public static AcademiaHolder getInstance() {
        if (instance == null) {
            instance = new AcademiaHolder();
        }
        return instance;
    }

    public Academia getAcademia() {
        return academia;
    }
}

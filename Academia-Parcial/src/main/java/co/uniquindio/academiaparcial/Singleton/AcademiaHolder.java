package co.uniquindio.academiaparcial.Singleton;

import co.uniquindio.academiaparcial.Model.Academia;

public class AcademiaHolder {
    // AcademiaHolder.java (Patrón Singleton)

        private static AcademiaHolder instance;

        private Academia academia;

        private AcademiaHolder() {
            this.academia = new Academia("LenguajeCafetero", "800123456-1", "Calle 10 #15-20", "3001234567", "info@cafetero.com", "www.cafetero.com");
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

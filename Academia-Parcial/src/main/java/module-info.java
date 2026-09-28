module co.uniquindio.academiaparcial {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.uniquindio.academiaparcial to javafx.fxml;
    exports co.uniquindio.academiaparcial;
    exports co.uniquindio.academiaparcial.Model;
    exports co.uniquindio.academiaparcial.Controller;
    exports co.uniquindio.academiaparcial.View;
    exports co.uniquindio.academiaparcial.Factory;
    exports co.uniquindio.academiaparcial.Singleton;
    exports co.uniquindio.academiaparcial.Prototype;
}

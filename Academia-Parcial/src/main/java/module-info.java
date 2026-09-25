module co.uniquindio.academiaparcial {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.uniquindio.academiaparcial to javafx.fxml;
    exports co.uniquindio.academiaparcial;
}
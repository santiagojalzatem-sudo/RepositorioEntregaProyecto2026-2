module co.uniquindio.parcial3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.uniquindio.parcial3 to javafx.fxml;
    exports co.uniquindio.parcial3;
}
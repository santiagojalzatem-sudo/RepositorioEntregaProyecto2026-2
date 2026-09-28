package co.uniquindio.academiaparcial;

import co.uniquindio.academiaparcial.View.AcademiaView;
import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        AcademiaView vista = new AcademiaView();
        vista.mostrar(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}


package co.uniquindio.academiaparcial.View;

import co.uniquindio.academiaparcial.Controller.AcademiaController;
import co.uniquindio.academiaparcial.Controller.MatriculaController;
import co.uniquindio.academiaparcial.Model.Matricula;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InicioPanel extends VBox {

    private AcademiaController academiaController;
    private MatriculaController matriculaController;
    private Label lblEst;
    private Label lblProf;
    private Label lblCur;
    private Label lblMat;
    private Label lblIngresos;
    private ListView<String> lista;

    public InicioPanel(AcademiaController ac, MatriculaController mc) {
        this.academiaController = ac;
        this.matriculaController = mc;
        setSpacing(12);
        setPadding(new Insets(16));
        setStyle("-fx-background-color: white;");

        Label titulo = new Label("Bienvenido a LenguajeCafetero");
        titulo.getStyleClass().add("panel-title");
        Label sub = new Label("Resumen general. Usa las pestanas superiores para gestionar todo.");
        sub.setStyle("-fx-text-fill: #64748b;");

        GridPane tarjetas = new GridPane();
        tarjetas.setHgap(10);
        tarjetas.setVgap(10);

        VBox c1 = tarjeta("Estudiantes", "#0ea5e9");
        lblEst = (Label) c1.getChildren().get(1);
        VBox c2 = tarjeta("Profesores", "#22c55e");
        lblProf = (Label) c2.getChildren().get(1);
        VBox c3 = tarjeta("Cursos", "#f59e0b");
        lblCur = (Label) c3.getChildren().get(1);
        VBox c4 = tarjeta("Matriculas", "#a855f7");
        lblMat = (Label) c4.getChildren().get(1);

        tarjetas.add(c1, 0, 0);
        tarjetas.add(c2, 1, 0);
        tarjetas.add(c3, 2, 0);
        tarjetas.add(c4, 3, 0);

        lblIngresos = new Label("Ingresos totales: $0");
        lblIngresos.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;"
                + "-fx-background-color: #0f172a; -fx-text-fill: white;"
                + "-fx-padding: 10 14 10 14; -fx-background-radius: 10;");

        Label t2 = new Label("Ultimas matriculas");
        t2.setStyle("-fx-font-weight: bold;");
        lista = new ListView<String>();
        lista.setPrefHeight(220);

        HBox fila = new HBox(10, lblIngresos);
        getChildren().addAll(titulo, sub, tarjetas, fila, t2, lista);
        refrescar();
    }

    private VBox tarjeta(String nombre, String color) {
        VBox v = new VBox(4);
        v.setPadding(new Insets(12));
        v.setPrefWidth(170);
        v.setStyle("-fx-background-color: #f8fafc; -fx-border-color: #e2e8f0;"
                + "-fx-border-radius: 10; -fx-background-radius: 10;");
        Label n = new Label(nombre);
        n.setStyle("-fx-text-fill: #64748b; -fx-font-weight: bold; -fx-font-size: 12px;");
        Label val = new Label("0");
        val.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: " + color + ";");
        v.getChildren().addAll(n, val);
        return v;
    }

    public void refrescar() {
        lblEst.setText(String.valueOf(academiaController.obtenerEstudiantes().size()));
        lblProf.setText(String.valueOf(academiaController.obtenerProfesores().size()));
        lblCur.setText(String.valueOf(academiaController.obtenerCursos().size()));
        List<Matricula> mats = matriculaController.obtenerMatriculas();
        lblMat.setText(String.valueOf(mats.size()));
        double total = 0;
        for (int i = 0; i < mats.size(); i++) {
            total = total + mats.get(i).calcularValorTotal();
        }
        lblIngresos.setText("Ingresos totales: $" + total);
        lista.getItems().clear();
        int desde = Math.max(0, mats.size() - 10);
        for (int i = desde; i < mats.size(); i++) {
            lista.getItems().add(mats.get(i).toString());
        }
    }
}

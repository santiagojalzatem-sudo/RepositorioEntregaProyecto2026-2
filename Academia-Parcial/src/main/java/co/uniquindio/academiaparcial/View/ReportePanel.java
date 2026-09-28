package co.uniquindio.academiaparcial.View;

import co.uniquindio.academiaparcial.Controller.ReporteController;
import co.uniquindio.academiaparcial.Model.Estudiante;
import co.uniquindio.academiaparcial.Model.Matricula;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ReportePanel extends VBox {

    private ReporteController controller;
    private TextField txtDoc;
    private Label lblEstudiante;
    private DatePicker dpInicio;
    private DatePicker dpFin;
    private Label lblTotal;
    private ListView<String> lista;

    public ReportePanel(ReporteController controller) {
        this.controller = controller;
        setSpacing(8);
        setPadding(new Insets(10));

        Label titulo = new Label("Consultas y reportes");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(6);

        txtDoc = new TextField();
        lblEstudiante = new Label("");
        dpInicio = new DatePicker(LocalDate.now().minusMonths(1));
        dpFin = new DatePicker(LocalDate.now());
        lblTotal = new Label("Total: $0");
        lista = new ListView<String>();

        Button btnBuscar = new Button("Buscar estudiante");
        Button btnIngresos = new Button("Calcular ingresos");

        btnBuscar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                buscarEstudiante();
            }
        });
        btnIngresos.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                calcular();
            }
        });

        form.add(new Label("Documento estudiante:"), 0, 0);
        form.add(txtDoc, 1, 0);
        form.add(btnBuscar, 1, 1);
        form.add(lblEstudiante, 1, 2);
        form.add(new Label("Fecha inicio:"), 0, 3);
        form.add(dpInicio, 1, 3);
        form.add(new Label("Fecha fin:"), 0, 4);
        form.add(dpFin, 1, 4);
        form.add(btnIngresos, 1, 5);
        form.add(lblTotal, 1, 6);

        getChildren().add(titulo);
        getChildren().add(form);
        getChildren().add(new Label("Matriculas en el periodo:"));
        getChildren().add(lista);
    }

    private void buscarEstudiante() {
        String doc = txtDoc.getText();
        Estudiante e = controller.buscarEstudiantePorDocumento(doc);
        if (e == null) {
            lblEstudiante.setText("No se encontro el estudiante.");
        } else {
            lblEstudiante.setText(e.getDocumento() + " - " + e.getNombreCompleto() + " - " + e.getTelefono() + " - " + e.getCorreo());
        }
    }

    private void calcular() {
        LocalDate inicio = dpInicio.getValue();
        LocalDate fin = dpFin.getValue();
        if (inicio == null || fin == null) {
            lblTotal.setText("Seleccione las dos fechas.");
            return;
        }
        double total = controller.calcularIngresos(inicio, fin);
        lblTotal.setText("Total entre " + inicio + " y " + fin + ": $" + total);
        lista.getItems().clear();
        List<Matricula> mats = controller.matriculasEntre(inicio, fin);
        for (int i = 0; i < mats.size(); i++) {
            Matricula m = mats.get(i);
            lista.getItems().add(m.toString());
        }
    }
}

package co.uniquindio.academiaparcial.View;

import co.uniquindio.academiaparcial.Controller.AcademiaController;
import co.uniquindio.academiaparcial.Model.Idioma;
import co.uniquindio.academiaparcial.Model.Profesor;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProfesorPanel extends VBox {

    private AcademiaController controller;
    private TextField txtId;
    private TextField txtNombre;
    private TextField txtTel;
    private TextField txtTarifa;
    private ComboBox<Idioma> cbIdioma;
    private Label lblMsg;
    private ListView<String> lista;

    public ProfesorPanel(AcademiaController controller) {
        this.controller = controller;
        setSpacing(8);
        setPadding(new Insets(10));

        Label titulo = new Label("Registro de profesores");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(6);

        txtId = new TextField();
        txtNombre = new TextField();
        txtTel = new TextField();
        txtTarifa = new TextField();
        cbIdioma = new ComboBox<Idioma>();
        cbIdioma.getItems().addAll(Idioma.values());
        cbIdioma.setValue(Idioma.INGLES);

        form.add(new Label("Identificacion:"), 0, 0);
        form.add(txtId, 1, 0);
        form.add(new Label("Nombre:"), 0, 1);
        form.add(txtNombre, 1, 1);
        form.add(new Label("Idioma:"), 0, 2);
        form.add(cbIdioma, 1, 2);
        form.add(new Label("Telefono:"), 0, 3);
        form.add(txtTel, 1, 3);
        form.add(new Label("Tarifa sesion:"), 0, 4);
        form.add(txtTarifa, 1, 4);

        Button btnGuardar = new Button("Registrar");
        lblMsg = new Label("");
        lista = new ListView<String>();

        btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                guardar();
            }
        });

        getChildren().add(titulo);
        getChildren().add(form);
        getChildren().add(btnGuardar);
        getChildren().add(lblMsg);
        getChildren().add(lista);
        refrescar();
    }

    private void guardar() {
        try {
            String id = txtId.getText();
            String nombre = txtNombre.getText();
            Idioma idioma = cbIdioma.getValue();
            String tel = txtTel.getText();
            double tarifa = Double.parseDouble(txtTarifa.getText());
            String r = controller.registrarProfesor(id, nombre, idioma, tel, tarifa);
            if (r.equals("LISTO")) {
                lblMsg.setText("Profesor guardado.");
                txtId.setText("");
                txtNombre.setText("");
                txtTel.setText("");
                txtTarifa.setText("");
            } else {
                lblMsg.setText(r);
            }
        } catch (NumberFormatException ex) {
            lblMsg.setText("La tarifa debe ser un numero.");
        }
        refrescar();
    }

    private void refrescar() {
        lista.getItems().clear();
        List<Profesor> datos = controller.obtenerProfesores();
        for (int i = 0; i < datos.size(); i++) {
            Profesor p = datos.get(i);
            lista.getItems().add(p.getIdentificacion() + " - " + p.getNombre() + " - " + p.getIdioma() + " - $" + p.getTarifaPorSesion());
        }
    }
}

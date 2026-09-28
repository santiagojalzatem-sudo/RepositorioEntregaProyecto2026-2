package co.uniquindio.academiaparcial.View;

import co.uniquindio.academiaparcial.Controller.AcademiaController;
import co.uniquindio.academiaparcial.Model.Estudiante;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class EstudiantePanel extends VBox {

    private AcademiaController controller;
    private TextField txtDoc;
    private TextField txtNombre;
    private TextField txtTel;
    private TextField txtCorreo;
    private TextField txtEdad;
    private TextField txtBuscar;
    private Label lblMsg;
    private ListView<String> lista;

    public EstudiantePanel(AcademiaController controller) {
        this.controller = controller;
        setSpacing(8);
        setPadding(new Insets(10));

        Label titulo = new Label("Registro de estudiantes");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(6);

        txtDoc = new TextField();
        txtNombre = new TextField();
        txtTel = new TextField();
        txtCorreo = new TextField();
        txtEdad = new TextField();
        txtBuscar = new TextField();
        txtBuscar.setPromptText("Documento para buscar");

        form.add(new Label("Documento:"), 0, 0);
        form.add(txtDoc, 1, 0);
        form.add(new Label("Nombre completo:"), 0, 1);
        form.add(txtNombre, 1, 1);
        form.add(new Label("Telefono:"), 0, 2);
        form.add(txtTel, 1, 2);
        form.add(new Label("Correo:"), 0, 3);
        form.add(txtCorreo, 1, 3);
        form.add(new Label("Edad:"), 0, 4);
        form.add(txtEdad, 1, 4);

        Button btnGuardar = new Button("Registrar");
        Button btnBuscar = new Button("Buscar por documento");
        Button btnListar = new Button("Listar");
        lblMsg = new Label("");
        lista = new ListView<String>();

        btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                guardar();
            }
        });
        btnBuscar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                buscar();
            }
        });
        btnListar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                refrescar();
            }
        });

        getChildren().add(titulo);
        getChildren().add(form);
        getChildren().add(btnGuardar);
        getChildren().add(txtBuscar);
        getChildren().add(btnBuscar);
        getChildren().add(btnListar);
        getChildren().add(lblMsg);
        getChildren().add(lista);
        refrescar();
    }

    private void guardar() {
        try {
            String doc = txtDoc.getText();
            String nombre = txtNombre.getText();
            String tel = txtTel.getText();
            String correo = txtCorreo.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            String r = controller.registrarEstudiante(doc, nombre, tel, correo, edad);
            if (r.equals("LISTO")) {
                lblMsg.setText("Estudiante guardado.");
                txtDoc.setText("");
                txtNombre.setText("");
                txtTel.setText("");
                txtCorreo.setText("");
                txtEdad.setText("");
            } else {
                lblMsg.setText(r);
            }
        } catch (NumberFormatException ex) {
            lblMsg.setText("La edad debe ser un numero.");
        }
        refrescar();
    }

    private void buscar() {
        String doc = txtBuscar.getText();
        Estudiante e = controller.buscarEstudiante(doc);
        lista.getItems().clear();
        if (e == null) {
            lblMsg.setText("No se encontro el estudiante.");
        } else {
            lblMsg.setText("Estudiante encontrado.");
            lista.getItems().add(e.getDocumento() + " - " + e.getNombreCompleto() + " - " + e.getTelefono() + " - " + e.getCorreo() + " - " + e.getEdad());
        }
    }

    private void refrescar() {
        lista.getItems().clear();
        List<Estudiante> datos = controller.obtenerEstudiantes();
        for (int i = 0; i < datos.size(); i++) {
            Estudiante e = datos.get(i);
            lista.getItems().add(e.getDocumento() + " - " + e.getNombreCompleto() + " - " + e.getTelefono());
        }
    }
}

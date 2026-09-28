package co.uniquindio.academiaparcial.View;

import co.uniquindio.academiaparcial.Controller.AcademiaController;
import co.uniquindio.academiaparcial.Model.ServicioAdicional;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ServicioPanel extends VBox {

    private AcademiaController controller;
    private TextField txtCodigo;
    private TextField txtNombre;
    private TextField txtDesc;
    private TextField txtPrecio;
    private CheckBox chkDisponible;
    private Label lblMsg;
    private ListView<String> lista;

    public ServicioPanel(AcademiaController controller) {
        this.controller = controller;
        setSpacing(8);
        setPadding(new Insets(10));

        Label titulo = new Label("Servicios adicionales");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(6);

        txtCodigo = new TextField();
        txtNombre = new TextField();
        txtDesc = new TextField();
        txtPrecio = new TextField();
        chkDisponible = new CheckBox("Disponible");
        chkDisponible.setSelected(true);

        form.add(new Label("Codigo:"), 0, 0);
        form.add(txtCodigo, 1, 0);
        form.add(new Label("Nombre:"), 0, 1);
        form.add(txtNombre, 1, 1);
        form.add(new Label("Descripcion:"), 0, 2);
        form.add(txtDesc, 1, 2);
        form.add(new Label("Precio:"), 0, 3);
        form.add(txtPrecio, 1, 3);
        form.add(chkDisponible, 1, 4);

        Button btnGuardar = new Button("Registrar servicio");
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
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String desc = txtDesc.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            boolean disp = chkDisponible.isSelected();
            String r = controller.registrarServicio(codigo, nombre, desc, precio, disp);
            if (r.equals("LISTO")) {
                lblMsg.setText("Servicio guardado.");
                txtCodigo.setText("");
                txtNombre.setText("");
                txtDesc.setText("");
                txtPrecio.setText("");
            } else {
                lblMsg.setText(r);
            }
        } catch (NumberFormatException ex) {
            lblMsg.setText("El precio debe ser un numero.");
        }
        refrescar();
    }

    private void refrescar() {
        lista.getItems().clear();
        List<ServicioAdicional> datos = controller.obtenerServicios();
        for (int i = 0; i < datos.size(); i++) {
            ServicioAdicional s = datos.get(i);
            lista.getItems().add(s.getCodigo() + " - " + s.getNombre() + " - $" + s.getPrecio());
        }
    }

    public void refrescarLista() {
        refrescar();
    }
}

package co.uniquindio.academiaparcial.View;

import co.uniquindio.academiaparcial.Controller.AcademiaController;
import co.uniquindio.academiaparcial.Model.Curso;
import co.uniquindio.academiaparcial.Model.EstadoCurso;
import co.uniquindio.academiaparcial.Model.Idioma;
import co.uniquindio.academiaparcial.Model.NivelReferencia;
import co.uniquindio.academiaparcial.Model.TipoCurso;
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

public class CursoPanel extends VBox {

    private AcademiaController controller;
    private ComboBox<TipoCurso> cbTipo;
    private TextField txtCodigo;
    private TextField txtNombre;
    private ComboBox<Idioma> cbIdioma;
    private ComboBox<EstadoCurso> cbEstado;
    private TextField txtDesc;
    private TextField txtMeses;
    private TextField txtValor;
    private TextField txtSesiones;
    private TextField txtTarifaSesion;
    private ComboBox<NivelReferencia> cbNivel;
    private TextField txtObjetivos;
    private Label lblMsg;
    private ListView<String> lista;

    public CursoPanel(AcademiaController controller) {
        this.controller = controller;
        setSpacing(8);
        setPadding(new Insets(10));

        Label titulo = new Label("Registro de cursos");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(6);

        cbTipo = new ComboBox<TipoCurso>();
        cbTipo.getItems().addAll(TipoCurso.values());
        cbTipo.setValue(TipoCurso.REGULAR);
        txtCodigo = new TextField();
        txtNombre = new TextField();
        cbIdioma = new ComboBox<Idioma>();
        cbIdioma.getItems().addAll(Idioma.values());
        cbIdioma.setValue(Idioma.INGLES);
        cbEstado = new ComboBox<EstadoCurso>();
        cbEstado.getItems().addAll(EstadoCurso.values());
        cbEstado.setValue(EstadoCurso.ACTIVO);
        txtDesc = new TextField();
        txtMeses = new TextField();
        txtValor = new TextField();
        txtSesiones = new TextField("0");
        txtTarifaSesion = new TextField("0");
        cbNivel = new ComboBox<NivelReferencia>();
        cbNivel.getItems().addAll(NivelReferencia.values());
        cbNivel.setValue(NivelReferencia.A1);
        txtObjetivos = new TextField();

        int fila = 0;
        form.add(new Label("Tipo:"), 0, fila);
        form.add(cbTipo, 1, fila);
        fila++;
        form.add(new Label("Codigo:"), 0, fila);
        form.add(txtCodigo, 1, fila);
        fila++;
        form.add(new Label("Nombre:"), 0, fila);
        form.add(txtNombre, 1, fila);
        fila++;
        form.add(new Label("Idioma:"), 0, fila);
        form.add(cbIdioma, 1, fila);
        fila++;
        form.add(new Label("Estado:"), 0, fila);
        form.add(cbEstado, 1, fila);
        fila++;
        form.add(new Label("Descripcion:"), 0, fila);
        form.add(txtDesc, 1, fila);
        fila++;
        form.add(new Label("Meses:"), 0, fila);
        form.add(txtMeses, 1, fila);
        fila++;
        form.add(new Label("Valor mensual:"), 0, fila);
        form.add(txtValor, 1, fila);
        fila++;
        form.add(new Label("Sesiones (personalizado):"), 0, fila);
        form.add(txtSesiones, 1, fila);
        fila++;
        form.add(new Label("Tarifa sesion:"), 0, fila);
        form.add(txtTarifaSesion, 1, fila);
        fila++;
        form.add(new Label("Nivel:"), 0, fila);
        form.add(cbNivel, 1, fila);
        fila++;
        form.add(new Label("Objetivos:"), 0, fila);
        form.add(txtObjetivos, 1, fila);

        Button btnGuardar = new Button("Registrar curso");
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
            TipoCurso tipo = cbTipo.getValue();
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            Idioma idioma = cbIdioma.getValue();
            EstadoCurso estado = cbEstado.getValue();
            String desc = txtDesc.getText();
            int meses = Integer.parseInt(txtMeses.getText());
            double valor = Double.parseDouble(txtValor.getText());
            int sesiones = Integer.parseInt(txtSesiones.getText());
            double tarifaSesion = Double.parseDouble(txtTarifaSesion.getText());
            NivelReferencia nivel = cbNivel.getValue();
            String objetivos = txtObjetivos.getText();
            String r = controller.crearYRegistrarCurso(tipo, codigo, nombre, idioma, estado, desc, meses, valor, sesiones, tarifaSesion, nivel, objetivos);
            if (r.equals("LISTO")) {
                lblMsg.setText("Curso guardado.");
                txtCodigo.setText("");
                txtNombre.setText("");
            } else {
                lblMsg.setText(r);
            }
        } catch (NumberFormatException ex) {
            lblMsg.setText("Revise los numeros: meses, valor, sesiones y tarifa.");
        }
        refrescar();
    }

    private void refrescar() {
        lista.getItems().clear();
        List<Curso> datos = controller.obtenerCursos();
        for (int i = 0; i < datos.size(); i++) {
            Curso c = datos.get(i);
            lista.getItems().add(c.getCodigo() + " - " + c.getNombre() + " - " + c.getIdioma() + " - $" + c.calcularCostoBase());
        }
    }

    public void refrescarLista() {
        refrescar();
    }
}

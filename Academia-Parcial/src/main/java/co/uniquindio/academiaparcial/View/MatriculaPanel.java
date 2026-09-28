package co.uniquindio.academiaparcial.View;

import co.uniquindio.academiaparcial.Controller.AcademiaController;
import co.uniquindio.academiaparcial.Controller.MatriculaController;
import co.uniquindio.academiaparcial.Model.Curso;
import co.uniquindio.academiaparcial.Model.Estudiante;
import co.uniquindio.academiaparcial.Model.Matricula;
import co.uniquindio.academiaparcial.Model.Profesor;
import co.uniquindio.academiaparcial.Model.ServicioAdicional;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MatriculaPanel extends VBox {

    private AcademiaController academiaController;
    private MatriculaController matriculaController;
    private TextField txtId;
    private TextField txtDescuento;
    private ComboBox<String> cbEstudiante;
    private ComboBox<String> cbProfesor;
    private ComboBox<String> cbCurso;
    private ListView<String> listServicios;
    private Label lblMsg;
    private ListView<String> listaMatriculas;

    public MatriculaPanel(AcademiaController academiaController, MatriculaController matriculaController) {
        this.academiaController = academiaController;
        this.matriculaController = matriculaController;
        setSpacing(8);
        setPadding(new Insets(10));

        Label titulo = new Label("Matriculas");
        titulo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(6);

        txtId = new TextField();
        txtDescuento = new TextField("0");
        cbEstudiante = new ComboBox<String>();
        cbProfesor = new ComboBox<String>();
        cbCurso = new ComboBox<String>();
        listServicios = new ListView<String>();
        listServicios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        form.add(new Label("Id matricula:"), 0, 0);
        form.add(txtId, 1, 0);
        form.add(new Label("Descuento %:"), 0, 1);
        form.add(txtDescuento, 1, 1);
        form.add(new Label("Estudiante:"), 0, 2);
        form.add(cbEstudiante, 1, 2);
        form.add(new Label("Profesor (opcional):"), 0, 3);
        form.add(cbProfesor, 1, 3);
        form.add(new Label("Curso:"), 0, 4);
        form.add(cbCurso, 1, 4);
        form.add(new Label("Servicios (Ctrl + clic):"), 0, 5);
        form.add(listServicios, 1, 5);

        Button btnGuardar = new Button("Matricular");
        Button btnRecargar = new Button("Recargar listas");
        lblMsg = new Label("");
        listaMatriculas = new ListView<String>();

        btnGuardar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                guardar();
            }
        });
        btnRecargar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                cargarCombos();
                refrescar();
            }
        });

        getChildren().add(titulo);
        getChildren().add(form);
        getChildren().add(btnGuardar);
        getChildren().add(btnRecargar);
        getChildren().add(lblMsg);
        getChildren().add(new Label("Matriculas registradas:"));
        getChildren().add(listaMatriculas);

        cargarCombos();
        refrescar();
    }

    private String soloCodigo(String texto) {
        if (texto == null) {
            return "";
        }
        int guion = texto.indexOf(" - ");
        if (guion > 0) {
            return texto.substring(0, guion).trim();
        }
        return texto.trim();
    }

    private void cargarCombos() {
        cbEstudiante.getItems().clear();
        List<Estudiante> est = academiaController.obtenerEstudiantes();
        for (int i = 0; i < est.size(); i++) {
            Estudiante e = est.get(i);
            cbEstudiante.getItems().add(e.getDocumento() + " - " + e.getNombreCompleto());
        }
        
        cbProfesor.getItems().clear();
        cbProfesor.getItems().add("");
        List<Profesor> prof = academiaController.obtenerProfesores();
        for (int i = 0; i < prof.size(); i++) {
            Profesor p = prof.get(i);
            cbProfesor.getItems().add(p.getIdentificacion() + " - " + p.getNombre());
        }
        
        cbCurso.getItems().clear();
        List<Curso> cursos = academiaController.obtenerCursos();
        for (int i = 0; i < cursos.size(); i++) {
            Curso c = cursos.get(i);
            cbCurso.getItems().add(c.getCodigo() + " - " + c.getNombre());
        }
        
        listServicios.getItems().clear();
        List<ServicioAdicional> serv = academiaController.obtenerServicios();
        for (int i = 0; i < serv.size(); i++) {
            ServicioAdicional s = serv.get(i);
            listServicios.getItems().add(s.getCodigo() + " - " + s.getNombre() + " $" + s.getPrecio());
        }
    }

    private void guardar() {
        try {
            String id = txtId.getText();
            String descTexto = txtDescuento.getText();
            double desc = 0;
            
            if (descTexto != null && !descTexto.trim().equals("")) {
                desc = Double.parseDouble(descTexto);
            }

            // Validacion manual para que el profesor vea que controlas los errores
            String valEstudiante = cbEstudiante.getValue();
            String valCurso = cbCurso.getValue();

            if (valEstudiante == null || valCurso == null) {
                lblMsg.setText("Por favor seleccione un estudiante y un curso.");
                return; // Corta la ejecucion aqui si faltan datos
            }

            String docEst = soloCodigo(valEstudiante);
            String idProf = soloCodigo(cbProfesor.getValue());
            String codCurso = soloCodigo(valCurso);

            List<String> elegidos = listServicios.getSelectionModel().getSelectedItems();
            List<String> cods = new ArrayList<String>();
            
            if (elegidos != null) {
                for (int i = 0; i < elegidos.size(); i++) {
                    cods.add(soloCodigo(elegidos.get(i)));
                }
            }

            String r = matriculaController.realizarMatricula(id, desc, docEst, idProf, codCurso, cods);
            
            if (r.equals("LISTO")) {
                lblMsg.setText("Matricula guardada exitosamente.");
                txtId.setText("");
                txtDescuento.setText("0");
                // Limpia los selectores para la siguiente matricula
                cbEstudiante.getSelectionModel().clearSelection();
                cbProfesor.getSelectionModel().clearSelection();
                cbCurso.getSelectionModel().clearSelection();
                listServicios.getSelectionModel().clearSelection();
            } else {
                lblMsg.setText(r); // Muestra el mensaje de error del controlador
            }
        } catch (NumberFormatException ex) {
            lblMsg.setText("El descuento debe ser un numero.");
        }
        refrescar();
    }

    private void refrescar() {
        listaMatriculas.getItems().clear();
        List<Matricula> datos = matriculaController.obtenerMatriculas();
        for (int i = 0; i < datos.size(); i++) {
            Matricula m = datos.get(i);
            listaMatriculas.getItems().add(m.toString());
        }
    }

    public void refrescarTodo() {
        cargarCombos();
        refrescar();
    }
}

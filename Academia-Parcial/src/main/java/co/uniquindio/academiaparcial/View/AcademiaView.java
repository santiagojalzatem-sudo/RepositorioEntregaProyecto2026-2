package co.uniquindio.academiaparcial.View;

import co.uniquindio.academiaparcial.Controller.AcademiaController;
import co.uniquindio.academiaparcial.Controller.MatriculaController;
import co.uniquindio.academiaparcial.Controller.ReporteController;
import co.uniquindio.academiaparcial.Singleton.AcademiaHolder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AcademiaView {

    private AcademiaController academiaController;
    private MatriculaController matriculaController;
    private ReporteController reporteController;

    public AcademiaView() {
        academiaController = new AcademiaController();
        matriculaController = new MatriculaController();
        reporteController = new ReporteController();
    }

    public void mostrar(Stage stage) {
        BorderPane raiz = new BorderPane();
        raiz.getStyleClass().add("root");

        String nit = AcademiaHolder.getInstance().getAcademia().getNit();
        String nombre = AcademiaHolder.getInstance().getAcademia().getNombreComercial();

        VBox textos = new VBox(2);
        Label encabezado = new Label("☕ Academia " + nombre);
        encabezado.getStyleClass().add("header-title");
        Label sub = new Label("Gestión de estudiantes, cursos y matrículas  •  NIT " + nit);
        sub.getStyleClass().add("header-sub");
        textos.getChildren().addAll(encabezado, sub);

        Label fecha = new Label(java.time.LocalDate.now().toString());
        fecha.getStyleClass().add("header-badge");

        HBox espacio = new HBox();
        HBox.setHgrow(espacio, Priority.ALWAYS);

        HBox top = new HBox(12, textos, espacio, fecha);
        top.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        top.getStyleClass().add("header");

        TabPane tabs = new TabPane();

        EstudiantePanel pEst = new EstudiantePanel(academiaController);
        ProfesorPanel pProf = new ProfesorPanel(academiaController);
        CursoPanel pCurso = new CursoPanel(academiaController);
        ServicioPanel pServ = new ServicioPanel(academiaController);
        MatriculaPanel pMat = new MatriculaPanel(academiaController, matriculaController);
        ReportePanel pRep = new ReportePanel(reporteController);

        InicioPanel pInicio = new InicioPanel(academiaController, matriculaController);

        Tab t0 = new Tab("🏠 Inicio", pInicio);
        Tab t1 = new Tab("🎓 Estudiantes", envolver(pEst));
        Tab t2 = new Tab("👩‍🏫 Profesores", envolver(pProf));
        Tab t3 = new Tab("📚 Cursos", envolver(pCurso));
        Tab t4 = new Tab("✨ Servicios", envolver(pServ));
        Tab t5 = new Tab("📝 Matrículas", envolver(pMat));
        Tab t6 = new Tab("📊 Consultas", envolver(pRep));
        t0.setClosable(false);
        t1.setClosable(false);
        t2.setClosable(false);
        t3.setClosable(false);
        t4.setClosable(false);
        t5.setClosable(false);
        t6.setClosable(false);

        tabs.getTabs().add(t0);
        tabs.getTabs().add(t1);
        tabs.getTabs().add(t2);
        tabs.getTabs().add(t3);
        tabs.getTabs().add(t4);
        tabs.getTabs().add(t5);
        tabs.getTabs().add(t6);

        Label lblEstado = new Label();
        lblEstado.getStyleClass().add("footer-label");
        HBox footer = new HBox(lblEstado);
        footer.getStyleClass().add("footer");

        Runnable actualizarEstado = () -> {
            int nEst = academiaController.obtenerEstudiantes().size();
            int nProf = academiaController.obtenerProfesores().size();
            int nCur = academiaController.obtenerCursos().size();
            int nMat = matriculaController.obtenerMatriculas().size();
            lblEstado.setText("👥 " + nEst + " estudiantes   •   👩‍🏫 " + nProf
                    + " profesores   •   📚 " + nCur + " cursos   •   📝 " + nMat + " matrículas");
        };
        actualizarEstado.run();

        tabs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            public void changed(ObservableValue<? extends Tab> obs, Tab antes, Tab ahora) {
                pMat.refrescarTodo();
                pCurso.refrescarLista();
                pServ.refrescarLista();
                pInicio.refrescar();
                actualizarEstado.run();
            }
        });

        raiz.setTop(top);
        raiz.setCenter(tabs);
        raiz.setBottom(footer);

        Scene scene = new Scene(raiz, 1020, 680);
        try {
            String css = getClass().getResource("/co/uniquindio/academiaparcial/app.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch (Exception ex) {
            System.out.println("No se pudo cargar el CSS: " + ex.getMessage());
        }
        stage.setTitle("Academia LenguajeCafetero");
        stage.setScene(scene);
        stage.show();
    }

    private javafx.scene.control.ScrollPane envolver(VBox panel) {
        panel.setStyle("-fx-background-color: white;");
        panel.setPadding(new Insets(16));
        javafx.scene.control.ScrollPane sp = new javafx.scene.control.ScrollPane(panel);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        return sp;
    }
}

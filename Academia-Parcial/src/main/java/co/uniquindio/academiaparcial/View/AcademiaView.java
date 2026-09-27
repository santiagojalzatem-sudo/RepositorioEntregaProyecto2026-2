package co.uniquindio.academiaparcial.View;

import co.uniquindio.academiaparcial.Controller.AcademiaController;
import co.uniquindio.academiaparcial.Controller.MatriculaController;
import co.uniquindio.academiaparcial.Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AcademiaView {
    private AcademiaController academiaController;
    private MatriculaController matriculaController;
    private Scanner scanner;

    public AcademiaView() {
        this.academiaController = new AcademiaController();
        this.matriculaController = new MatriculaController();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenuPrincipal();
            System.out.print("Seleccione una opción: ");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el salto de línea
                procesarOpcion(opcion);
            } else {
                System.out.println("Opción inválida. Intente de nuevo.");
                scanner.nextLine();
            }
        }
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n===== ACADEMIA DE IDIOMAS LENGUAJECAFETERIA =====");
        System.out.println("1. Registrar Estudiante");
        System.out.println("2. Registrar Profesor");
        System.out.println("3. Registrar Curso (Regular / Intensivo / Personalizado)");
        System.out.println("4. Registrar Servicio Adicional");
        System.out.println("5. Crear Matrícula");
        System.out.println("6. Consultar Total de Ingresos por Rango de Fechas");
        System.out.println("7. Listar Matrículas");
        System.out.println("0. Salir");
        System.out.println("=================================================");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> vistaRegistrarEstudiante();
            case 2 -> vistaRegistrarProfesor();
            case 3 -> vistaRegistrarCurso();
            case 4 -> vistaRegistrarServicio();
            case 5 -> vistaCrearMatricula();
            case 6 -> vistaConsultarIngresos();
            case 7 -> vistaListarMatriculas();
            case 0 -> System.out.println("Saliendo del sistema...");
            default -> System.out.println("Opción no válida.");
        }
    }

    private void vistaRegistrarEstudiante() {
        System.out.println("\n--- Registro de Estudiante ---");
        System.out.print("Documento: ");
        String doc = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Teléfono: ");
        String tel = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();

        academiaController.registrarEstudiante(doc, nombre, tel, correo, edad);
        System.out.println("¡Estudiante registrado exitosamente!");
    }

    private void vistaRegistrarProfesor() {
        System.out.println("\n--- Registro de Profesor ---");
        System.out.print("Identificación: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.println("Idioma (1. INGLES, 2. FRANCES, 3. PORTUGUES): ");
        int optIdioma = scanner.nextInt();
        scanner.nextLine();
        Idioma idioma = Idioma.values()[Math.max(0, Math.min(optIdioma - 1, 2))];

        System.out.print("Teléfono: ");
        String tel = scanner.nextLine();
        System.out.print("Tarifa por sesión: ");
        double tarifa = scanner.nextDouble();
        scanner.nextLine();

        academiaController.registrarProfesor(id, nombre, idioma, tel, tarifa);
        System.out.println("¡Profesor registrado exitosamente!");
    }

    private void vistaRegistrarCurso() {
        System.out.println("\n--- Registro de Curso ---");
        System.out.println("Tipo de curso (1. REGULAR, 2. INTENSIVO, 3. PERSONALIZADO): ");
        int optTipo = scanner.nextInt();
        scanner.nextLine();
        TipoCurso tipo = TipoCurso.values()[Math.max(0, Math.min(optTipo - 1, 2))];

        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Idioma (1. INGLES, 2. FRANCES, 3. PORTUGUES): ");
        int optIdioma = scanner.nextInt();
        scanner.nextLine();
        Idioma idioma = Idioma.values()[Math.max(0, Math.min(optIdioma - 1, 2))];

        System.out.print("Descripción: ");
        String desc = scanner.nextLine();
        System.out.print("Duración en meses: ");
        int meses = scanner.nextInt();
        System.out.print("Valor mensualidad: ");
        double valorMes = scanner.nextDouble();
        scanner.nextLine();

        int sesiones = 0;
        double tarifaSesion = 0.0;
        NivelReferencia nivel = NivelReferencia.A1;
        String objetivos = "";

        if (tipo == TipoCurso.PERSONALIZADO) {
            System.out.print("Número de sesiones: ");
            sesiones = scanner.nextInt();
            System.out.print("Tarifa referencia por sesión: ");
            tarifaSesion = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Nivel Referencia (1. A1, 2. A2, 3. B1, 4. B2, 5. C1, 6. C2): ");
            int optNivel = scanner.nextInt();
            scanner.nextLine();
            nivel = NivelReferencia.values()[Math.max(0, Math.min(optNivel - 1, 5))];
            System.out.print("Objetivos del curso: ");
            objetivos = scanner.nextLine();
        }

        academiaController.crearYRegistrarCurso(tipo, codigo, nombre, idioma, EstadoCurso.ACTIVO, desc, meses, valorMes, sesiones, tarifaSesion, nivel, objetivos);
        System.out.println("¡Curso registrado exitosamente con el Factory!");
    }

    private void vistaRegistrarServicio() {
        System.out.println("\n--- Registro de Servicio Adicional ---");
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String desc = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine();

        academiaController.registrarServicio(codigo, nombre, desc, precio, true);
        System.out.println("¡Servicio Adicional registrado!");
    }

    private void vistaCrearMatricula() {
        System.out.println("\n--- Creación de Matrícula ---");
        System.out.print("ID Matrícula: ");
        String id = scanner.nextLine();
        System.out.print("Porcentaje de descuento (0 si no aplica): ");
        double descuento = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Documento del Estudiante: ");
        String docEstudiante = scanner.nextLine();

        System.out.print("ID del Profesor asignado (Enter para omitir): ");
        String idProfesor = scanner.nextLine();

        System.out.print("Código del Curso: ");
        String codCurso = scanner.nextLine();

        List<String> codServicios = new ArrayList<>();
        System.out.print("¿Desea agregar un código de Servicio Adicional? (S/N): ");
        String resp = scanner.nextLine();
        while (resp.equalsIgnoreCase("S")) {
            System.out.print("Código de Servicio: ");
            codServicios.add(scanner.nextLine());
            System.out.print("¿Desea agregar otro servicio? (S/N): ");
            resp = scanner.nextLine();
        }

        boolean exito = matriculaController.realizarMatricula(id, descuento, docEstudiante, idProfesor, codCurso, codServicios);
        if (exito) {
            System.out.println("¡Matrícula realizada exitosamente usando el Builder!");
        } else {
            System.out.println("Error: Estudiante o Curso no encontrados.");
        }
    }

    private void vistaConsultarIngresos() {
        System.out.println("\n--- Consulta de Ingresos ---");
        System.out.print("Año inicio (ej. 2026): ");
        int a1 = scanner.nextInt();
        System.out.print("Mes inicio (1-12): ");
        int m1 = scanner.nextInt();
        System.out.print("Día inicio: ");
        int d1 = scanner.nextInt();

        System.out.print("Año fin (ej. 2026): ");
        int a2 = scanner.nextInt();
        System.out.print("Mes fin (1-12): ");
        int m2 = scanner.nextInt();
        System.out.print("Día fin: ");
        int d2 = scanner.nextInt();
        scanner.nextLine();

        LocalDate fInicio = LocalDate.of(a1, m1, d1);
        LocalDate fFin = LocalDate.of(a2, m2, d2);

        double total = matriculaController.consultarIngresos(fInicio, fFin);
        System.out.println("El total de ingresos registrados entre " + fInicio + " y " + fFin + " es: $" + total);
    }

    private void vistaListarMatriculas() {
        System.out.println("\n--- Lista de Matrículas Registradas ---");
        List<Matricula> matriculas = matriculaController.obtenerMatriculas();
        if (matriculas.isEmpty()) {
            System.out.println("No hay matrículas registradas.");
        } else {
            for (Matricula m : matriculas) {
                System.out.println("ID: " + m.getId() +
                        " | Fecha: " + m.getFechaMatricula() +
                        " | Estudiante: " + m.getEstudiante().getNombreCompleto() +
                        " | Curso: " + m.getCurso().getNombre() +
                        " | Valor Total: $" + m.calcularValorTotal());
            }
        }
    }
}

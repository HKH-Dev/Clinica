package co.edu.uniquindio.clinica;

import co.edu.uniquindio.clinica.model.Clinica;
import co.edu.uniquindio.clinica.model.factory.TipoSuscripcion;
import co.edu.uniquindio.clinica.model.servicio.ComplejidadServicios;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ClinicaApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        inicializarDatosClinica();

        // Cargar la interfaz gráfica
        FXMLLoader loader = new FXMLLoader(ClinicaApplication.class.getResource("/panel.fxml")); // Corrige la ruta si es necesario
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Clinica");
//        stage.setMaximized(true);
        stage.show();

    }

    private void inicializarDatosClinica() {
        try {
            Clinica clinica = Clinica.getInstance();

            // Agregar pacientes por defecto
            clinica.registrarPaciente("123", "Juan Pérez", "555-1234", "juan@example.com", TipoSuscripcion.BASICA);
            clinica.registrarPaciente("987654321", "Maria Garcia", "555-5678", "maria@example.com", TipoSuscripcion.PREMIUM);
            clinica.registrarPaciente("456123789", "Carlos Sanchez", "555-4321", "carlos@example.com", TipoSuscripcion.BASICA);
            clinica.registrarPaciente("321987654", "Ana López", "555-8765", "ana@example.com", TipoSuscripcion.PREMIUM);
            clinica.registrarPaciente("654789123", "Pedro Díaz", "555-9876", "pedro@example.com", TipoSuscripcion.BASICA);
            clinica.registrarPaciente("789456123", "Luisa Hernández", "555-6543", "luisa@example.com", TipoSuscripcion.PREMIUM);

            // Agregar servicios por defecto
            clinica.generarServicio("001","Consulta General", 50.00, ComplejidadServicios.BAJA);
            clinica.generarServicio("002","Radiografía", 100.00, ComplejidadServicios.MEDIA);
            clinica.generarServicio("003","Examen de Sangre", 30.00, ComplejidadServicios.ALTA);
            clinica.generarServicio("004","Consulta Especialista", 150.00, ComplejidadServicios.ALTA);
            clinica.generarServicio("005","Cirugía Menor", 500.00, ComplejidadServicios.ALTA);
            clinica.generarServicio("006","Terapia Física", 80.00, ComplejidadServicios.MEDIA);
            System.out.println("Servicios generados: " + clinica.getListaServiciosDisponibles().size());

            }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(ClinicaApplication.class, args);
    }
}





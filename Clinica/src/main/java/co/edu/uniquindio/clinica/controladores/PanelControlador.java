package co.edu.uniquindio.clinica.controladores;
import co.edu.uniquindio.clinica.model.ClinicaPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class PanelControlador {
    @FXML
    private StackPane panelPrincipal;
    private final ClinicaPrincipal clinica;


    public PanelControlador() {
        this.clinica = new ClinicaPrincipal(); // Se crea una única instancia de la clase Clinica
    }


    public void mostrarRegistroPaciente(ActionEvent actionEvent) {
        Parent node = cargarPanel("/registroPaciente.fxml");


        // Se reemplaza el contenido del panel principal
        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarListaPacientes(ActionEvent actionEvent) {
        Parent node = cargarPanel("/listaPacientes.fxml");


        // Se reemplaza el contenido del panel principal
        panelPrincipal.getChildren().setAll(node);
    }


    public void mostrarRegistroCita(ActionEvent actionEvent) {
        //Completar
    }


    public void mostrarListaCitas(ActionEvent actionEvent) {
        //Completar
    }


    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();
            ((AbstractControlador)loader.getController()).inicializarClinica(clinica);
            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}



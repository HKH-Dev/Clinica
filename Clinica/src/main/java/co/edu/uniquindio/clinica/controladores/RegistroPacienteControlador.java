package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.model.Clinica;
import co.edu.uniquindio.clinica.model.factory.TipoSuscripcion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistroPacienteControlador extends AbstractControlador implements Initializable{
    @FXML
    private StackPane panelPrincipal;
    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<String> txtSuscripcion;
    private final Clinica clinica;

    public RegistroPacienteControlador() {
        clinica = new Clinica();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("RegistroPacienteControlador.initialize");
        txtSuscripcion.setItems(FXCollections.observableArrayList());
    }

    public void registrarPaciente(ActionEvent e) {
       try{
           clinica.registrarPaciente(txtCedula.getText(), txtNombre.getText(), txtTelefono.getText(), txtEmail.getText(), TipoSuscripcion.valueOf(txtSuscripcion.getValue()));
       }catch(Exception ex){
           mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);}
    }

//    @FXML
//    public void cancelarRegistro() {
//        System.out.println("RegistroPacienteControlador.cancelarRegistro");
//    }

    @Override
    public void inicializarClinica(Clinica clinica) {
        super.inicializarClinica(clinica);
        System.out.println("RegistroPacienteControlador.inicializarClinica");
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }


}

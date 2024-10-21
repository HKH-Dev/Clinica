package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.model.Clinica;
import co.edu.uniquindio.clinica.model.Paciente;
import co.edu.uniquindio.clinica.model.factory.TipoSuscripcion;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    @FXML
    private Button btnNuevoPaciente;

    private final Clinica clinica;


    public RegistroPacienteControlador() {
        clinica = Clinica.getInstance();
    }
    private  List<Node> camposDatos = new ArrayList<>();



    private boolean verificarCamposLlenos() throws Exception{
        for (Node nodo: camposDatos){
            if (nodo instanceof TextField && ((TextField) nodo).getText().isEmpty()||
                    nodo instanceof ComboBox && ((ComboBox<?>) nodo).getValue() == null){
                throw new Exception("Todos los campos deben estar llenos");
            }
        }
    return true;
    }

    private boolean verficarIdNoRegistrado(String id) throws Exception{
        for (Paciente pacientico : clinica.getListaPacientes()){
            if(pacientico.getCedula().equals(id)){
                throw new Exception("El paciente ya se encuentra registrado");
            }
        }
        return true;
    }
    public void registrarPaciente(ActionEvent evento) {

        try {if (verificarCamposLlenos() && verficarIdNoRegistrado(txtCedula.getText())){
            clinica.registrarPaciente(txtCedula.getText(), txtNombre.getText(), txtTelefono.getText(), txtEmail.getText(), TipoSuscripcion.valueOf(txtSuscripcion.getValue()));
            limpiarCampos();
            mostrarAlerta("Paciente registrado correctamente", Alert.AlertType.INFORMATION);}
        } catch (Exception ex) {
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        camposDatos.add(txtCedula);
        camposDatos.add(txtTelefono);
        camposDatos.add(txtSuscripcion);
        camposDatos.add(txtCedula);
        camposDatos.add(txtEmail);
        txtSuscripcion.setItems(FXCollections.observableArrayList(TipoSuscripcion.valueOf("BASICA").toString(), TipoSuscripcion.valueOf("PREMIUM").toString()));
    }



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

    private void limpiarCampos(){
        txtCedula.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtSuscripcion.setValue(null);
    }

}

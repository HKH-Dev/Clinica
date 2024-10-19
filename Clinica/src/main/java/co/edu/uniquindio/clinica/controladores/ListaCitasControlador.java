package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.model.Clinica;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ListaCitasControlador {
//        extends AbstractControlador implements Initializable {
    @FXML
    private StackPane CitasPanel;
    @FXML
//    private TextField txtId;
//    @FXML
//    private TextField txtPaciente;
//    @FXML
//    private DatePicker txtFecha;
//    @FXML
//    private ComboBox txtServicio;
//    @FXML
//    private TextField txtFactura;
//
//    private final Clinica clinica;
//
//    public ListaCitasControlador() {
//        clinica = new Clinica();
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        System.out.println("ListaCitasControlador.initialize");
//        txtServicio.setItems(FXCollections.observableArrayList(clinica.getListaServiciosDisponibles()));
//    }
//    public void registrarCita() {
//        try {
//            clinica.generarCita(txtId.getText(), txtFecha.getValue(), txtPaciente.getText(), txtServicio.getValue().toString(), txtFactura.getText());
//            limpiarCampos();
//            mostrarAlerta("Cita registrada correctamente", Alert.AlertType.INFORMATION);
//        } catch (Exception ex) {
//            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
//        }
//    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

//    private void limpiarCampos(){
//        txtPaciente.clear();
//        txtFecha.setValue(null);
//        txtServicio.setValue(null);
//        txtFactura.clear();
//
//    }



}

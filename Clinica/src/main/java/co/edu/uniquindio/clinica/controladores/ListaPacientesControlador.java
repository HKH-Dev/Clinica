package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.model.Clinica;
import co.edu.uniquindio.clinica.model.Paciente;
import co.edu.uniquindio.clinica.model.factory.TipoSuscripcion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ListaPacientesControlador extends AbstractControlador implements Initializable {
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
    private TableView<Paciente> tablaPacientes;
    @FXML
    private TableColumn<Paciente, String> colCedula;
    @FXML
    private TableColumn<Paciente, String> colNombre;
    @FXML
    private TableColumn<Paciente, String> colTelefono;
    @FXML
    private TableColumn<Paciente, String> colEmail;
    @FXML
    private TableColumn<Paciente, String> colSuscripcion;

    private ObservableList<Paciente> pacientesObservable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Bind columns to Paciente properties
        colCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colSuscripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoSuscripcion().toString()));

        // Set items to the TableView
        tablaPacientes.setItems(pacientesObservable);

        // Initialize ComboBox with subscription types
        txtSuscripcion.setItems(FXCollections.observableArrayList(TipoSuscripcion.BASICA.toString(), TipoSuscripcion.PREMIUM.toString()));
    }

    @FXML
    public void cargarPacientes(ActionEvent e) {
        // Create new patient and add to the observable list
        Paciente paciente = new Paciente(txtCedula.getText(), txtNombre.getText(), txtTelefono.getText(), txtEmail.getText(), TipoSuscripcion.valueOf(txtSuscripcion.getValue()));
        pacientesObservable.add(paciente);

        // Show success alert
        mostrarAlerta("Paciente registrado correctamente", Alert.AlertType.INFORMATION);

        // Clear fields after adding
        limpiarCampos();
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    private void limpiarCampos() {
        txtCedula.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtEmail.clear();
        txtSuscripcion.setValue(null);
    }
}

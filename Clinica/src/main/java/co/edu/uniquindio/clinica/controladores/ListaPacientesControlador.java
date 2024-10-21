package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.model.Clinica;
import co.edu.uniquindio.clinica.model.Paciente;
import co.edu.uniquindio.clinica.model.factory.TipoSuscripcion;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private ComboBox<TipoSuscripcion> txtSuscripcion;

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
    @FXML
    private Button btnBorrarPaciente;
    Clinica clinica = Clinica.getInstance();
    private final ObservableList<Paciente> pacientesObservable = FXCollections.observableArrayList();
    private final List<Node>camposDatos = new ArrayList<>();
    private  Paciente pacienteSeleccionado;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pacienteSeleccionado = tablaPacientes.getSelectionModel().getSelectedItem();
        // Set nodes to Nodes list
        camposDatos.add(txtNombre);
        camposDatos.add(txtCedula);
        camposDatos.add(txtSuscripcion);
        camposDatos.add(txtEmail);
        camposDatos.add(txtTelefono);

        // Bind columns to Paciente properties
        colCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCedula()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colSuscripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoSuscripcion().toString()));
        //Set list to pacientesObservable
        pacientesObservable.addAll(clinica.getListaPacientes());
        // Set items to the TableView
        txtSuscripcion.setItems(FXCollections.observableArrayList(TipoSuscripcion.values()));
        //Se agrega un listener de seleccion
        selecionListener();
        tablaPacientes.setItems(pacientesObservable);
    }
    private void actualizarTabla(){
        tablaPacientes.refresh();
    }
    public void selecionListener(){
        tablaPacientes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> {
            pacienteSeleccionado = newValue;
            llenarCamposItemSeleccionado();
        });
    }
    private void llenarCamposItemSeleccionado(){ try{if(verificarSeleccionTabla()) {
        txtCedula.setText(pacienteSeleccionado.getCedula());
        txtTelefono.setText(pacienteSeleccionado.getTelefono());
        txtEmail.setText(pacienteSeleccionado.getEmail());
        txtNombre.setText(pacienteSeleccionado.getNombre());
        txtSuscripcion.setValue(pacienteSeleccionado.getTipoSuscripcion());
    }   }catch (Exception e){};
    }
    private boolean verificarSeleccionTabla()throws Exception{
        if (tablaPacientes.getSelectionModel().getSelectedItem() == null){
            throw new Exception("Debe seleccionar un elemento");
        }
        return true;
    }
    private boolean verificarCamposLlenos() throws Exception{
        for (Node nodo: camposDatos){
            if (nodo instanceof TextField && ((TextField) nodo).getText().isEmpty()||
                    nodo instanceof ComboBox && ((ComboBox<?>) nodo).getValue() == null){
                throw new Exception("Todos los campos deben estar llenos");
            }
        }
        return true;
    }
    @FXML
    public void borrarPaciente(ActionEvent e){try{ if (verificarSeleccionTabla()){
        clinica.getListaPacientes().remove(pacienteSeleccionado);
        pacientesObservable.remove(pacienteSeleccionado);
        limpiarCampos();
        mostrarAlerta("Paciente eliminado con exito", Alert.AlertType.INFORMATION);
        actualizarTabla();
    }
    }   catch (Exception e1){mostrarAlerta(e1.getMessage(), Alert.AlertType.WARNING);}
    }
    @FXML
    public void actualizarPaciente(ActionEvent e) {try{
      if (verificarSeleccionTabla() && verificarCamposLlenos()){
          for (Paciente paciente : clinica.getListaPacientes()){
              if (paciente == tablaPacientes.getSelectionModel().getSelectedItem()){
                  paciente.setCedula(txtCedula.getText());
                  paciente.setNombre(txtNombre.getText());
                  paciente.setEmail(txtEmail.getText());
                  paciente.setTelefono(txtTelefono.getText());
                  paciente.setTipoSuscripcion(txtSuscripcion.getValue());
                  limpiarCampos();
                  tablaPacientes.getSelectionModel().clearSelection();
                  pacienteSeleccionado = null;
                  actualizarTabla();
                  mostrarAlerta("Paciente actualizado con exito", Alert.AlertType.INFORMATION);
              }
          }
        } } catch (Exception e2){
        mostrarAlerta(e2.getMessage(), Alert.AlertType.ERROR);
    }
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

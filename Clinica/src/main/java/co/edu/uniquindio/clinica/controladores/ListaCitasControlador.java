package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.model.Cita;
import co.edu.uniquindio.clinica.model.Clinica;
import co.edu.uniquindio.clinica.model.Factura;
import co.edu.uniquindio.clinica.model.Paciente;
import co.edu.uniquindio.clinica.model.factory.TipoSuscripcion;
import co.edu.uniquindio.clinica.model.servicio.Servicio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.util.converter.LocalDateStringConverter;
import java.time.format.DateTimeFormatter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.util.Arrays.stream;

public class ListaCitasControlador extends AbstractControlador implements Initializable {
    @FXML
    private StackPane CitasPanel;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtPaciente;
    @FXML
    private DatePicker txtFecha;
    @FXML
    private ComboBox txtServicio;
    @FXML
    private TextField txtFactura;
    @FXML
    private TableView<Cita> tablaCitas;
    @FXML
    private TableColumn<Cita, String> colId;
    @FXML
    private TableColumn<Cita, String> colPaciente;
    @FXML
    private TableColumn<Cita, LocalDate> colFecha;
    @FXML
    private TableColumn<Cita, String> colServicio;
    @FXML
    private TableColumn<Cita, String> colFactura;

    private Clinica clinica = Clinica.getInstance();  // Initialize this way if it's needed by default.
    private final ObservableList<Cita> citasObservable = FXCollections.observableArrayList();
    private final List<Node> camposDatos = new ArrayList<>();
    private Cita citaSeleccionada;

    Paciente paciente = clinica.getListaPacientes()
            .stream()
            .filter(p -> p.getNombre().equals(txtPaciente.getText()))
            .findFirst()
            .orElse(null);  // Handle case where no matching patient is found
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
        camposDatos.add(txtId);
        camposDatos.add(txtPaciente);
        camposDatos.add(txtFecha);
        camposDatos.add(txtServicio);
        camposDatos.add(txtFactura);

        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colPaciente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getNombre()));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colFecha.setCellFactory(column -> new TableCell<Cita, LocalDate>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            protected void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (empty || date == null) {
                    setText(null);
                } else {
                    setText(date.format(formatter));
                }
            }
        });
        colServicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServicio().getNombre()));
        colFactura.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFactura().getId()));
        citasObservable.addAll(clinica.getListaCitas());
        txtServicio.setItems(FXCollections.observableArrayList(clinica.getListaServiciosDisponibles()));
        selecionListener();
        tablaCitas.setItems(citasObservable);
    }
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
//        // Set nodes to Nodes list
//        camposDatos.add(txtId);
//        camposDatos.add(txtPaciente);
//        camposDatos.add(txtFecha);
//        camposDatos.add(txtServicio);
//        camposDatos.add(txtFactura);
//
//        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
//        colPaciente.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPaciente().getNombre()));
//        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
//        colFecha.setCellFactory(column -> new TableCell<Cita, LocalDate>() {
//            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            @Override
//            protected void updateItem(LocalDate date, boolean empty) {
//                super.updateItem(date, empty);
//                if (empty || date == null) {
//                    setText(null);
//                } else {
//                    setText(date.format(formatter));
//                }
//            }
//        });
//        colServicio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServicio().getNombre()));
//        colFactura.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFactura().getId()));
//        citasObservable.addAll(clinica.getListaCitas());
//        txtServicio.setItems(FXCollections.observableArrayList(clinica.getListaServiciosDisponibles()));
//        selecionListener();
//        tablaCitas.setItems(citasObservable);
//    }

    private void actualizarTabla(){
        tablaCitas.refresh();
    }
    public void selecionListener(){
        tablaCitas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> {
            citaSeleccionada = newValue;
            llenarCamposItemSeleccionado();
        });
    }
    private void llenarCamposItemSeleccionado(){ try{if(verificarSeleccionTabla()) {
        txtId.setText(citaSeleccionada.getId());
        txtPaciente.setText(citaSeleccionada.getPaciente().getNombre());
        txtFecha.setValue(citaSeleccionada.getFecha());
        txtServicio.setValue(citaSeleccionada.getServicio());
        txtFactura.setText(citaSeleccionada.getFactura().getId());
    }   }catch (Exception e){};
    }
    private boolean verificarSeleccionTabla()throws Exception{
        if (tablaCitas.getSelectionModel().getSelectedItem() == null){
            throw new Exception("Debe seleccionar un elemento");
        }
        return true;
    }

    private boolean verificarCamposLlenos() throws Exception {
        for (Node nodo : camposDatos) {
            if (nodo instanceof TextField) {
                if (((TextField) nodo).getText().isEmpty() || nodo instanceof ComboBox && ((ComboBox<?>) nodo).getValue() == null || nodo instanceof DatePicker && ((DatePicker) nodo).getValue() == null) {
                    throw new Exception("Todos los campos deben estar llenos");
                }
            }
        }
        return true;
    }


    @FXML
    public void cargarCitas() {
        // Find the paciente based on name or some other criteria
        Paciente paciente = clinica.getListaPacientes()
                .stream()
                .filter(p -> p.getNombre().equals(txtPaciente.getText()))
                .findFirst()
                .orElse(null);  // Handle case where no patient is found

        if (paciente == null) {
            mostrarAlerta("Paciente no encontrado", Alert.AlertType.ERROR);
            return;  // Exit the method if no patient is found
        }

        // Get the selected date from the DatePicker
        LocalDate fecha = txtFecha.getValue();
        if (fecha == null) {
            mostrarAlerta("Fecha no seleccionada", Alert.AlertType.ERROR);
            return;
        }

        // Find the servicio based on the selection in the ComboBox
        Servicio servicio = clinica.getListaServiciosDisponibles()
                .stream()
                .filter(s -> s.getNombre().equals(txtServicio.getValue().toString()))
                .findFirst()
                .orElse(null);  // Handle case where no service is found

        if (servicio == null) {
            mostrarAlerta("Servicio no encontrado", Alert.AlertType.ERROR);
            return;
        }

        // Assuming txtFactura contains the factura ID or data
        Factura factura = clinica.getListaFacturas()
                .stream()
                .filter(f -> f.getId().equals(txtFactura.getText()))
                .findFirst()
                .orElse(null);  // Handle case where no factura is found
        // Use appropriate constructor for Factura

        // Create the new Cita object
        Cita cita = new Cita(paciente, txtId.getText(), fecha, servicio, factura);
        citasObservable.add(cita);

        // Show success alert and clear fields
        mostrarAlerta("Cita registrada correctamente", Alert.AlertType.INFORMATION);
        limpiarCampos();
    }


    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    private void limpiarCampos() {
        txtPaciente.clear();
        txtFecha.setValue(null);
        txtServicio.setValue(null);
        txtFactura.clear();
    }
}
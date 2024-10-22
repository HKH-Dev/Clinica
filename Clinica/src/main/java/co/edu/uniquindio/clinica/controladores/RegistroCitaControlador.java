package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.model.Clinica;
import co.edu.uniquindio.clinica.model.Factura;
import co.edu.uniquindio.clinica.model.Paciente;
import co.edu.uniquindio.clinica.model.factory.TipoSuscripcion;
import co.edu.uniquindio.clinica.model.servicio.ComplejidadServicios;
import co.edu.uniquindio.clinica.model.servicio.Servicio;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RegistroCitaControlador extends AbstractControlador implements Initializable {
    @FXML
    private StackPane CitasPanel;
    @FXML
    private TextField txtId; // Campo para ingresar ID del paciente
    @FXML
    private TextField txtPaciente; // Campo para mostrar el nombre del paciente
    @FXML
    private DatePicker txtFecha; // Campo para seleccionar la fecha
    @FXML
    private ComboBox<Servicio> comboServicios;  // ComboBox for displaying services
    @FXML
    private TextField txtFactura; // Campo para ingresar el ID de la factura

    @FXML
    public void initialize() {
        comboServicios.getItems().addAll(clinica.getListaServiciosDisponibles());
    }

    private final Clinica clinica;

    public RegistroCitaControlador() {
        clinica = Clinica.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboServicios.getItems().addAll(clinica.getListaServiciosDisponibles());
//        comboServicios.setPromptText("Seleccione un servicio");
        List<Servicio> listadoServicios = clinica.getListaServiciosDisponibles();
        System.out.println("Servicios disponibles: " + listadoServicios.size());
//
        comboServicios.setCellFactory(listView -> new ListCell<Servicio>() {
            @Override
            protected void updateItem(Servicio servicio, boolean empty) {
                super.updateItem(servicio, empty);
                if (empty || servicio == null) {
                    setText(null);
                } else {
                    setText(servicio.getNombre());  // Display the name of the service
                }
            }
        });
    }


    public void registrarCita(ActionEvent e) {
        try {
            // Buscar el paciente por su nombre o ID desde el campo de texto txtPaciente
            Paciente pacienteSeleccionado = buscarPaciente(txtId.getText());
            if (pacienteSeleccionado == null) {
                throw new Exception("El paciente no está registrado.");
            }

            // Obtener los servicios seleccionados del ComboBox
            List<Servicio> serviciosSeleccionados = new ArrayList<>();
            if (comboServicios.getValue() instanceof List<?>) {
                for (Object servicioSeleccionado : (List<?>) comboServicios.getValue()) {
                    serviciosSeleccionados.add((Servicio) servicioSeleccionado);
                }
            } else {
                // Si solo se selecciona un servicio
                serviciosSeleccionados.add((Servicio) comboServicios.getValue());
            }
            if (serviciosSeleccionados.isEmpty()) {
                throw new Exception("Debe seleccionar al menos un servicio.");
            }

            // Generar la factura usando el ID proporcionado y los servicios seleccionados
            double subtotal = calcularSubtotal(serviciosSeleccionados);
            double valorTotal = calcularValorTotal(serviciosSeleccionados); // Aplicar descuentos si es necesario
            Factura factura = new Factura(LocalDate.now(), txtFactura.getText(), subtotal, valorTotal);

            clinica.generarFactura(factura.getFecha(), factura.getId(), factura.getSubtotal(), factura.getValorTotal());

            // Crear la cita utilizando el paciente, los servicios y la factura
            Servicio servicioPrincipal = serviciosSeleccionados.get(0); // Puedes elegir el principal o gestionar la lista
            clinica.generarCita(txtId.getText(), txtFecha.getValue(), pacienteSeleccionado, servicioPrincipal, factura);

            // Limpiar campos
            limpiarCampos();
            mostrarAlerta("Cita registrada correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private Paciente buscarPaciente(String id) {
        for (Paciente pacientico : clinica.getListaPacientes()) {
            if (pacientico.getCedula().equals(id)) { // Compara cédulas
                return pacientico; // Retorna el paciente encontrado
            }
        }
        return null; // Retorna null si no se encuentra el paciente
    }

    private void cambioIDListener() {
        txtId.textProperty().addListener((observable, oldValue, newValue) -> {
            Paciente pacienteSeleccionado = buscarPaciente(newValue);
            if (pacienteSeleccionado != null) {
                txtPaciente.setText(pacienteSeleccionado.getNombre()); // Rellena el campo de nombre del paciente
            } else {
                txtPaciente.clear(); // Limpia el campo si no se encuentra el paciente
            }
        });
    }

    private double calcularSubtotal(List<Servicio> servicios) {
        double subtotal = 0.0;
        for (Servicio servicio : servicios) {
            subtotal += servicio.getPrecio();
        }
        return subtotal;
    }

    private double calcularValorTotal(List<Servicio> servicios) {
        double valorTotal = 0.0;
        for (Servicio servicio : servicios) {
            if (servicio.getComplejidadServicios() == ComplejidadServicios.MEDIA) {
                valorTotal += servicio.getPrecio() * 0.5; // 50% descuento para servicios medianos
            } else if (servicio.getComplejidadServicios() == ComplejidadServicios.ALTA) {
                valorTotal += servicio.getPrecio(); // Pago completo para servicios difíciles
            }
        }
        return valorTotal;
    }

//    @Override
//    public void inicializarClinica(Clinica clinica) {
//        super.inicializarDatosClinica(clinica);
//        System.out.println("RegistroPacienteControlador.inicializarClinica");
//    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    private void limpiarCampos() {
        txtPaciente.clear();
        txtFecha.setValue(null);
        comboServicios.setValue(null);
        txtFactura.clear();
    }
}
package co.edu.uniquindio.clinica.model.servicio;

import javafx.fxml.FXML;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Servicio {
    private String id;
    private String nombre;
    private double precio;
    private boolean disponibilidad;
    private ComplejidadServicios complejidadServicios;

    public Servicio(String id, String nombre, double precio, ComplejidadServicios complejidadServicios) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.complejidadServicios = complejidadServicios;
    }
}
/*ublic class RegistroCitaControlador extends AbstractControlador implements Initializable {
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
    private TextArea txtDetalleServicio;
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
        List<Servicio> listaServicios = clinica.getListaServiciosDisponibles(); // Obtener los servicios disponibles
        System.out.println("Servicios disponibles: " + listaServicios.size()); // Verifica que hay servicios

// Si la lista tiene elementos, agrégalos al ComboBox
        if (!listaServicios.isEmpty()) {
            comboServicios.setItems(FXCollections.observableArrayList(listaServicios));
        }

        // Optional: Display service names in ComboBox dropdown
        comboServicios.setCellFactory(listView -> new ListCell<Servicio>() {
            @Override
            protected void updateItem(Servicio servicio, boolean empty) {
                super.updateItem(servicio, empty);
                if (empty || servicio == null) {
                    setText(null);
                } else {
                    setText(servicio.getNombre()); // Display the service name
                }
            }
        });

        // Set the selected service's name in the ComboBox button
        comboServicios.setButtonCell(new ListCell<Servicio>() {
            @Override
            protected void updateItem(Servicio servicio, boolean empty) {
                super.updateItem(servicio, empty);
                if (empty || servicio == null) {
                    setText(null);
                } else {
                    setText(servicio.getNombre()); // Display the name of selected service
                }
            }
        });

        // Listener to display selected service details in txtDetalleServicio
        comboServicios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtDetalleServicio.setText(newValue.getDetalles()); // Display service details
            } else {
                txtDetalleServicio.clear(); // Clear if no service is selected
            }
        });

        comboServicios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Servicio seleccionado: " + newValue.getNombre() + " - Detalles: " + newValue.getDetalles());
                txtDetalleServicio.setText(newValue.getDetalles()); // Display service details
            } else {
                txtDetalleServicio.clear(); // Clear if no service is selected
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
}*/



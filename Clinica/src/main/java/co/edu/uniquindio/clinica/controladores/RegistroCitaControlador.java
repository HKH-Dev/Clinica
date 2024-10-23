package co.edu.uniquindio.clinica.controladores;
import co.edu.uniquindio.clinica.model.Clinica;
import co.edu.uniquindio.clinica.model.Factura;
import co.edu.uniquindio.clinica.model.Paciente;
import co.edu.uniquindio.clinica.model.servicio.ComplejidadServicios;
import co.edu.uniquindio.clinica.model.servicio.CoverturaServicio;
import co.edu.uniquindio.clinica.model.servicio.Servicio;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RegistroCitaControlador extends AbstractControlador implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtPaciente;
    @FXML
    private DatePicker txtFecha;
    @FXML
    private ComboBox<Servicio> comboServicios;
    @FXML
    private TextArea txtDetalleServicio;
    @FXML
    private TextArea txtFactura;
    @FXML
    private Button btnCalcularFactura;

    private Clinica clinica;

    public RegistroCitaControlador() {
//        clinica = new Clinica();
        clinica = Clinica.getInstance();
    }

//    @FXML
//    private void initialize() {
//        // Populate the combo box with available services
//        comboServicios.getItems().addAll(
//                new Servicio("Consulta General", 50.0, ComplejidadServicios.BAJA),
//                new Servicio("Examen de Laboratorio", 100.0, ComplejidadServicios.MEDIA),
//                new Servicio("Cirugía", 300.0, ComplejidadServicios.ALTA)
//        );
//    }
@Override
public void initialize(URL url, ResourceBundle resourceBundle) {
//    // Obtener la lista de servicios disponibles de la clínica
//    List<Servicio> listaServicios = clinica.getListaServiciosDisponibles();
//    System.out.println("Servicios disponibles: " + listaServicios.size()); // Mostrar en consola la cantidad de servicios disponibles

    // Populate the combo box with available services from the clinic
    List<Servicio> listaServicios = clinica.getListaServiciosDisponibles();
    if (!listaServicios.isEmpty()) {comboServicios.setItems(FXCollections.observableArrayList(listaServicios));}
//    // Verificar si la lista no está vacía y añadir los servicios al ComboBox
//    if (!listaServicios.isEmpty()) {comboServicios.setItems(FXCollections.observableArrayList(listaServicios));}

    comboServicios.setCellFactory(listView -> new ListCell<Servicio>() { // Configurar el ComboBox para mostrar el nombre del servicio en la lista desplegable
        @Override
        protected void updateItem(Servicio servicio, boolean empty) {
            super.updateItem(servicio, empty);
            if (empty || servicio == null) {
                setText(null);
            } else {
                setText(servicio.getNombre()); // Mostrar el nombre del servicio
                 }
        }
    });

    // Configurar el ComboBox para mostrar el nombre del servicio seleccionado
    comboServicios.setButtonCell(new ListCell<Servicio>() {
        @Override
        protected void updateItem(Servicio servicio, boolean empty) {
            super.updateItem(servicio, empty);
            if (empty || servicio == null) {
                setText(null);
            } else {
                setText(servicio.getNombre()); // Mostrar el nombre del servicio seleccionado
            }
        }
    });

    cambioIDListener();

    // Listener para mostrar los detalles del servicio seleccionado en el TextArea
    comboServicios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            // Mostrar todos los detalles del servicio en el TextArea
            String detallesServicio = generarDetallesServicio(newValue);
            txtDetalleServicio.setText(detallesServicio);
            System.out.println("Servicio seleccionado: " + newValue.getNombre() + " - Detalles: " + detallesServicio);
        } else {
            txtDetalleServicio.clear(); // Limpiar el TextArea si no se selecciona un servicio
        }
    });// Listener para mostrar los detalles de la factura en el TextArea
    txtFactura.textProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            // Mostrar todos los detalles de la factura en el TextArea
            txtFactura.setText(newValue);
            System.out.println("Factura seleccionada: " + newValue);
        } else {
            txtFactura.clear(); // Limpiar el TextArea si no se selecciona una factura
        }
    });
}
    private String generarDetallesServicio(Servicio servicio) {
        return "ID: " + servicio.getId() + "\n"
                + "Nombre: " + servicio.getNombre() + "\n"
                + "Precio: " + servicio.getPrecio() + "\n"
                + "Disponibilidad: " + (servicio.isDisponibilidad() ? "Disponible" : "No Disponible") + "\n"
                + "Complejidad: " + servicio.getComplejidadServicios();
    }
//    @FXML
//    private void buscarPacientePorId(KeyEvent e) {
//        Paciente paciente = buscarPaciente(txtId.getText());
//        if (paciente != null) {
//            txtNombre.setText(paciente.getNombre());
//            txtTelefono.setText(paciente.getTelefono());
//        } else {
//            txtNombre.clear();
//            txtTelefono.clear();
//        }
//    }
    @FXML
    private void seleccionarServicio(MouseEvent e) {
        Servicio servicioSeleccionado = comboServicios.getValue();
        if (servicioSeleccionado != null) {
            txtFactura.setText("Servicio: " + servicioSeleccionado.getNombre() + "\n" +
                    "Precio: " + servicioSeleccionado.getPrecio() + "\n" +
                    "Complejidad: " + servicioSeleccionado.getComplejidadServicios());
        }
    }

    @FXML
    private void registrarCita(ActionEvent e) {
        try {
            // Ensure a patient is selected
            Paciente pacienteSeleccionado = buscarPaciente(txtId.getText());
            if (pacienteSeleccionado == null) {throw new Exception("Debe ingresar un ID de paciente válido.");}
            // Ensure a service is selected
            Servicio servicioSeleccionado = comboServicios.getValue();
            if (servicioSeleccionado == null) {throw new Exception("Debe seleccionar un servicio.");}

//            CoverturaServicio coverturaServicio;
//            if (servicioSeleccionado.getComplejidadServicios() == ComplejidadServicios.BAJA) {coverturaServicio = CoverturaServicio.GRATUITO;}
//            else if (servicioSeleccionado.getComplejidadServicios() == ComplejidadServicios.MEDIA) {coverturaServicio = CoverturaServicio.PARTICULARCONDESCUENTO;}
//            else {coverturaServicio = CoverturaServicio.PARTICULARSINDESCUENTO;}
//
//            // Define the coverage of the service
//            CoverturaServicio coverturaServicio;
//            if (servicioSeleccionado.getComplejidadServicios() == ComplejidadServicios.BAJA) {
//                coverturaServicio = CoverturaServicio.GRATUITO;
//            } else if (servicioSeleccionado.getComplejidadServicios() == ComplejidadServicios.MEDIA) {
//                coverturaServicio = CoverturaServicio.PARTICULARCONDESCUENTO;
//            } else {
//                coverturaServicio = CoverturaServicio.PARTICULARSINDESCUENTO;
//            }

            CoverturaServicio coverturaServicio;
            if (servicioSeleccionado.getComplejidadServicios() == ComplejidadServicios.BAJA) {
                coverturaServicio = CoverturaServicio.GRATUITO;
            } else if (servicioSeleccionado.getComplejidadServicios() == ComplejidadServicios.MEDIA) {
                coverturaServicio = CoverturaServicio.PARTICULARCONDESCUENTO;
            } else {
                coverturaServicio = CoverturaServicio.PARTICULARSINDESCUENTO;
            }
            // Calculate subtotal and total charges
            double subtotal = servicioSeleccionado.getPrecio();
            double valorTotal = calcularValorTotal(servicioSeleccionado); // Apply discounts if necessary
            // Generate a new Factura object
//            Factura nuevaFactura = new Factura(LocalDate.now(), txtFactura.getText(), pacienteSeleccionado, servicioSeleccionado, coverturaServicio, valorTotal, subtotal);

            // Create and save the factura
            Factura nuevaFactura = new Factura(LocalDate.now(), txtId.getText(), pacienteSeleccionado, servicioSeleccionado,
                    coverturaServicio, valorTotal, subtotal);

            clinica.generarFactura(nuevaFactura.getFecha(), nuevaFactura.getId(), nuevaFactura.getPaciente(), nuevaFactura.getServicio(),
                    nuevaFactura.getCoverturaServicio(), nuevaFactura.getSubtotal(), nuevaFactura.getValorTotal());

            // Register the appointment (Cita)
            clinica.generarCita(txtId.getText(), txtFecha.getValue(), pacienteSeleccionado, servicioSeleccionado, nuevaFactura);

            mostrarAlerta("Cita registrada correctamente", Alert.AlertType.INFORMATION);


            // Display the calculated charges in the TextArea
            txtFactura.setText("Servicio: " + servicioSeleccionado.getNombre() + "\n" +
                    "Subtotal: " + subtotal + "\nValor Total: " + valorTotal);

//            // Optionally, you can save the factura in the system
//            clinica.generarFactura(nuevaFactura.getFecha(), nuevaFactura.getId(), nuevaFactura.getPaciente(), nuevaFactura.getServicio(), nuevaFactura.getCoverturaServicio(), nuevaFactura.getSubtotal(), nuevaFactura.getValorTotal());

            mostrarAlerta("Factura calculada exitosamente", Alert.AlertType.INFORMATION);

            // Crear la cita con el paciente, servicio y factura
//            clinica.generarCita(txtId.getText(), txtFecha.getValue(), pacienteSeleccionado, servicioSeleccionado, nuevaFactura);

            limpiarCampos();
//            mostrarAlerta("Cita registrada correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void calcularFactura(ActionEvent e) {
        try {
            Servicio servicioSeleccionado = comboServicios.getValue();
            if (servicioSeleccionado == null) {
                throw new Exception("Debe seleccionar un servicio.");
            }

            double subtotal = servicioSeleccionado.getPrecio();
            double valorTotal = calcularValorTotal(servicioSeleccionado);

            // Display the calculated factura in the TextArea
            txtFactura.setText("Servicio: " + servicioSeleccionado.getNombre() + "\n"
                    + "Subtotal: " + subtotal + "\n"
                    + "Valor Total: " + valorTotal);

        } catch (Exception ex) {
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
//    // Utility method to search for a patient by ID (simulated)
//    private Paciente buscarPaciente(String id) {
//        // Simulate patient search (you would replace this with your actual search logic)
//        if (id.equals("123")) {
//            return new Paciente("123", "Juan Perez", "987654321");
//        }
//        return null;
//    }
//    // Utility method to calculate total value of the service, applying discounts if necessary
//    private double calcularValorTotal(Servicio servicio) {
//        double total = servicio.getPrecio();
//        if (servicio.getComplejidadServicios() == ComplejidadServicios.MEDIA) {
//            total *= 0.5; // Apply 50% discount for medium complexity
//        } else if (servicio.getComplejidadServicios() == ComplejidadServicios.BAJA) {
//            total = 0.0; // Easy services are free
//        }
//        return total;
//    }
//
//    // Utility method to show an alert message
//    private void mostrarAlerta(String mensaje, Alert.AlertType tipoAlerta) {
//        Alert alert = new Alert(tipoAlerta);
//        alert.setContentText(mensaje);
//        alert.show();
//    }

    private Paciente buscarPaciente(String id) {
        for (Paciente pacientico : clinica.getListaPacientes()) {
            if (pacientico.getCedula().equals(id)) {
                return pacientico; // Retornar el paciente encontrado
            }
        }
        return null; // Retornar null si no se encuentra el paciente
    }

    private void cambioIDListener() {
        txtId.textProperty().addListener((observable, oldValue, newValue) -> {
            Paciente pacienteSeleccionado = buscarPaciente(newValue);
            if (pacienteSeleccionado != null) {
                txtPaciente.setText(pacienteSeleccionado.getNombre()); // Rellenar el campo de nombre del paciente
            } else {
                txtPaciente.clear(); // Limpiar el campo si no se encuentra el paciente
            }
        });
    }

    private double calcularValorTotal(Servicio servicio) {
        if(servicio.getComplejidadServicios() == ComplejidadServicios.BAJA){
            return 0.0; // Servicios fáciles son gratuitos
        } else if (servicio.getComplejidadServicios() == ComplejidadServicios.MEDIA) {
            return servicio.getPrecio() * 0.5; // 50% descuento para servicios de complejidad media
        } else if (servicio.getComplejidadServicios() == ComplejidadServicios.ALTA) {
            return servicio.getPrecio(); // Pago completo para servicios difíciles
        }
        return servicio.getPrecio(); // Para otros casos (Baja complejidad)
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
        txtDetalleServicio.clear();
        txtFactura.clear();
    }
}
/*package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.model.Clinica;
import co.edu.uniquindio.clinica.model.Factura;
import co.edu.uniquindio.clinica.model.Paciente;
import co.edu.uniquindio.clinica.model.servicio.ComplejidadServicios;
import co.edu.uniquindio.clinica.model.servicio.CoverturaServicio;
import co.edu.uniquindio.clinica.model.servicio.Servicio;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.time.LocalDate;
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
    private ComboBox<Servicio> comboServicios;  // ComboBox para mostrar los servicios disponibles
    @FXML
    private TextArea txtDetalleServicio; // TextArea para mostrar los detalles del servicio
    @FXML
    private TextArea txtFactura; // Campo para ingresar el ID de la factura
    @FXML
    private Button btnCalcularFactura;

    private final Clinica clinica;

    public RegistroCitaControlador() {
        clinica = Clinica.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Obtener la lista de servicios disponibles de la clínica
        List<Servicio> listaServicios = clinica.getListaServiciosDisponibles();
        System.out.println("Servicios disponibles: " + listaServicios.size()); // Mostrar en consola la cantidad de servicios disponibles

        // Verificar si la lista no está vacía y añadir los servicios al ComboBox
        if (!listaServicios.isEmpty()) {
            comboServicios.setItems(FXCollections.observableArrayList(listaServicios));
        }

        // Configurar el ComboBox para mostrar el nombre del servicio en la lista desplegable
        comboServicios.setCellFactory(listView -> new ListCell<Servicio>() {
            @Override
            protected void updateItem(Servicio servicio, boolean empty) {
                super.updateItem(servicio, empty);
                if (empty || servicio == null) {
                    setText(null);
                } else {
                    setText(servicio.getNombre()); // Mostrar el nombre del servicio
                }
            }
        });

        // Configurar el ComboBox para mostrar el nombre del servicio seleccionado
        comboServicios.setButtonCell(new ListCell<Servicio>() {
            @Override
            protected void updateItem(Servicio servicio, boolean empty) {
                super.updateItem(servicio, empty);
                if (empty || servicio == null) {
                    setText(null);
                } else {
                    setText(servicio.getNombre()); // Mostrar el nombre del servicio seleccionado
                }
            }
        });

        // Listener para mostrar los detalles del servicio seleccionado en el TextArea
        comboServicios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Mostrar todos los detalles del servicio en el TextArea
                String detallesServicio = generarDetallesServicio(newValue);
                txtDetalleServicio.setText(detallesServicio);
                System.out.println("Servicio seleccionado: " + newValue.getNombre() + " - Detalles: " + detallesServicio);
            } else {
                txtDetalleServicio.clear(); // Limpiar el TextArea si no se selecciona un servicio
            }
        });

        // Listener para mostrar los detalles de la factura en el TextArea
        txtFactura.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Mostrar todos los detalles de la factura en el TextArea
                txtFactura.setText(newValue);
                System.out.println("Factura seleccionada: " + newValue);
            } else {
                txtFactura.clear(); // Limpiar el TextArea si no se selecciona una factura
            }
        });

        // Listener para actualizar el campo de paciente cuando se ingresa el ID
        cambioIDListener();
    }

    // Método para generar la descripción detallada del servicio seleccionado
    private String generarDetallesServicio(Servicio servicio) {
        return "ID: " + servicio.getId() + "\n"
                + "Nombre: " + servicio.getNombre() + "\n"
                + "Precio: " + servicio.getPrecio() + "\n"
                + "Disponibilidad: " + (servicio.isDisponibilidad() ? "Disponible" : "No Disponible") + "\n"
                + "Complejidad: " + servicio.getComplejidadServicios();
    }

    public void registrarCita(ActionEvent e) {
        try {
            // Buscar el paciente por su ID
            Paciente pacienteSeleccionado = buscarPaciente(txtId.getText());
            if (pacienteSeleccionado == null) {
                throw new Exception("El paciente no está registrado.");
            }

            // Obtener el servicio seleccionado del ComboBox
            Servicio servicioSeleccionado = comboServicios.getValue();
            if (servicioSeleccionado == null) {
                throw new Exception("Debe seleccionar un servicio.");
            }

            // Definir la cobertura del servicio
            CoverturaServicio coverturaServicio;
            if (servicioSeleccionado.getComplejidadServicios() == ComplejidadServicios.BAJA) {
                coverturaServicio = CoverturaServicio.GRATUITO;
            } else if (servicioSeleccionado.getComplejidadServicios() == ComplejidadServicios.MEDIA) {
                coverturaServicio = CoverturaServicio.PARTICULARCONDESCUENTO;
            } else {
                coverturaServicio = CoverturaServicio.PARTICULARSINDESCUENTO;
            }

            // Generar la factura
            double subtotal = servicioSeleccionado.getPrecio();
            double valorTotal = calcularValorTotal(servicioSeleccionado); // Aplicar descuentos si es necesario

            Factura factura = new Factura(LocalDate.now(), txtFactura.getText(), pacienteSeleccionado, servicioSeleccionado, coverturaServicio, valorTotal, subtotal);

            clinica.generarFactura(factura.getFecha(), factura.getId(), factura.getPaciente(), factura.getServicio(), factura.getCoverturaServicio(), factura.getSubtotal(), factura.getValorTotal());

            // Crear la cita con el paciente, servicio y factura
            clinica.generarCita(txtId.getText(), txtFecha.getValue(), pacienteSeleccionado, servicioSeleccionado, factura);

            // Limpiar campos
            limpiarCampos();
            mostrarAlerta("Cita registrada correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }


    /*public void registrarCita(ActionEvent e) {
        try {
            Paciente pacienteSeleccionado = buscarPaciente(txtId.getText());  // Buscar el paciente por su ID
            if (pacienteSeleccionado == null) {
                throw new Exception("El paciente no está registrado.");
            }
            Servicio servicioSeleccionado = comboServicios.getValue();  // Obtener el servicio seleccionado del ComboBox
            if (servicioSeleccionado == null) {
                throw new Exception("Debe seleccionar un servicio.");
            }

            double subtotal = servicioSeleccionado.getPrecio();   // Generar la factura
            double valorTotal = calcularValorTotal(servicioSeleccionado); // Aplicar descuentos si es necesario

            Factura factura = new Factura(LocalDate.now(), txtFactura.getText(), pacienteSeleccionado, servicioSeleccionado, CoverturaServicio, valorTotal, subtotal);

            clinica.generarFactura(factura.getFecha(), factura.getId(), factura.getPaciente(), factura.getServicio(), factura.getCoverturaServicio(), factura.getSubtotal(), factura.getValorTotal());

            // Crear la cita con el paciente, servicio y factura
            clinica.generarCita(txtId.getText(), txtFecha.getValue(), pacienteSeleccionado, servicioSeleccionado, factura);

            // Limpiar campos
            limpiarCampos();
            mostrarAlerta("Cita registrada correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private Paciente buscarPaciente(String id) {
        for (Paciente pacientico : clinica.getListaPacientes()) {
            if (pacientico.getCedula().equals(id)) {
                return pacientico; // Retornar el paciente encontrado
            }
        }
        return null; // Retornar null si no se encuentra el paciente
    }

    private void cambioIDListener() {
        txtId.textProperty().addListener((observable, oldValue, newValue) -> {
            Paciente pacienteSeleccionado = buscarPaciente(newValue);
            if (pacienteSeleccionado != null) {
                txtPaciente.setText(pacienteSeleccionado.getNombre()); // Rellenar el campo de nombre del paciente
            } else {
                txtPaciente.clear(); // Limpiar el campo si no se encuentra el paciente
            }
        });
    }

    private double calcularValorTotal(Servicio servicio) {
        if (servicio.getComplejidadServicios() == ComplejidadServicios.MEDIA) {
            return servicio.getPrecio() * 0.5; // 50% descuento para servicios de complejidad media
        } else if (servicio.getComplejidadServicios() == ComplejidadServicios.ALTA) {
            return servicio.getPrecio(); // Pago completo para servicios difíciles
        }
        return servicio.getPrecio(); // Para otros casos (Baja complejidad)
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
        txtDetalleServicio.clear();
        txtFactura.clear();
    }
}*/



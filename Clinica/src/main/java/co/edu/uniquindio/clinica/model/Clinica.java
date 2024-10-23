package co.edu.uniquindio.clinica.model;

import co.edu.uniquindio.clinica.model.factory.TipoSuscripcion;
import co.edu.uniquindio.clinica.model.servicio.ComplejidadServicios;
import co.edu.uniquindio.clinica.model.servicio.CoverturaServicio;
import co.edu.uniquindio.clinica.model.servicio.Servicio;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString

public class Clinica {
    private  List<Paciente> listaPacientes;
    private  List<Servicio> listaServiciosDisponibles;
    private List<Cita> listaCitas;
    private List<Factura> listaFacturas;
    private static  Clinica clinica;

    public Clinica() {
        this.listaPacientes = new ArrayList<>();
        this.listaServiciosDisponibles = new ArrayList<>();
        this.listaCitas = new ArrayList<>();
        this.listaFacturas = new ArrayList<>();
    }


    public static Clinica getInstance(){
        if (clinica == null){
            clinica = new Clinica();
        }
        return clinica;
    }

    public void registrarPaciente(String cedula, String nombre, String telefono, String email, TipoSuscripcion suscripcion){
        Paciente paciente = new Paciente(cedula, nombre, telefono, email, suscripcion);
        listaPacientes.add(paciente);
    }


    public void generarServicio(String id, String nombre, double precio, ComplejidadServicios complejidadServicios) throws Exception {
        // Remove random availability check for service creation
        boolean disponibilidad = true; // Set to true or handle availability separately
        if (id != null && nombre != null && precio > 0) {
            Servicio servicio = new Servicio(id, nombre, precio, complejidadServicios);
            servicio.setDisponibilidad(disponibilidad); // Set availability here if needed
            listaServiciosDisponibles.add(servicio);
            System.out.println("Servicio creado: " + servicio.toString());
        } else {
            throw new Exception("Datos inválidos para el servicio.");
        }
    }

public void generarCita(String id, LocalDate fechaCita, Paciente paciente, Servicio servicio, Factura factura) throws Exception {
    boolean fechaDisponible = true;
    for (Cita disponibilidadCita : listaCitas) {
        if (disponibilidadCita.getFecha().equals(fechaCita)) {
            fechaDisponible = false;
            if (disponibilidadCita.getPaciente().equals(paciente)) {
                throw new Exception("Paciente ya cuenta con una cita en la fecha y hora seleccionada");
            }
        }
    }
    if (fechaDisponible) {
        if (id != null && servicio != null && factura != null) {
            Cita cita = new Cita(paciente, id, fechaCita, servicio, factura);
            listaCitas.add(cita);
            System.out.println("Cita creada exitosamente: " + cita.toString());
        } else {
            throw new Exception("Cita no pudo ser creada");
        }
    } else {
        throw new Exception("Fecha no disponible");
    }
}


    public void generarFactura(LocalDate fecha, String id, Paciente paciente, Servicio servicio, CoverturaServicio coverturaServicio, double valorTotal, double subtotal) throws Exception{

        if(fecha != null && id != null && valorTotal > 0 && subtotal > 0){
            Factura factura = new Factura(fecha, id, paciente, servicio, coverturaServicio, subtotal, valorTotal);
            System.out.println("Factura generada: " + factura.toString());
            listaFacturas.add(factura);
            System.out.println("Factura generada: " + factura.toString());
        } else {
            throw new Exception("Factura no generada");
        }
    }
}
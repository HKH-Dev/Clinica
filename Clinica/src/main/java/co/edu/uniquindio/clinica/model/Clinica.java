package co.edu.uniquindio.clinica.model;

import co.edu.uniquindio.clinica.model.factory.TipoSuscripcion;
import co.edu.uniquindio.clinica.model.servicio.ComplejidadServicios;
import co.edu.uniquindio.clinica.model.servicio.Servicio;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

@Setter
@Getter


public class Clinica {
    private List<Paciente> listaPacientes;
    private List<Servicio> listaServiciosDisponibles;
    private List<Cita> listaCitas;
    private List<Factura> listaFacturas;

    public Clinica() {
        List<Paciente> listaPacientes = new ArrayList<>();
        List<Servicio> listaServiciosDisponibles = new ArrayList<>();
        List<Cita> listaCitas = new ArrayList<>();
        List<Factura> listaFacturas = new ArrayList<>();
    }

    public void registrarPaciente(String cedula, String nombre, String telefono, String email, TipoSuscripcion suscripcion){
        Paciente paciente = new Paciente(cedula, nombre, telefono, email, suscripcion);
        listaPacientes.add(paciente);
    }

    public void  baseDatosPacientes(){
        for(Paciente paciente: listaPacientes)
            System.out.println(paciente);
    }


    public void generarServicio(String id, String nombre, double precio, ComplejidadServicios complejidadServicios) throws Exception {
        boolean disponibilidad = RandomGenerator.getDefault().nextBoolean();
        if (disponibilidad){
            if ( id != null && nombre != null && precio > 0) {
                Servicio servicio = new Servicio(id, nombre, precio, complejidadServicios);
                listaServiciosDisponibles.add(servicio);
                System.out.println("Servicio creado: " + servicio.toString());
            }
        } else {
            throw new Exception("Servicio no disponible");
        }
    }
    public void generarCita(String id, LocalDate fechaCita, Paciente paciente, Servicio servicio, Factura factura)throws Exception {
        for (Cita disponibilidadCita : listaCitas) {
            if (disponibilidadCita.getFecha().equals(fechaCita)) {
                for (Paciente pacienteCita : listaPacientes) {
                    if (disponibilidadCita.getPaciente().equals(pacienteCita)) {
                        if (id != null && servicio != null && factura != null) {
                            Cita cita = new Cita(paciente, id, fechaCita, servicio, factura);
                            listaCitas.add(cita);
                            System.out.println("Cita creada exitosamente: " + cita.toString());
                        } else {
                            throw new Exception("Cita no pudo ser creada");
                        }
                    } else {
                        throw new Exception("Paciente ya cuenta con una cita en la fecha y hora seleccionada");
                    }
                }
            }else{
                throw new Exception("Fecha no disponible");
            }
        }
    }
    public void cancelarCita(String CcPaciente, LocalDate fechaCita) throws Exception {
        for (Paciente paciente : listaPacientes){
            if(paciente.getCedula().equals(CcPaciente)){
                for(Cita citaConsultar : listaCitas){
                    if (citaConsultar.getPaciente().equals(paciente)){
                        if(citaConsultar.getFecha().equals(fechaCita)){
                        listaCitas.remove(citaConsultar);
                        System.out.println("Cita cancelada exitosamente");
                        }
                    }else {
                        throw new Exception("Cita no encontrada");
                }
            }
        }else {
            throw new Exception("Paciente no tiene citas programadas");
            }
        }
    }

    public void generarFactura(LocalDate fecha, String id, double subtotal, double valorTotal) throws Exception{

        if(fecha != null && id != null && valorTotal > 0 && subtotal > 0){
            Factura factura = new Factura(fecha, id,subtotal, valorTotal);
            System.out.println("Factura generada: " + factura.toString());
            listaFacturas.add(factura);
            System.out.println("Factura generada: " + factura.toString());
        } else {
            throw new Exception("Factura no generada");
        }
    }


}
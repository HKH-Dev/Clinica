package co.edu.uniquindio.clinica.model;

import co.edu.uniquindio.clinica.TipoSuscripcion;
import co.edu.uniquindio.clinica.servicio.ComplejidadServicios;
import co.edu.uniquindio.clinica.servicio.Servicio;
import lombok.Getter;

import java.util.List;

@Getter
public class ClinicaPrincipal {
    private List<Paciente> listaPacientes;
    private List<Servicio> listaServiciosDisponibles;

    public void registrarPaciente(String cedula, String nombre, String telefono, String email, TipoSuscripcion suscripcion){
        Paciente paciente = new Paciente(cedula, nombre, telefono, email, suscripcion);
        listaPacientes.add(paciente);
    }

    public void generarServicio(String id, String nombre, double precio, ComplejidadServicios complejidadServicios) throws Exception {
        boolean disponibilidad = true;
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
}

package co.edu.uniquindio.clinica.model;

import co.edu.uniquindio.clinica.model.factory.Suscripcion;

import java.util.List;

public class ClinicaPrincipal {
    private List<Paciente> listaPacientes;

    public void registrarPaciente(String cedula, String nombre, String telefono, String email, Suscripcion suscripcion){
        Paciente paciente = new Paciente(cedula, nombre, telefono, email, suscripcion);
        listaPacientes.add(paciente);
    }
}

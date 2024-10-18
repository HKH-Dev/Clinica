package co.edu.uniquindio.clinica.model;

import co.edu.uniquindio.clinica.servicio.Servicio;

import java.util.List;

public class Clinica {
    private List<Cita> listaCitas;
    private List<Paciente> listaPacientes;
    private List<Servicio> listaServicios;
    private String tipoSuscripcion;

    public void registrarPaciente(Paciente paciente){
        if (paciente.getCedula().isEmpty() || paciente.getNombre().isEmpty() || paciente.getTelefono().isEmpty() || paciente.getEmail().isEmpty()){
            throw new NullPointerException("Los datos del paciente no pueden estar vacíos");
        }
        listaPacientes.add(paciente);
    }

    public void registrarCita(Cita cita){
        if(cita.getId().isEmpty()|| cita.getFecha() == null || cita.getPaciente() == null || cita.getServicio() == null || cita.getFactura() == null){
            throw new NullPointerException("La cita no puede estar vacía");
        }
        listaCitas.add(cita);
    }

    public void registrarServicio(Servicio servicio){
        if(servicio.getId().isEmpty() || servicio.getNombre().isEmpty() || servicio.getPrecio() >= 0){
            throw new NullPointerException("El servicio no puede ser nulo");
        }
        listaServicios.add(servicio);
    }
    public void litadoPacientes(Paciente paciente){
        if(paciente.getCedula().isEmpty() || paciente.getNombre().isEmpty() || paciente.getTelefono().isEmpty() || paciente.getEmail().isEmpty()){
            throw new NullPointerException("Los datos del paciente no pueden estar vacíos");
        }
        for (Paciente paciente1: listaPacientes){
            System.out.println(paciente1);
        }

    }


//    public void serviciosDisponibles(String tipoSuscripcion){
//        if(tipoSuscripcion.isEmpty()){
//            throw new NullPointerException("El tipo de suscripción no puede estar vacío");
//            }
//        if (tipoSuscripcion.equals("Basica")){
//            for (Servicio servicio: listaServicios){
//                if (servicio.getNombre().equals("Consulta")){
//                    System.out.println(servicio);
//                }
//            }
//        }



}

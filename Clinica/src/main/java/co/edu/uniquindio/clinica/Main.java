package co.edu.uniquindio.clinica;

import co.edu.uniquindio.clinica.model.Clinica;
import co.edu.uniquindio.clinica.model.factory.TipoSuscripcion;
import co.edu.uniquindio.clinica.model.servicio.ComplejidadServicios;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        Clinica clinica = new Clinica();

        clinica.registrarPaciente("123", "Laura", "123456", "laura@email.com", TipoSuscripcion.PREMIUM);
        clinica.registrarPaciente("456", "Juan", "7891011", "juan@email.com", TipoSuscripcion.BASICA);
        clinica.registrarPaciente("789", "Pedro", "121314", "pedro@email.com", TipoSuscripcion.PREMIUM);

//        lifeCare.baseDatosPacientes();

        clinica.generarServicio("001", "Consulta General", 100, ComplejidadServicios.BAJA);
        clinica.generarServicio("002", "Rayos X", 200, ComplejidadServicios.MEDIA);
        clinica.generarServicio("003", "Cirugía", 1000, ComplejidadServicios.ALTA);
        clinica.generarServicio("004", "Consulta Especializada", 500, ComplejidadServicios.ALTA);


        clinica.registrarPaciente("123456789", "Juan Pérez", "555-1234", "juan@example.com", TipoSuscripcion.BASICA);
        clinica.registrarPaciente("987654321", "Maria Gomez", "555-5678", "maria@example.com", TipoSuscripcion.PREMIUM);
        clinica.registrarPaciente("135792468", "Carlos Ruiz", "555-2468", "carlos@example.com", TipoSuscripcion.BASICA);
        clinica.registrarPaciente("246813579", "Ana Martínez", "555-1357", "ana@example.com", TipoSuscripcion.PREMIUM);
        clinica.registrarPaciente("112233445", "Luis Castro", "555-4455", "luis@example.com", TipoSuscripcion.BASICA);
        clinica.registrarPaciente("998877665", "Lucía Fernández", "555-7766", "lucia@example.com", TipoSuscripcion.PREMIUM);


        // Generar servicios (6 ejemplos)
        clinica.generarServicio("S001", "Consulta General", 50.0, ComplejidadServicios.BAJA);
        clinica.generarServicio("S002", "Cirugía", 200.0, ComplejidadServicios.ALTA);
        clinica.generarServicio("S003", "Terapia Física", 75.0, ComplejidadServicios.MEDIA);
        clinica.generarServicio("S004", "Rayos X", 100.0, ComplejidadServicios.MEDIA);
        clinica.generarServicio("S005", "Análisis de Sangre", 40.0, ComplejidadServicios.BAJA);
        clinica.generarServicio("S006", "Consulta Odontológica", 80.0, ComplejidadServicios.ALTA);

        System.out.println("Pacientes registrados: " + clinica.getListaPacientes().toString());
        System.out.println("Servicios generados: " + clinica.getListaServiciosDisponibles().toString());


//        lifeCare.generarCita("001", LocalDate.now(), lifeCare.getListaPacientes().get(0), lifeCare.getListaServiciosDisponibles().get(0), null);
//



    }
}

package co.edu.uniquindio.clinica;

import co.edu.uniquindio.clinica.model.Clinica;
import co.edu.uniquindio.clinica.model.factory.TipoSuscripcion;
import co.edu.uniquindio.clinica.model.servicio.ComplejidadServicios;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        Clinica lifeCare = new Clinica();

        lifeCare.registrarPaciente("123", "Laura", "123456", "laura@email.com", TipoSuscripcion.PREMIUM);
        lifeCare.registrarPaciente("456", "Juan", "7891011", "juan@email.com", TipoSuscripcion.BASICA);
        lifeCare.registrarPaciente("789", "Pedro", "121314", "pedro@email.com", TipoSuscripcion.PREMIUM);

//        lifeCare.baseDatosPacientes();

        lifeCare.generarServicio("001", "Consulta General", 100, ComplejidadServicios.BAJA);
        lifeCare.generarServicio("002", "Rayos X", 200, ComplejidadServicios.MEDIA);
        lifeCare.generarServicio("003", "Cirugía", 1000, ComplejidadServicios.ALTA);
        lifeCare.generarServicio("004", "Consulta Especializada", 500, ComplejidadServicios.ALTA);

//        lifeCare.generarCita("001", LocalDate.now(), lifeCare.getListaPacientes().get(0), lifeCare.getListaServiciosDisponibles().get(0), null);
//



    }
}

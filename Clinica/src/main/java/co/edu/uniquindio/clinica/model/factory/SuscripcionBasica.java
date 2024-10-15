package co.edu.uniquindio.clinica.model.factory;

import co.edu.uniquindio.clinica.model.Servicio;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SuscripcionBasica implements Suscripcion {
    Servicio servicioPrestado;
    List<Servicio> Consulta;

    public SuscripcionBasica(Servicio servicioPrestado) {
        this.servicioPrestado = servicioPrestado;
    }
//
//    @Override
//    public void formatoConsulta() {
//        Boolean asistio = null;
//        LocalDate fechaConsulta = LocalDate.now();
//        if(asistio){
//            String motivoConsulta;
//            String diagnostico;
//            String tratamiento = servicioPrestado.getListaServiciosDisponibles();

//        }


//    }
//    @Override
//    public void tipoServicio(Servicio servicio) {
//        System.out.println("Consulta");
//    }





}

package co.edu.uniquindio.clinica.model.factory;

import co.edu.uniquindio.clinica.model.Clinica;
import co.edu.uniquindio.clinica.model.servicio.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class SuscripcionBasica implements Suscripcion {
    @Override
    public void coverturaServicio(Servicio servicio) {
        Clinica clinica = new Clinica();
        List<Servicio> listaServicios = clinica.getListaServiciosDisponibles();

        for(Servicio servicioConsultar : listaServicios) {
            if(servicioConsultar.getNombre().equals(servicio.getNombre())) {
                servicio.setComplejidadServicios(servicioConsultar.getComplejidadServicios());
                servicio.setPrecio(servicioConsultar.getPrecio());
                System.out.println("Servicio encontrado: " + servicioConsultar.toString());
                break;
            }
        }

        switch (servicio.getComplejidadServicios()) {
            case BAJA -> {
                servicio.setPrecio(0);  // Free for basic subscription
                System.out.println("El servicio es gratuito para Suscripción Básica.");
            }
            case MEDIA -> {
                servicio.setPrecio(servicio.getPrecio() * 0.5);
                System.out.println("El servicio tiene 50% de descuento para Suscripción Básica.");
            }
            case ALTA -> {
                System.out.println("El servicio requiere el pago completo para Suscripción Básica.");
            }
        }
    }
}

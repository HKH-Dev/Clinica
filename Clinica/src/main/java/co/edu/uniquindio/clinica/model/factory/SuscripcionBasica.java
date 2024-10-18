package co.edu.uniquindio.clinica.model.factory;

import co.edu.uniquindio.clinica.ClinicaApplication;
import co.edu.uniquindio.clinica.model.ClinicaPrincipal;
import co.edu.uniquindio.clinica.servicio.ConverturaServicio;
import co.edu.uniquindio.clinica.servicio.Servicio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter

public class SuscripcionBasica implements Suscripcion {
    @Override
    public void coverturaServicio(Servicio servicio) {
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

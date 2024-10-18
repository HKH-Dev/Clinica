package co.edu.uniquindio.clinica.model.factory;

import co.edu.uniquindio.clinica.servicio.ComplejidadServicios;
import co.edu.uniquindio.clinica.servicio.Servicio;

public class SuscripcionPremium implements Suscripcion {
        @Override
        public void coverturaServicio(Servicio servicio) {
                switch (servicio.getComplejidadServicios()) {
                        case BAJA -> {
                                servicio.setPrecio(0);  // Free for basic subscription
                                System.out.println("El servicio es gratuito para Suscripción Premium.");
                        }
                        case MEDIA -> {
                                servicio.setPrecio(servicio.getPrecio() * 0.15);
                                System.out.println("El servicio tiene 50% de descuento para Suscripción Premium.");
                        }
                        case ALTA -> {
                                servicio.setPrecio(servicio.getPrecio() * 0.5);
                                System.out.println("El servicio requiere el pago completo para Suscripción Premium.");
                        }
                }
        }
}




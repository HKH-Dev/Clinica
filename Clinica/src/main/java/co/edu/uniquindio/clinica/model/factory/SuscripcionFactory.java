package co.edu.uniquindio.clinica.model.factory;

import co.edu.uniquindio.clinica.TipoSuscripcion;
import co.edu.uniquindio.clinica.model.factory.SuscripcionPremium;

public class SuscripcionFactory {
    public static Suscripcion crearSuscripcion(TipoSuscripcion tipo) {
        return switch (tipo) {
            case BASICA -> new SuscripcionBasica();
            case PREMIUM -> new SuscripcionPremium();
            default -> throw new IllegalArgumentException("Tipo de suscripción no válido");
        };
    }
}

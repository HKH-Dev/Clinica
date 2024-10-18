package co.edu.uniquindio.clinica.model.servicio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Servicio {
    private String id;
    private String nombre;
    private double precio;
    private boolean disponibilidad;
    private ComplejidadServicios complejidadServicios;

    public Servicio(String id, String nombre, double precio, ComplejidadServicios complejidadServicios) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.complejidadServicios = complejidadServicios;
    }
}

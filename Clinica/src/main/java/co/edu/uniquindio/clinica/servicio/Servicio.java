package co.edu.uniquindio.clinica.servicio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
public class Servicio {
    private String id;
    private String nombre;
    private double precio;
    private boolean disponibilidad;
    private ComplejidadServicios complejidadServicios;
    private List<Servicio> listaServiciosDisponibles;

    public Servicio() {
        listaServiciosDisponibles = new ArrayList<>();
    }

    public Servicio(String id, String nombre, double precio, ComplejidadServicios complejidadServicios) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
//        this.disponibilidad = disponibilidad;
        this.complejidadServicios = complejidadServicios;
    }
}

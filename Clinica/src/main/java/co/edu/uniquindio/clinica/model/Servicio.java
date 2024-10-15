package co.edu.uniquindio.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Servicio {
    private String id;
    private String nombre;
    private double precio;
    private boolean disponibilidad;
    private List<Servicio> listaServiciosDisponibles;

    public Servicio(){
        listaServiciosDisponibles = new ArrayList<>();
    }

    public Servicio(String id, String nombre, double precio, boolean disponibilidad){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public void generarServicio(String id, String nombre, double precio, boolean disponibilidad) throws Exception{
        if(disponibilidad && id != null && nombre != null && precio > 0){
            Servicio servicio = new Servicio(id, nombre, precio, disponibilidad);
            listaServiciosDisponibles.add(servicio);
            System.out.println("Servicio creado: " + servicio.toString());
        }else{
            throw new Exception("Servicio no disponible");
        }
    }


}

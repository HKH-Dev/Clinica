package co.edu.uniquindio.clinica.model;

import co.edu.uniquindio.clinica.model.servicio.CoverturaServicio;
import co.edu.uniquindio.clinica.model.servicio.Servicio;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Factura {
    private LocalDate fecha;
    private String id;
    private Paciente paciente;
    private Servicio servicio;
    private CoverturaServicio coverturaServicio;
    private double valorTotal;
    private double subtotal;

    public Factura(LocalDate fecha, String id, Paciente paciente, Servicio servicio, CoverturaServicio coverturaServicio, double valorTotal, double subtotal) {
        this.fecha = fecha;
        this.id = id;
        this.paciente = paciente;
        this.servicio = servicio;
        this.coverturaServicio = coverturaServicio;
        this.subtotal = servicio.getPrecio();
        this.valorTotal = servicio.getPrecio();
    }
}

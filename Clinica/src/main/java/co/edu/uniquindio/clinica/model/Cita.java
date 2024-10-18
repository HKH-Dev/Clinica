package co.edu.uniquindio.clinica.model;

import co.edu.uniquindio.clinica.servicio.Servicio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class Cita {
    private Paciente paciente;
    private String id;
    private LocalDate fecha;
    private Servicio servicio;
    private Factura factura;

}

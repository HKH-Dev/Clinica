package co.edu.uniquindio.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.List;

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

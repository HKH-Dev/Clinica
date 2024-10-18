package co.edu.uniquindio.clinica.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Factura {
    private LocalDate fecha;
    private String id;
    private double valorTotal;
    private double subtotal;






}

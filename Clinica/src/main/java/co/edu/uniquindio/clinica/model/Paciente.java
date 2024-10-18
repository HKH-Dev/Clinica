package co.edu.uniquindio.clinica.model;

import co.edu.uniquindio.clinica.TipoSuscripcion;
import co.edu.uniquindio.clinica.model.factory.Suscripcion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Paciente {
    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private TipoSuscripcion tipoSuscripcion;
}

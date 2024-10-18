package co.edu.uniquindio.clinica.model;

import co.edu.uniquindio.clinica.model.factory.TipoSuscripcion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString

public class Paciente {
    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private TipoSuscripcion tipoSuscripcion;
}

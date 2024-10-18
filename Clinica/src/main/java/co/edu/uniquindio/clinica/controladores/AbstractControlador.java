package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.model.ClinicaPrincipal;
import lombok.Getter;



public abstract class AbstractControlador {
    @Getter
    private ClinicaPrincipal clinica;




    public void inicializarClinica(ClinicaPrincipal clinica){
        this.clinica = clinica;
    }


}


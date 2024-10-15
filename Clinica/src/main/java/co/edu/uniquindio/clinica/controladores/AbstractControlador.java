package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.model.Clinica;
import lombok.Getter;



public abstract class AbstractControlador {
    @Getter
    private Clinica clinica;




    public void inicializarClinica(Clinica clinica){
        this.clinica = clinica;
    }


}


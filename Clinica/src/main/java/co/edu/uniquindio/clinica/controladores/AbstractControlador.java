package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.model.Clinica;
import javafx.scene.control.Alert;
import lombok.Getter;
import org.simplejavamail.api.internal.clisupport.model.Cli;


public abstract class AbstractControlador {

    @Getter
    private Clinica clinica;

    public void inicializarClinica(Clinica clinica){
        this.clinica = Clinica.getInstance();
    }

//    private void mostrarAlerta(String mensaje, Alert.AlertType tipo){
//
//
//        Alert alert = new Alert(tipo);
//        alert.setTitle("Información");
//        alert.setHeaderText(null);
//        alert.setContentText(mensaje);
//        alert.show();

//    }



}


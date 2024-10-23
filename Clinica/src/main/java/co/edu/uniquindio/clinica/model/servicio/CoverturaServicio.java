package co.edu.uniquindio.clinica.model.servicio;

import lombok.Getter;

@Getter

public enum CoverturaServicio {
//    GRATUITO("Descuento Total 100%" , 1.0),
//    PARTICULARCONDESCUENTO("Descuento Parcial 50%", 0.5),
//    PARTICULARSINDESCUENTO("Cobro valor Total dscto 0%", 0);
    GRATUITO(1.0),
    PARTICULARCONDESCUENTO(0.5),
    PARTICULARSINDESCUENTO(0);

//    private String descripcion;
    private double porcentage;


   CoverturaServicio(double porcentage){
//        this.descripcion = descripcion;
        this.porcentage = porcentage;
    }
}

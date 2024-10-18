package co.edu.uniquindio.clinica.servicio;

import lombok.Getter;

@Getter

public enum ConverturaServicio {
    GRATUITO("Descuento Total 100%" , 1.0),
    PARTICULARCONDESCUENTO("Descuento Parcial 50%", 0.5),
    PARTICULARSINDESCUENTO("Cobro valor Total dscto 0%", 0);

    private String descripcion;
    private double porcentage;


   ConverturaServicio(String descripcion, double porcentage){
        this.descripcion = descripcion;
        this.porcentage = porcentage;
    }
}

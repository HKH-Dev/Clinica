package co.edu.uniquindio.clinica.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private LocalDate fecha;
    private String id;
    private double valorTotal;
    private double subtotal;
    private List<Factura> listaFacturas;

    public Factura(){
        listaFacturas = new ArrayList<>();
    }

    public Factura(LocalDate fecha, String id,double subtotal, double valorTotal){
        this.fecha = fecha;
        this.id = id;
        this.valorTotal = valorTotal;
        this.subtotal = subtotal;
    }

    public void generarFactura(LocalDate fecha, String id, double subtotal, double valorTotal) throws Exception{
        Factura factura = new Factura(fecha, id,subtotal, valorTotal);
        if(fecha != null && id != null && valorTotal > 0 && subtotal > 0){
            System.out.println("Factura generada: " + factura.toString());
            listaFacturas.add(factura);
        System.out.println("Factura generada: " + factura.toString());
    } else {
        throw new Exception("Factura no generada");
    }
    }



}

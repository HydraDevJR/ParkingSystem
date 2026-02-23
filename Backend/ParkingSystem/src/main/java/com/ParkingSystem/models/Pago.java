package com.ParkingSystem.models;

import java.time.LocalDate;

public class Pago {
    
    public enum MetodoPago {
        Efectivo,
        Tarjeta_Credito,
        Tarjeta_Debito,
        Qr
    }

    public enum EstadoPago {
        Pendiente,
        Completado,
        Fallido
    }
    
    //id, factura(Factura), monto, fecha de pago, metodo de pago(efectivo, tarjeta credito, tarjeta debito, qr), estado(pendiente, completado, fallido), referencia de pago
    private int id;
    private Factura factura;
    private double monto;
    private LocalDate fecha_pago;
    private MetodoPago metodo_pago;
    private EstadoPago estado_pago;
    private String referencia_pago;

    public Pago(int id, Factura factura, double monto, LocalDate fecha_pago, MetodoPago metodo_pago, EstadoPago estado_pago, String referencia_pago){
        this.id = id;
        this.factura = factura;
        this.monto = monto;
        this.fecha_pago = fecha_pago;
        this.metodo_pago = metodo_pago;
        this.estado_pago = estado_pago;
        this.referencia_pago = referencia_pago;
    }

    public Pago() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDate getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDate fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public MetodoPago getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(MetodoPago metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public EstadoPago getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(EstadoPago estado_pago) {
        this.estado_pago = estado_pago;
    }

    public String getReferencia_pago() {
        return referencia_pago;
    }

    public void setReferencia_pago(String referencia_pago) {
        this.referencia_pago = referencia_pago;
    }

}


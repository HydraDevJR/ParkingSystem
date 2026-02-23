package com.ParkingSystem.models;

import java.time.LocalDate;
@Entity
@table(name = "mantenimiento")

public class Mantenimiento {
    
    public enum TipoMantenimiento {
        Preventivo,
        Correctivo,
        Predictivo,
        Inspeccion,
        Emergencia
    }

    public enum EstadoMantenimiento {
        Programado,
        En_Progreso,
        Completado,
        Cancelado
    }

    //id, parqueadero(Parqueadero), tipo(tipo mantenimiento: preventivo, correctivo, predictivo, inspeccion, emergencia), descripcion, fecha programada, fecha inicio, fecha fin, estado(programado, en progreso, completado, cancelado), costo, proveedor

    private int id;
    private Parqueadero parqueadero;
    private TipoMantenimiento tipo;
    private String descripcion;
    private LocalDate fecha_programada;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private EstadoMantenimiento estado;
    private double costo;
    private String proveedor;

    public Mantenimiento(int id, Parqueadero parqueadero, TipoMantenimiento tipo, String descripcion, LocalDate fecha_programada, LocalDate fecha_inicio, LocalDate fecha_fin, EstadoMantenimiento estado, double costo, String proveedor){
        this.id = id;
        this.parqueadero = parqueadero;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha_programada = fecha_programada;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.estado = estado;
        this.costo = costo;
        this.proveedor = proveedor;
    }

    public Mantenimiento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public TipoMantenimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMantenimiento tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_programada() {
        return fecha_programada;
    }

    public void setFecha_programada(LocalDate fecha_programada) {
        this.fecha_programada = fecha_programada;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public EstadoMantenimiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoMantenimiento estado) {
        this.estado = estado;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}

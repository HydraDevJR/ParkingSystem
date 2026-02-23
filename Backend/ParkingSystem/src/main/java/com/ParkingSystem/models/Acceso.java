package com.ParkingSystem.models;

import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "accesos")
public class Acceso {
    
    public enum TipoAcceso {
        Entrada,
        Salida
    }

    public enum MetodoAcceso {
        Tarjeta,
        CodigoQR,
        Manual,
        Automatico
    }
    

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private Parqueadero parqueadero;
    private Vehiculo vehiculo;
    private TipoAcceso tipo;
    private MetodoAcceso metodo;
    private LocalDate fecha;
    private LocalTime hora;

    public Acceso(UUID id, Parqueadero parqueadero, Vehiculo vehiculo, TipoAcceso tipo, MetodoAcceso metodo, LocalDate fecha, LocalTime hora) {
        this.id = id;
        this.parqueadero = parqueadero;
        this.vehiculo = vehiculo;
        this.tipo = tipo;
        this.metodo = metodo;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Acceso() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public TipoAcceso getTipo() {
        return tipo;
    }

    public void setTipo(TipoAcceso tipo) {
        this.tipo = tipo;
    }

    public MetodoAcceso getMetodo() {
        return metodo;
    }

    public void setMetodo(MetodoAcceso metodo) {
        this.metodo = metodo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}

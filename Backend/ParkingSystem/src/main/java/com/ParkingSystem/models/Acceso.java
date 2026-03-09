package com.ParkingSystem.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
    @Column(updatable = false, nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fk_parqueadero", referencedColumnName = "id", updatable = false, nullable = false)
    private Parqueadero parqueadero;

    @ManyToOne
    @JoinColumn(name = "fk_vehiculo", referencedColumnName = "id", updatable = false, nullable = false)
    private Vehiculo vehiculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_acceso",nullable = false)
    private TipoAcceso tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_acceso",nullable = false)
    private MetodoAcceso metodo;

    @Column(name = "fecha",nullable = false)
    private LocalDateTime fecha;

    public Acceso(UUID id, Parqueadero parqueadero, Vehiculo vehiculo, TipoAcceso tipo, MetodoAcceso metodo, LocalDateTime fecha) {
        this.id = id;
        this.parqueadero = parqueadero;
        this.vehiculo = vehiculo;
        this.tipo = tipo;
        this.metodo = metodo;
        this.fecha = fecha;
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}

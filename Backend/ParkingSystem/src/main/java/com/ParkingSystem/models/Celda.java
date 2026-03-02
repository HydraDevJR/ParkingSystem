package com.ParkingSystem.models;

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
@Table(name = "celdas")
public class Celda {

    public enum EstadoCelda {
        Disponible,
        Ocupada,
        Reservada
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private Integer piso;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id", nullable = false)
    private TipoVehiculo tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCelda estado;

    @ManyToOne
    @JoinColumn(name = "parqueadero_id", nullable = false)
    private Parqueadero parqueadero;

    public Celda(Integer id, Integer numero, Integer piso, TipoVehiculo tipo, EstadoCelda estado, Parqueadero parqueadero) {
        this.id = id;
        this.numero = numero;
        this.piso = piso;
        this.tipo = tipo;
        this.estado = estado;
        this.parqueadero = parqueadero;
    }

    public Celda() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public EstadoCelda getEstado() {
        return estado;
    }

    public void setEstado(EstadoCelda estado) {
        this.estado = estado;
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

}

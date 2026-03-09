package com.ParkingSystem.models;

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
@Table(name = "celdas")
public class Celda {

    public enum EstadoCelda {
        Disponible,
        Ocupada,
        Reservada
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "numero_celda", nullable = false, unique = true)
    private Integer numero;

    @Column(name = "numero_piso", nullable = false)
    private Integer piso;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_vehiculo", referencedColumnName = "id", nullable = false)
    private TipoVehiculo tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_celda", nullable = false)
    private EstadoCelda estado;

    @ManyToOne
    @JoinColumn(name = "fk_parqueadero", referencedColumnName = "id", nullable = false)
    private Parqueadero parqueadero;

    public Celda(UUID id, Integer numero, Integer piso, TipoVehiculo tipo, EstadoCelda estado, Parqueadero parqueadero) {
        this.id = id;
        this.numero = numero;
        this.piso = piso;
        this.tipo = tipo;
        this.estado = estado;
        this.parqueadero = parqueadero;
    }

    public Celda() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

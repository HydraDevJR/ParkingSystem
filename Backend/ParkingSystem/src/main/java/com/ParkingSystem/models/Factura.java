package com.ParkingSystem.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
public class Factura {

    public enum TipoFactura {
        ESTADIA,
        RESERVA,
        PENALIZACION,
        MENSUALIDAD,
        AJUSTE
    }

    public enum EstadoFactura {
        EMITIDA,
        PAGADA,
        ANULADA,
        VENCIDA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int estadia;

    @Enumerated(EnumType.STRING)
    private TipoFactura tipo;

    private LocalDateTime fechaEmision;

    @Enumerated(EnumType.STRING)
    private EstadoFactura estado;

    @ManyToOne
    @JoinColumn(name = "tarifa_id")
    private Tarifa tarifa;

    public Factura() {
    }

    public Factura(Long id, int estadia, TipoFactura tipo, LocalDateTime fechaEmision, EstadoFactura estado, Tarifa tarifa) {
        this.id = id;
        this.estadia = estadia;
        this.tipo = tipo;
        this.fechaEmision = fechaEmision;
        this.estado = estado;
        this.tarifa = tarifa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEstadia() {
        return estadia;
    }

    public void setEstadia(int estadia) {
        this.estadia = estadia;
    }

    public TipoFactura getTipo() {
        return tipo;
    }

    public void setTipo(TipoFactura tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public void setEstado(EstadoFactura estado) {
        this.estado = estado;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }
}

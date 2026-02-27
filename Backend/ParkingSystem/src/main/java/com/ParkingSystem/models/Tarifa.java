package com.ParkingSystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tarifas")
public class Tarifa {

    public enum TipoTarifa {
        POR_HORA,
        POR_DIA,
        POR_MES
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoTarifa tipo;

    private double valor;

    private boolean activo;

    public Tarifa() {
    }

    public Tarifa(Long id, TipoTarifa tipo, double valor, boolean activo) {
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoTarifa getTipo() {
        return tipo;
    }

    public void setTipo(TipoTarifa tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
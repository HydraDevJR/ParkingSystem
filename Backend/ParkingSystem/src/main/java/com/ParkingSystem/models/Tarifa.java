package com.ParkingSystem.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
    @Column(name = "tipo_tarifa", nullable = false)
    private TipoTarifa tipo;

    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    // Relación: Una tarifa puede estar en muchas estadías
    @OneToMany(mappedBy = "tarifa", cascade = CascadeType.ALL)
    private List<Estadia> estadias;

    public Tarifa() {
    }

    public Tarifa(TipoTarifa tipo, double valor, boolean activo) {
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

    public List<Estadia> getEstadias() {
        return estadias;
    }

    public void setEstadias(List<Estadia> estadias) {
        this.estadias = estadias;
    }
}
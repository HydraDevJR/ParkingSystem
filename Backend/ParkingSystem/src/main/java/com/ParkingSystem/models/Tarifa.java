package com.ParkingSystem.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
    private Integer id;

    @Column(name = "tipo_tarifa", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private TipoTarifa tipo;

    @Column(name = "valor", nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "activo", nullable = false)
    private Boolean activo;

    @OneToMany(mappedBy = "tarifa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Estadia> estadias;

    public Tarifa() {}

    public Tarifa(TipoTarifa tipo, BigDecimal valor, Boolean activo) {
        this.tipo = tipo;
        this.valor = valor;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoTarifa getTipo() {
        return tipo;
    }

    public void setTipo(TipoTarifa tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<Estadia> getEstadias() {
        return estadias;
    }

    public void setEstadias(List<Estadia> estadias) {
        this.estadias = estadias;
    }
}
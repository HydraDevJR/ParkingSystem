package com.ParkingSystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String placa;
    private String marca;
    private String modelo;
    private String color;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id")
    private TipoVehiculo tipoVehiculo;

    @ManyToOne
    @JoinColumn(name = "id_usuario") 
    private Usuario propietario;

    
    public Vehiculo() {
    }


    public Vehiculo(Integer id, String placa, String marca, String modelo, String color, TipoVehiculo tipoVehiculo,
            Usuario propietario) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipoVehiculo = tipoVehiculo;
        this.propietario = propietario;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getPlaca() {
        return placa;
    }


    public void setPlaca(String placa) {
        this.placa = placa;
    }


    public String getMarca() {
        return marca;
    }


    public void setMarca(String marca) {
        this.marca = marca;
    }


    public String getModelo() {
        return modelo;
    }


    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }


    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }


    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }


    public Usuario getPropietario() {
        return propietario;
    }


    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

 
    
}
package com.ParkingSystem.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_vehiculos")
public class TipoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL)
    private List<Vehiculo> vehiculos;

    
    public TipoVehiculo() {
    }


    public TipoVehiculo(Integer id, String nombre, List<Vehiculo> vehiculos) {
        this.id = id;
        this.nombre = nombre;
        this.vehiculos = vehiculos;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }


    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }


}
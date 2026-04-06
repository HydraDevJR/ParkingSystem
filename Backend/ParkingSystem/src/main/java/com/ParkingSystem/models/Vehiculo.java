package com.ParkingSystem.models;

import java.util.List;

import com.ParkingSystem.models.utils.TipoVehiculo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "placa", nullable = false, unique = true, length = 20)
    private String placa;

    @Column(name = "marca", nullable = false, unique = false, length = 50)
    private String marca;

    @Column(name = "modelo", nullable = false, unique = false, length = 50)
    private String modelo;

    @Column(name = "color", nullable = false, unique = false, length = 20)
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vehiculo", nullable = false, unique = false)
    private TipoVehiculo tipoVehiculo;

    // Relación Muchos a Uno con Usuario
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario usuario;

    // Relación Uno a Muchos con Estadia
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<Estadia> estancias;

    
    public Vehiculo() {}


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


    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public List<Estadia> getEstancias() {
        return estancias;
    }


    public void setEstancias(List<Estadia> estancias) {
        this.estancias = estancias;
    }

   
}
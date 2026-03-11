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

       
    @Column(name = "tipo_vehiculo", nullable = false, unique = false)
    private TipoVehiculo tipoVehiculo;

    @Enumerated(EnumType.STRING)
    @Column(name = "TipoVehiculoE", nullable = false, unique = false )


    @ManyToOne
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id")
    private Usuario propietario;

    
    public Vehiculo() {
        
    }


    


 
    
}
package com.ParkingSystem.models;

import java.lang.annotation.Inherited;

@Entity
@table(name = "parqueadero")

public class Parqueadero {
    //id, nombre, tipo, uso, direccion, pisos, propietario, administrador, activo

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private integer id;

    @column(name = "nombre", nullable = false, unique = false, length = 50)
    private String nombre;

    @column(name = "tipo", nullable = false, unique = false, length = 50)
    private String tipo;

    @column(name = "uso", nullable = false, unique = false)
    private integer uso;

    @column(name = "direccion", nullable = false, unique = false, length = 250)
    private String direccion;

    @column(name = "pisos", nullable = false, unique = false)
    private integer pisos;

    @column(name = "propietario", nullable = false, unique = false, length = 100) 
    private String propietario;

    @column(name = "administrador", nullable = false, unique = false, length = 100)
    private String administrador;

    @column(name = "activo", nullable = false, unique = false)
    private boolean activo;

    @OneToMany(mappedBy = "parqueadero")
    private List<Mantenimiento> mantenimiento;

    public Parqueadero(integer id, String nombre, String tipo, integer uso, String direccion, integer pisos, String propietario, String administrador, boolean activo){
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.uso = uso;
        this.direccion = direccion;
        this.pisos = pisos;
        this.propietario = propietario;
        this.administrador = administrador;
        this.activo = activo;
    }

    public Parqueadero() {
    }

    public integer getId() {
        return id;
    }
    public void setId(integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public integer getUso() {
        return uso;
    }

    public void setUso(integer uso) {
        this.uso = uso;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public integer getPisos() {
        return pisos;
    }

    public void setPisos(integer pisos) {
        this.pisos = pisos;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}


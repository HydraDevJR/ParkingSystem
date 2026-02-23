package com.ParkingSystem.models;

public class Parqueadero {
    //id, nombre, tipo, uso, direccion, pisos, propietario, administrador, activo

    private integer id;
    private String nombre;
    private String tipo;
    private integer uso;
    private String direccion;
    private integer pisos;
    private String propietario;
    private String administrador;
    private boolean activo;

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


package com.ParkingSystem.models;

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
import java.time.LocalDate;
import java.util.List;

import com.ParkingSystem.models.utils.TipoCedula; 

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cedula", nullable = false, unique = true)
    private String aprovechamiento; 

    @Column(name = "cedula", nullable = false, unique = true)
    private String cedula;

    @Column(name = "nombre", nullable = false, unique = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, unique = false, length = 50)
    private String apellido;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(name = "telefono", nullable = false, unique = false, length = 20)
    private String telefono;

    @Column(name = "fecha_nacimiento", nullable = false, unique = false, length = 20)
    private LocalDate fechaNacimiento;

    @Column(name = "genero", nullable = false, unique = false, length = 20)
    private String genero;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cedula", nullable = false, unique = false, length = 20)
    private TipoCedula tipoCedula;

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL)
    private List<Vehiculo> vehiculos;

    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    public Usuario() {
    }

    public Usuario(Integer id, String cedula, String nombre, String apellido, String email, String telefono,
            LocalDate fechaNacimiento, String genero, TipoCedula tipoCedula, List<Vehiculo> vehiculos, List<Reserva> reservas) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.tipoCedula = tipoCedula;
        this.vehiculos = vehiculos;
        this.reservas = reservas; 
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public TipoCedula getTipoCedula() {
        return tipoCedula;
    }

    public void setTipoCedula(TipoCedula tipoCedula) {
        this.tipoCedula = tipoCedula;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
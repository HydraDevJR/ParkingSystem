package com.ParkingSystem.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "estadias")
public class Estadia {
    
    public enum EstadoEstadia {
        En_Curso,
        Finalizada,
        Cancelada
    }
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_vehiculo", referencedColumnName = "id", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "fk_celda", referencedColumnName = "id", nullable = false)
    private Celda celda;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin", nullable = true)
    private LocalDateTime fechaFin;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEstadia estado;

    @ManyToOne
    @JoinColumn(name = "fk_tarifa", referencedColumnName = "id", nullable = false)
    private Tarifa tarifa;

    // Constructor vacío y constructor con parámetros
    /*
        nota: no se incluyen la fechaCreacion y ni fechaModificacion porque se manejan automaticamente 
        con las anotaciones @CreationTimestamp y @UpdateTimestamp respectivamente
    */
    public Estadia(Vehiculo vehiculo, Celda celda, LocalDateTime fechaInicio, LocalDateTime fechaFin, EstadoEstadia estado, Tarifa tarifa) {
        this.vehiculo = vehiculo;
        this.celda = celda;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.tarifa = tarifa;
    }

    public Estadia() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Celda getCelda() {
        return celda;
    }

    public void setCelda(Celda celda) {
        this.celda = celda;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public EstadoEstadia getEstado() {
        return estado;
    }

    public void setEstado(EstadoEstadia estado) {
        this.estado = estado;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

}
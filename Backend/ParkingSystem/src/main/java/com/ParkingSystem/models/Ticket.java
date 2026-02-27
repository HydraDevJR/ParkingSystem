package com.ParkingSystem.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    public enum EstadoTicket {
        ABIERTO,
        CERRADO,
        ANULADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int estadia;

    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private EstadoTicket estado;

    public Ticket() {
    }

    public Ticket(Long id, int estadia, LocalDateTime fechaCreacion, EstadoTicket estado) {
        this.id = id;
        this.estadia = estadia;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEstadia() {
        return estadia;
    }

    public void setEstadia(int estadia) {
        this.estadia = estadia;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoTicket getEstado() {
        return estado;
    }

    public void setEstado(EstadoTicket estado) {
        this.estado = estado;
    }
}
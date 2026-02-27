package com.ParkingSystem.models;

public class Reserva {
    
    public enum EstadoReserva {
        Activa,
        Cancelada,
        Finalizada
    }

    //id, celda(Celda) una celda pertenece a una reserva, usuario(Un usuario tinene muchas reservas pertenecen a un usuario), fecha inicio, fecha fin, limite de tiempo, fecha de creacion, fecha de cancelacion, estado(activa, cancelada, finalizada)
}

package com.ParkingSystem.models;

public class Factura {
    
    public enum TipoFactura {
        Estadia,
        Reserva,
        Penalizacion,
        Mensualidad,
        Ajuste
    }

    public enum EstadoFactura {
        Emitida,
        Pagada,
        Anulada,
        Vencida
    }

    //id, estadia(Estadia), tipo(tipo factura: estadia, reserva, penalizacion, mensualidad, ajuste), fecha de emision, estado(emitida, pagada, anulada, vencida), tarifa(Tarifa)

}

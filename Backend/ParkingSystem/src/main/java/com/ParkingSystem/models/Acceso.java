package com.ParkingSystem.models;

public class Acceso {
    
    public enum TipoAcceso {
        Entrada,
        Salida
    }

    public enum MetodoAcceso {
        Tarjeta,
        CodigoQR,
        Manual,
        Automatico
    }

    //id, parqueadero(Parqueadero), vehiculo(Vehiculo), tipo(tipo acceso: entrada, salida), metodo(metodo acceso: tarjeta, codigo QR, manual, automatico), fecha y hora
}

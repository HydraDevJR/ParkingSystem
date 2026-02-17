package com.ParkingSystem.models;

public class Celda {

    public enum EstadoCelda {
        Disponible,
        Ocupada,
        Reservada
    }
    //id, numero, piso, tipo(carro, moto...), estado(disponible, ocupada, reservada), parqueadero
}

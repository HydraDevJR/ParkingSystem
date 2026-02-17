package com.ParkingSystem.models;

public class Mantenimiento {
    
    public enum TipoMantenimiento {
        Preventivo,
        Correctivo,
        Predictivo,
        Inspeccion,
        Emergencia
    }

    public enum EstadoMantenimiento {
        Programado,
        En_Progreso,
        Completado,
        Cancelado
    }

    //id, parqueadero(Parqueadero), tipo(tipo mantenimiento: preventivo, correctivo, predictivo, inspeccion, emergencia), descripcion, fecha programada, fecha inicio, fecha fin, estado(programado, en progreso, completado, cancelado), costo, proveedor
}

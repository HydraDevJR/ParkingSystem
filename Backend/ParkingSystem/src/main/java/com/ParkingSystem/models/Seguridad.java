package com.ParkingSystem.models;

public class Seguridad {
    
    public enum TipoSeguridad {
        Incidente,
        Alerta,
        Novedad,
        Emergencia
    }

    public enum TipoGravedad {
        Baja,
        Media,
        Alta,
        Critica
    }

    //id, parqueadero(Parqueadero), tipo(tipo seguridad: incidente, alerta, novedad, emergencia), descripcion, fecha creacion, gravedad(baja, media, alta, critica), resuelto, fecha resolucion
}

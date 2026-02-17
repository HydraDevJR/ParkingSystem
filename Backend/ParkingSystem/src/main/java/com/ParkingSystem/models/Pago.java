package com.ParkingSystem.models;

public class Pago {
    
    public enum MetodoPago {
        Efectivo,
        Tarjeta_Credito,
        Tarjeta_Debito,
        Qr
    }

    public enum EstadoPago {
        Pendiente,
        Completado,
        Fallido
    }
    
    //id, factura(Factura), monto, fecha de pago, metodo de pago(efectivo, tarjeta credito, tarjeta debito, qr), estado(pendiente, completado, fallido), referencia de pago
}

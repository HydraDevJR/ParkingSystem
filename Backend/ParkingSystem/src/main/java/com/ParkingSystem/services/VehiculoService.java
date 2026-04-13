package com.ParkingSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ParkingSystem.models.Vehiculo;
import com.ParkingSystem.repositories.IVehiculoRepositorie;

@Service
public class VehiculoService {
    
    @Autowired
    private IVehiculoRepositorie vehiculoRepositorie;

    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {

        if(vehiculoRepositorie.findById(vehiculo.getId()).isPresent()){

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El vehículo ya existe"
            );

        }

        if(vehiculo.getPlaca().isEmpty() || vehiculo.getPlaca().isBlank() || vehiculo.getPlaca() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "La placa del vehículo es obligatoria"
            );
        }

        if(vehiculo.getMarca().isEmpty() || vehiculo.getMarca().isBlank() || vehiculo.getMarca() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "La marca del vehículo es obligatoria"
            );
        }

        if(vehiculo.getModelo().isEmpty() || vehiculo.getModelo().isBlank() || vehiculo.getModelo() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El modelo del vehículo es obligatorio"
            );
        }

        if(vehiculo.getColor().isEmpty() || vehiculo.getColor().isBlank() || vehiculo.getColor() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El color del vehículo es obligatorio"
            );
        }

        if(vehiculo.getTipoVehiculo() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El tipo de vehículo es obligatorio"
            );
        }

        if(vehiculo.getUsuario() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El usuario es obligatorio"
            );
        }


        return vehiculoRepositorie.save(vehiculo);
    }
}

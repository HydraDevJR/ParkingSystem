package com.ParkingSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ParkingSystem.models.Tarifa;
import com.ParkingSystem.repositories.ITarifaRepositorie;

@Service
public class TarifaService {

    @Autowired
    private ITarifaRepositorie tarifaRepositorie;

    // Guardar una tarifa
    public Tarifa guardarTarifa(Tarifa tarifa) {
        

        if (tarifa.getTipo() == null) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El tipo de tarifa es obligatorio"
            );

        }

        
        if (tarifa.getValor() <= 0) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El valor de la tarifa debe ser mayor a cero"
            );

        }

       return tarifaRepositorie.save(tarifa);
    }

    public List<Tarifa> Listar_Tarifas() {
        return tarifaRepositorie.findByActivoTrue();
    }
}
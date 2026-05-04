package com.ParkingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ParkingSystem.models.Vehiculo;
import com.ParkingSystem.services.VehiculoService;

@RestController
@RequestMapping("parkingsystem/v1/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    //Funcion controladora del servicio de guardar un vehiculo
    @PostMapping
    public ResponseEntity<?> controllerGuardar(@RequestBody Vehiculo vehiculo){
        return ResponseEntity.status(HttpStatus.OK).body(
            vehiculoService.guardarVehiculo(vehiculo)
        );
    }

    //Funcion controladora del servicio de listar todos los usuarios
    @GetMapping
    public ResponseEntity<?> controllerListar(){
        return ResponseEntity.status(HttpStatus.OK).body(
            vehiculoService.listarVehiculos()
        );
    }
}

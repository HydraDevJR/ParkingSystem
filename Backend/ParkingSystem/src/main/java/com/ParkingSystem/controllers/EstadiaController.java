package com.ParkingSystem.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ParkingSystem.models.Estadia;
import com.ParkingSystem.services.EstadiaService;

@RestController
@RequestMapping("/parkingsystem/v1/estadias")
public class EstadiaController {
    
    //Inyectar el servicio correspondiente
    @Autowired
    private EstadiaService servicio;

    //para cada servicio ofrecido se debe programar una funcion
    //esa funcion recibira las peticiones del pedido y respondera

    //funcion controladora del servicio guardar estadia
    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody Estadia datos){
        return ResponseEntity.status(HttpStatus.OK).body(
            servicio.guardarEstadia(datos)
        );
    }
    

    //funcion controladora del servicio de listar todos los usuarios
    @GetMapping
    public ResponseEntity<?>controladorListarTodo(){
        return ResponseEntity.status(HttpStatus.OK).body(
            servicio.listarEstadias()
        );
    }


}
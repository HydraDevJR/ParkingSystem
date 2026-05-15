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

    //Funcion controladora del servicio de guardar una estadia
    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody estadia datos){
        return ResponseEntity.status(HttpStatus.ok).body(
            servicio.guardarEstadia(datos)
        );
    }

    //Funcion controladora del servicio de listar todas las estadias
    @GetMapping
    public ResponseEntity<?>controladorListarTodo(){
        return ResponseEntity.status(HttpStatus.ok).body(
            servicio.listarEstadias()
        );
    }

    //funcion controladora del servicio modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> controladorModificar(@PathVariable Integer id, @RequestBody estadia datos){
        return ResponseEntity.status(HttpStatus.OK).body(
            servicio.modificarEstadia(id,datos)
        );
    }
    //funcion controladora del servicio eliminar 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> controladorEliminar(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
            servicio.eliminarEstadia(id)
        );
    }
    //funcion controladora del servicio buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> controladorBuscarporId(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
            servicio.buscarEstadiaPorId(id)
        );
    }



}
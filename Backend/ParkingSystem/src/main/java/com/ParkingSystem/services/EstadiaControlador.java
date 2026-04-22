package com.ParkingSystem.controladores;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.ParkingSystem.models.estadia;
import com.ParkingSystem.services.EstadiaService;

@RestController
@RequestMapping("/ParkingSystem/v1/estadias")
public class EstadiaControlador {
    
    //Inyectar el servicio correspondiente
    @Autowired
    private EstadiaService servicio;

    //para cada servicio ofrecido se debe programar una funcion
    //esa funcion recibira las peticiones del pedido y respondera

    //funcion controladora del servicio guardar estadia
    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody estadia datos){
        return ResponseEntity.status(HttpStatus.ok).body(
            servicio.guardarEstadia(datos)
        );
    }
    

    //funcion controladora del servicio de listar todos los usuarios
    @GetMapping
    public ResponseEntity<?>controladorListarTodo(){
        return ResponseEntity.status(HttpStatus.ok).body(
            servicio.listarEstadias()
        );
    }


}
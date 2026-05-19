package com.ParkingSystem.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private EstadiaService estadiaService;

    //para cada servicio ofrecido se debe programar una funcion
    //esa funcion recibira las peticiones del pedido y respondera

    //Funcion controladora del servicio de guardar una estadia
    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody Estadia datos){
        return ResponseEntity.status(HttpStatus.CREATED).body(
            estadiaService.guardarEstadia(datos)
        );
    }

    //Funcion controladora del servicio de listar todas las estadias
    @GetMapping
    public ResponseEntity<?> controladorListarTodo() {
        return ResponseEntity.status(HttpStatus.OK).body(
            estadiaService.listarEstadias()
        );
    }

    //funcion controladora del servicio modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> controladorModificar(@PathVariable Integer id, @RequestBody Estadia datosActualizados){
        return ResponseEntity.status(HttpStatus.OK).body(
            estadiaService.actualizarEstadia(id,datosActualizados)
        );
    }
    
    //funcion controladora del servicio eliminar 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> controladorEliminar(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
            estadiaService.eliminarEstadia(id)
        );
    }
    //funcion controladora del servicio buscar por id la estadia
    @GetMapping("/{id}")
    public ResponseEntity<?> controladorBuscarporId(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
            estadiaService.obtenerEstadiaById(id)
        );
    }

    //función controladora del servicio buscar estadia por vehiculo
    @GetMapping("/vehiculo/{id}")
    public ResponseEntity<?> controladorBuscarPorVehiculo(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
            estadiaService.obtenerEstadiasActivasPorVehiculo(id)
        );
    }

    //Función controladora buscar estadia por celda
    @GetMapping("/celda/{id}")
    public ResponseEntity<?> controladorBuscarPorCelda(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
            estadiaService.obtenerEstadiasPorCelda(id)
        );
    }
}
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

    //Funcion controladora del servicio de guardar una estadia
    @PostMapping
    public ResponseEntity<?> controllerGuardar(@RequestBody estadia estadia){
        return ResponseEntity.status(HttpStatus.OK).body(
            servicio.guardarEstadia(estadia)
        );
    }

    //Funcion controladora del servicio de listar todas las estadias
    @GetMapping
    public ResponseEntity<?> controllerListar(){
        return ResponseEntity.status(HttpStatus.OK).body(
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
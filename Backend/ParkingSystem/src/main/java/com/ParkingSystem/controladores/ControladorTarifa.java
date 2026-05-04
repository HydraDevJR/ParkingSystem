package com.ParkingSystem.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ParkingSystem.models.Tarifa;
import com.ParkingSystem.services.TarifaServicie;

@RestController
@RequestMapping("/ParkingSystem/v1/tarifas")
public class ControladorTarifa {

    @Autowired
    TarifaServicie servicio;

    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody Tarifa datos){
        return ResponseEntity.status(HttpStatus.OK).body(servicio.guardar_tarifa(datos));
    }

    @GetMapping
    public ResponseEntity<?> controladorListar(){
        return ResponseEntity.status(HttpStatus.OK).body(servicio.listar_tarifas());
    }

    // controlador para modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> controladorModificar(@PathVariable Integer id, @RequestBody Tarifa datos){
        return ResponseEntity.status(HttpStatus.OK).body(servicio.modificar_tarifa(id, datos));
    }

    // controlador para eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> controladorEliminar(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(servicio.eliminar_tarifa(id));
    }

    // controlador para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> controladorBuscarPorId(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(servicio.buscar_tarifa_por_id(id));
    }

}
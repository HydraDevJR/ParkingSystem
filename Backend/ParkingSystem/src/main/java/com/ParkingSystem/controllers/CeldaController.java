package com.ParkingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ParkingSystem.models.Celda;
import com.ParkingSystem.services.CeldaService;


@RestController
@RequestMapping("/parkingsystem/v1/celdas")
public class CeldaController {

    @Autowired
    private CeldaService celdaService;

    // Guardar una celda
    @PostMapping
    public ResponseEntity<?> guardarCelda(Celda celda) {
        return ResponseEntity.status(HttpStatus.OK).body(
            celdaService.guardarCelda(celda)
        );
    }

    // Listar todas las celdas
    @GetMapping
    public ResponseEntity<?> listarCeldas() {
        return ResponseEntity.status(HttpStatus.OK).body(
            celdaService.listarCeldas()
        );
    }

    // Controlador para modificar una celda
    @PutMapping("/{id}")
    public ResponseEntity<?> controladorModificarCelda(Integer id, Celda datosActualizados) {
        return ResponseEntity.status(HttpStatus.OK).body(
            celdaService.modificarCelda(id, datosActualizados)
        );
    }

    // controlador para eliminar una celda
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCelda(Integer id) {
        celdaService.eliminarCelda(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Controlador para buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> controladorBuscarPorId(Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(
            celdaService.buscarCeldaPorId(id)
        );
    }

}
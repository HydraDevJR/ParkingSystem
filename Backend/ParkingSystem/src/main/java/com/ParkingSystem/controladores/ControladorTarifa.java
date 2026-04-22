package com.ParkingSystem.controladores;

import com.ParkingSystem.models.Tarifa;
import com.ParkingSystem.services.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ParkingSystem/v1/tarifas")
public class ControladorTarifa {

    @Autowired
    private TarifaService servicio;

    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody Tarifa datos) {
        return ResponseEntity.status(HttpStatus.OK).body(
                servicio.guardarTarifa(datos)
        );
    }

    @GetMapping
    public ResponseEntity<?> controladorListar() {
        return ResponseEntity.status(HttpStatus.OK).body(
                servicio.Listar_Tarifas()
        );
    }
}
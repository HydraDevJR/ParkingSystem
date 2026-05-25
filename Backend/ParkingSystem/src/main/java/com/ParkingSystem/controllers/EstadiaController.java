package com.ParkingSystem.controllers;

import com.ParkingSystem.models.utils.EstadoEstadia;
import com.ParkingSystem.models.Estadia;
import com.ParkingSystem.services.EstadiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/parkingsystem/v1/estadias")
public class EstadiaController {

    @Autowired
    private EstadiaService estadiaService;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Estadia estadia) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(estadiaService.guardarEstadia(estadia));
    }

    @GetMapping
    public ResponseEntity<?> listarTodas() {
        return ResponseEntity.ok(estadiaService.listarEstadias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(estadiaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Estadia estadia) {
        return ResponseEntity.ok(estadiaService.actualizarEstadia(id, estadia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        estadiaService.eliminarEstadia(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizar(@PathVariable Integer id) {
        return ResponseEntity.ok(estadiaService.finalizarEstadia(id));
    }

    @GetMapping("/vehiculo/{vehiculoId}")
    public ResponseEntity<?> buscarPorVehiculo(@PathVariable Integer vehiculoId) {
        return ResponseEntity.ok(estadiaService.buscarPorVehiculo(vehiculoId));
    }

    @GetMapping("/celda/{celdaId}")
    public ResponseEntity<?> buscarPorCelda(@PathVariable Integer celdaId) {
        return ResponseEntity.ok(estadiaService.buscarPorCelda(celdaId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable EstadoEstadia estado) {
        return ResponseEntity.ok(estadiaService.buscarPorEstado(estado));
    }

    @GetMapping("/activas")
    public ResponseEntity<?> buscarActivas() {
        return ResponseEntity.ok(estadiaService.buscarActivas());
    }

    @GetMapping("/rango-fechas")
    public ResponseEntity<?> buscarPorRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(estadiaService.buscarPorRangoFechas(inicio, fin));
    }

    @GetMapping("/finalizadas-rango")
    public ResponseEntity<?> buscarFinalizadasPorRango(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(estadiaService.buscarFinalizadasPorRango(inicio, fin));
    }

    @GetMapping("/vehiculo/{vehiculoId}/activas")
    public ResponseEntity<?> buscarActivasPorVehiculo(@PathVariable Integer vehiculoId) {
        return ResponseEntity.ok(estadiaService.buscarActivasPorVehiculo(vehiculoId));
    }

    @GetMapping("/tarifa/{tarifaId}")
    public ResponseEntity<?> buscarPorTarifa(@PathVariable Integer tarifaId) {
        return ResponseEntity.ok(estadiaService.buscarPorTarifa(tarifaId));
    }
}
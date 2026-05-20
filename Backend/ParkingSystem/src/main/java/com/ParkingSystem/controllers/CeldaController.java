package com.ParkingSystem.controllers;

import com.ParkingSystem.models.Celda;
import com.ParkingSystem.models.utils.EstadoCelda;
import com.ParkingSystem.models.utils.TipoVehiculo;
import com.ParkingSystem.services.CeldaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingsystem/v1/celdas")
public class CeldaController {

    @Autowired
    private CeldaService celdaService;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Celda celda) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(celdaService.guardarCelda(celda));
    }

    @GetMapping
    public ResponseEntity<?> listarTodas() {
        return ResponseEntity.ok(celdaService.listarCeldas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(celdaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Celda celda) {
        return ResponseEntity.ok(celdaService.actualizarCelda(id, celda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        celdaService.eliminarCelda(id);
        return ResponseEntity.noContent().build();
    }

    // ========== Endpoints adicionales ==========
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable String codigo) {
        return celdaService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable EstadoCelda estado) {
        return ResponseEntity.ok(celdaService.buscarPorEstado(estado));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> buscarPorTipo(@PathVariable TipoVehiculo tipo) {
        return ResponseEntity.ok(celdaService.buscarPorTipoVehiculo(tipo));
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorCodigoYEstado(
            @RequestParam String codigo,
            @RequestParam EstadoCelda estado) {
        return celdaService.buscarPorCodigoYEstado(codigo, estado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/disponibles/{tipo}")
    public ResponseEntity<?> buscarDisponiblesPorTipo(@PathVariable TipoVehiculo tipo) {
        return ResponseEntity.ok(celdaService.buscarPorTipoYEstado(tipo, EstadoCelda.DISPONIBLE));
    }
}
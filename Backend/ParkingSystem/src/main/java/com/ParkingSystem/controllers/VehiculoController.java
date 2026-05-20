package com.ParkingSystem.controllers;

import com.ParkingSystem.models.Vehiculo;
import com.ParkingSystem.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingsystem/v1/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    // CREAR
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Vehiculo vehiculo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculoService.guardarVehiculo(vehiculo));
    }

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(vehiculoService.listarVehiculos());
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vehiculoService.buscarPorId(id));
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Vehiculo vehiculo) {
        return ResponseEntity.ok(vehiculoService.actualizarVehiculo(id, vehiculo));
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        vehiculoService.eliminarVehiculo(id);
        return ResponseEntity.noContent().build();
    }

    // BUSCAR POR PLACA EXACTA
    @GetMapping("/placa/{placa}")
    public ResponseEntity<?> buscarPorPlaca(@PathVariable String placa) {
        return vehiculoService.buscarPorPlaca(placa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // BUSCAR POR MARCA
    @GetMapping("/marca/{marca}")
    public ResponseEntity<?> buscarPorMarca(@PathVariable String marca) {
        return ResponseEntity.ok(vehiculoService.buscarPorMarca(marca));
    }

    // BUSCAR POR PLACA QUE CONTENGA (ej: /buscar?placa=ABC)
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorPlacaContiene(@RequestParam String placa) {
        return ResponseEntity.ok(vehiculoService.buscarPorPlacaConteniendo(placa));
    }

    // BUSCAR POR MODELO
    @GetMapping("/modelo/{modelo}")
    public ResponseEntity<?> buscarPorModelo(@PathVariable String modelo) {
        return ResponseEntity.ok(vehiculoService.buscarPorModelo(modelo));
    }
}
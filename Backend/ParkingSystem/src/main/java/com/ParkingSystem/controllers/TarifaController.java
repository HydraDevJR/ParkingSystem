package com.ParkingSystem.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ParkingSystem.models.Tarifa;
import com.ParkingSystem.models.utils.TipoTarifa;
import com.ParkingSystem.services.TarifaServicie;

@RestController
@RequestMapping("/parkingsystem/v1/tarifas")
public class TarifaController {

    @Autowired
    private TarifaServicie servicio;

    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody Tarifa datos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.guardar_tarifa(datos));
    }

    @GetMapping
    public ResponseEntity<?> controladorListar() {
        return ResponseEntity.ok(servicio.listar_tarifas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> controladorBuscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.buscar_tarifa_por_id(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> controladorModificar(@PathVariable Integer id, @RequestBody Tarifa datos) {
        return ResponseEntity.ok(servicio.modificar_tarifa(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> controladorEliminar(@PathVariable Integer id) {
        servicio.eliminar_tarifa(id);
        return ResponseEntity.noContent().build();
    }

    // ========== Nuevos endpoints para consultas personalizadas ==========
    
    @GetMapping("/activas")
    public ResponseEntity<?> listarActivas() {
        return ResponseEntity.ok(servicio.tarifas_activas());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> buscarPorTipo(@PathVariable TipoTarifa tipo) {
        return servicio.buscar_por_tipo(tipo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo-activo/{tipo}")
    public ResponseEntity<?> buscarPorTipoActivo(@PathVariable TipoTarifa tipo) {
        return servicio.buscar_por_tipo_activo(tipo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/por-valor")
    public ResponseEntity<?> buscarPorValorMenorIgual(@RequestParam BigDecimal maxValor) {
        return ResponseEntity.ok(servicio.buscar_por_valor_menor_o_igual(maxValor));
    }
}
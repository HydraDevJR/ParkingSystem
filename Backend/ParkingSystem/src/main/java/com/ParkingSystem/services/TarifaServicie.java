package com.ParkingSystem.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ParkingSystem.models.Tarifa;
import com.ParkingSystem.models.Tarifa.TipoTarifa;
import com.ParkingSystem.repositories.ITarifaRepository;

@Service
public class TarifaServicie {

    @Autowired
    private ITarifaRepository repositorio;

    // Guardar tarifa
    public Tarifa guardar_tarifa(Tarifa datosTarifa) {
        validarTarifa(datosTarifa);
        
        // Opcional: evitar duplicados de tipo (si solo debe haber una tarifa por tipo)
        if (repositorio.findByTipo(datosTarifa.getTipo()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe una tarifa con el tipo: " + datosTarifa.getTipo());
        }
        
        return repositorio.save(datosTarifa);
    }

    // Listar todas
    public List<Tarifa> listar_tarifas() {
        return repositorio.findAll();
    }

    // Modificar tarifa
    public Tarifa modificar_tarifa(Integer id, Tarifa datosNuevos) {
        Tarifa tarifaExistente = buscar_tarifa_por_id(id); // ya lanza 404 si no existe
        
        // Validar los nuevos datos
        if (datosNuevos.getTipo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de tarifa es obligatorio");
        }
        if (datosNuevos.getValor() == null || datosNuevos.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El valor debe ser mayor a cero");
        }
        
        // Si cambia el tipo, validar que no exista ya otra tarifa con ese tipo
        if (!tarifaExistente.getTipo().equals(datosNuevos.getTipo()) &&
            repositorio.findByTipo(datosNuevos.getTipo()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe otra tarifa con el tipo: " + datosNuevos.getTipo());
        }
        
        tarifaExistente.setTipo(datosNuevos.getTipo());
        tarifaExistente.setValor(datosNuevos.getValor());
        tarifaExistente.setActivo(datosNuevos.getActivo());
        return repositorio.save(tarifaExistente);
    }

    // Eliminar tarifa
    public boolean eliminar_tarifa(Integer id) {
        Tarifa tarifa = buscar_tarifa_por_id(id); // si no existe, lanza 404
        repositorio.delete(tarifa);
        return true;
    }

    // Buscar por id
    public Tarifa buscar_tarifa_por_id(Integer id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Tarifa no encontrada con id: " + id));
    }

    // ========== Métodos adicionales para usar el repositorio ==========
    public List<Tarifa> tarifas_activas() {
        return repositorio.findByActivoTrue();
    }

    public Optional<Tarifa> buscar_por_tipo(TipoTarifa tipo) {
        return repositorio.findByTipo(tipo);
    }

    public Optional<Tarifa> buscar_por_tipo_activo(TipoTarifa tipo) {
        return repositorio.findByTipoAndActivoTrue(tipo);
    }

    public List<Tarifa> buscar_por_valor_menor_o_igual(BigDecimal valorMaximo) {
        return repositorio.findByValorLessThanEqual(valorMaximo);
    }

    // Método auxiliar de validación (DRY)
    private void validarTarifa(Tarifa tarifa) {
        if (tarifa.getTipo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de tarifa es obligatorio");
        }
        if (tarifa.getValor() == null || tarifa.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El valor de la tarifa debe ser mayor a cero");
        }
        // activo puede ser null? si es null, se puede establecer por defecto true
        if (tarifa.getActivo() == null) {
            tarifa.setActivo(true);
        }
    }
}
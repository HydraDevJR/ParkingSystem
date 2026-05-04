package com.ParkingSystem.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ParkingSystem.models.Celda;
import com.ParkingSystem.repositories.ICeldaRepositorio;

@Service
public class CeldaService {

    @Autowired
    private ICeldaRepositorio celdaRepositorio;

    // Guardar una Celda
    public Celda guardarCelda(Celda celda) {
        // Validar que no exista
        if (celda.getId() != null && celdaRepositorio.findById(celda.getId()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La celda ya existe"
            );
        }

        // Validar número de celda
        if (celda.getNumero() == null || celda.getNumero() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El número de la celda es obligatorio y debe ser mayor a 0"
            );
        }

        // Validar unicidad del número de celda
        if (celdaRepositorio.findByNumero(celda.getNumero()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Ya existe una celda con ese número"
            );
        }

        // Validar piso
        if (celda.getPiso() == null || celda.getPiso() < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El número de piso es obligatorio y debe ser mayor o igual a 0"
            );
        }

        // Validar tipo de vehículo
        if (celda.getTipoVehiculo() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El tipo de vehículo es obligatorio"
            );
        }

        // Validar estado de la celda
        if (celda.getEstado() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El estado de la celda es obligatorio"
            );
        }

        return celdaRepositorio.save(celda);
    }

    // Listar todas las celdas
    public List<Celda> listarCeldas() {
        return celdaRepositorio.findAll();
    }

    // Buscar una celda por ID
    public Celda buscarCeldaPorId(UUID id) {
        return celdaRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Celda no encontrada con ID: " + id
                ));
    }

    // Modificar una celda
    public Celda modificarCelda(UUID id, Celda celdaActualizada) {
        Celda celdaExistente = buscarCeldaPorId(id);

        // Actualizar número (si se envía y es diferente)
        if (celdaActualizada.getNumero() != null) {
            if (!celdaExistente.getNumero().equals(celdaActualizada.getNumero()) &&
                celdaRepositorio.findByNumero(celdaActualizada.getNumero()).isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Ya existe una celda con el número " + celdaActualizada.getNumero()
                );
            }
            celdaExistente.setNumero(celdaActualizada.getNumero());
        }

        // Actualizar piso
        if (celdaActualizada.getPiso() != null && celdaActualizada.getPiso() >= 0) {
            celdaExistente.setPiso(celdaActualizada.getPiso());
        }

        // Actualizar tipo de vehículo
        if (celdaActualizada.getTipoVehiculo() != null) {
            celdaExistente.setTipoVehiculo(celdaActualizada.getTipoVehiculo());
        }

        // Actualizar estado
        if (celdaActualizada.getEstado() != null) {
            celdaExistente.setEstado(celdaActualizada.getEstado());
        }

        return celdaRepositorio.save(celdaExistente);
    }

    // Eliminar una Celda por ID
    public void eliminarCelda(UUID id) {
        if (!celdaRepositorio.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "La celda no existe"
            );
        }
        celdaRepositorio.deleteById(id);
    }
}
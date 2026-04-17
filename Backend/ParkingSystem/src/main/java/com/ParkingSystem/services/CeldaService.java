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
        // Validar la operación

        if (celda.getId() != null && celdaRepositorio.findById(celda.getId()).isPresent()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La celda ya existe"
            );
        }

        if (celda.getNumero() == null || celda.getNumero() <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El número de la celda es obligatorio y debe ser mayor a 0"
            );
        }

        if (celdaRespositorio.findByNumero(celda.getNumero()).isPresent()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Ya existe una celda con ese número"
            );
        }

        if (celda.getPiso() == null || celda.getPiso() < 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El número de piso es obligatorio y debe ser mayor o igual a 0"
            );
        }

        if (celda.getTipo() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El tipo de vehículo es obligatorio"
            );
        }

        if (celda.getEstado() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El estado de la celda es obligatorio"
            );
        }

        if (celda.getParqueadero() == null) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El parqueadero de la celda es obligatorio"
            );
        }

        return celdaRepositorio.save(celda);
    }

    // Listar todas las celdas
    public List<Celda> listarCeldas() {
        return celdaRepositorio.findAll();
    }

    // Eliminar una Celda por ID
    public void eliminarCelda(UUID id) {
        if (!celdaRepositorio.findById(id).isPresent()) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "La celda no existe"
            );
        }

        celdaRepositorio.deleteById(id);
    }

    // Modificar una celda

    // Buscar una celda por ID


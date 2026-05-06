package com.ParkingSystem.services;

import java.util.List;

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

        //validar codigo de celda
        if (celda.getCodigo() == null || celda.getCodigo().isBlank() || celda.getCodigo().isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El código de la celda es obligatorio"
            );
        }

        // Validar que el código de la celda sea único
        if (celdaRepositorio.findByCodigo(celda.getCodigo()).isPresent()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Ya existe una celda con el código " + celda.getCodigo()
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
    public Celda buscarCeldaPorId(Integer id) {

        Optional<Celda> buscandoCelda = celdaRepositorio.findById(id);
        if (buscandoCelda.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Celda no encontrada."
            );

        } else {
            return buscandoCelda.get();
        }
    }

    // Modificar una celda
    public Celda modificarCelda(Integer id, Celda celdaActualizada) {
        Optional<Celda> buscandoCelda = celdaRepositorio.findById(id);
        if (buscandoCelda.isEmpty()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Celda no encontrada con ID: " + id
            );

        } else {
            Celda celdaEncontrada= buscandoCelda.get();
            //modifcación de datos de la celda
            celdaEncontrada.setCodigo(celdaActualizada.getCodigo());
            celdaEncontrada.setTipoVehiculo(celdaActualizada.getTipoVehiculo());
            celdaEncontrada.setEstado(celdaActualizada.getEstado());
            return celdaRepositorio.save(celdaEncontrada);
        }
    }

    // Eliminar una Celda por ID
    public boolean eliminarCelda(Integer id) {

        Optional<Celda> buscandoCelda = celdaRepositorio.findById(id);
        if (buscandoCelda.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Celda no encontrada con ID:" + id
            );
        }else{
            celdaRepositorio.deleteById(id);
            return true;
        }
    }
}
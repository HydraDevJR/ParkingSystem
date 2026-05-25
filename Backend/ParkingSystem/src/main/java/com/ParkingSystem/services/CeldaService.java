package com.ParkingSystem.services;

import com.ParkingSystem.models.Celda;
import com.ParkingSystem.models.utils.EstadoCelda;
import com.ParkingSystem.models.utils.TipoVehiculo;
import com.ParkingSystem.repositories.ICeldaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CeldaService {

    @Autowired
    private ICeldaRepository celdaRepository;

    // Guardar
    @Transactional
    public Celda guardarCelda(Celda celda) {
        validarCelda(celda);

        if (celdaRepository.findByCodigo(celda.getCodigo()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe una celda con el código: " + celda.getCodigo());
        }
        return celdaRepository.save(celda);
    }

    // Listar todas
    public List<Celda> listarCeldas() {
        return celdaRepository.findAll();
    }

    // Buscar por ID
    public Celda buscarPorId(Integer id) {
        return celdaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Celda no encontrada con id: " + id));
    }

    // Actualizar
    @Transactional
    public Celda actualizarCelda(Integer id, Celda datosNuevos) {
        Celda existente = buscarPorId(id);

        // Validar código duplicado si cambió
        if (!existente.getCodigo().equals(datosNuevos.getCodigo()) &&
                celdaRepository.findByCodigo(datosNuevos.getCodigo()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe otra celda con el código: " + datosNuevos.getCodigo());
        }

        existente.setCodigo(datosNuevos.getCodigo());
        existente.setTipoVehiculo(datosNuevos.getTipoVehiculo());
        existente.setEstado(datosNuevos.getEstado());
        return celdaRepository.save(existente);
    }

    // Eliminar
    @Transactional
    public void eliminarCelda(Integer id) {
        Celda celda = buscarPorId(id);
        // Validar que no tenga estadías asociadas antes de eliminar
        if (celda.getEstadias() != null && !celda.getEstadias().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "No se puede eliminar la celda porque tiene estadías asociadas");
        }
        celdaRepository.delete(celda);
    }

    // ========== Métodos adicionales del repositorio ==========
    public Optional<Celda> buscarPorCodigo(String codigo) {
        return celdaRepository.findByCodigo(codigo);
    }

    public List<Celda> buscarPorEstado(EstadoCelda estado) {
        return celdaRepository.findByEstado(estado);
    }

    public List<Celda> buscarPorTipoVehiculo(TipoVehiculo tipo) {
        return celdaRepository.findByTipoVehiculo(tipo);
    }

    public Optional<Celda> buscarPorCodigoYEstado(String codigo, EstadoCelda estado) {
        return celdaRepository.findByCodigoAndEstado(codigo, estado);
    }

    public List<Celda> buscarPorTipoYEstado(TipoVehiculo tipo, EstadoCelda estado) {
        return celdaRepository.findByTipoVehiculoAndEstado(tipo, estado);
    }

    // Validación centralizada
    private void validarCelda(Celda celda) {
        if (celda.getCodigo() == null || celda.getCodigo().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El código de la celda es obligatorio");
        }
        if (celda.getTipoVehiculo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de vehículo es obligatorio");
        }
        if (celda.getEstado() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El estado de la celda es obligatorio");
        }
    }
}
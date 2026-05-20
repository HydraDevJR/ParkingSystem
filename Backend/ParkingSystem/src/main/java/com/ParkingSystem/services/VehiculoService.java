package com.ParkingSystem.services;

import com.ParkingSystem.models.Vehiculo;
import com.ParkingSystem.repositories.IVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {

    @Autowired
    private IVehiculoRepository vehiculoRepository;

    // Guardar (CREAR)
    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
        // Validación de duplicado por placa
        if (vehiculoRepository.findByPlaca(vehiculo.getPlaca()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe un vehículo con la placa: " + vehiculo.getPlaca());
        }

        // Validaciones de campos obligatorios (con orden correcto para null)
        if (vehiculo.getPlaca() == null || vehiculo.getPlaca().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La placa es obligatoria");
        }
        if (vehiculo.getMarca() == null || vehiculo.getMarca().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La marca es obligatoria");
        }
        if (vehiculo.getModelo() == null || vehiculo.getModelo().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El modelo es obligatorio");
        }
        if (vehiculo.getColor() == null || vehiculo.getColor().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El color es obligatorio");
        }
        if (vehiculo.getTipoVehiculo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de vehículo es obligatorio");
        }
        if (vehiculo.getUsuario() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario propietario es obligatorio");
        }

        return vehiculoRepository.save(vehiculo);
    }

    // Listar todos
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }

    // Buscar por ID (lanzando excepción si no existe)
    public Vehiculo buscarPorId(Integer id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Vehículo no encontrado con id: " + id));
    }

    // Actualizar (PUT)
    public Vehiculo actualizarVehiculo(Integer id, Vehiculo vehiculoActualizado) {
        Vehiculo existente = buscarPorId(id);

        // Validar placa duplicada (si cambió la placa)
        if (!existente.getPlaca().equals(vehiculoActualizado.getPlaca()) &&
                vehiculoRepository.findByPlaca(vehiculoActualizado.getPlaca()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe otro vehículo con la placa: " + vehiculoActualizado.getPlaca());
        }

        // Actualizar campos
        existente.setPlaca(vehiculoActualizado.getPlaca());
        existente.setMarca(vehiculoActualizado.getMarca());
        existente.setModelo(vehiculoActualizado.getModelo());
        existente.setColor(vehiculoActualizado.getColor());
        existente.setTipoVehiculo(vehiculoActualizado.getTipoVehiculo());
        existente.setUsuario(vehiculoActualizado.getUsuario());
        // No se actualiza la lista de estancias aquí

        return vehiculoRepository.save(existente);
    }

    // Eliminar
    public void eliminarVehiculo(Integer id) {
        Vehiculo vehiculo = buscarPorId(id); // lanza 404 si no existe
        vehiculoRepository.delete(vehiculo);
    }

    // Métodos adicionales usando los query methods del repositorio
    public Optional<Vehiculo> buscarPorPlaca(String placa) {
        return vehiculoRepository.findByPlaca(placa);
    }

    public List<Vehiculo> buscarPorMarca(String marca) {
        return vehiculoRepository.findByMarca(marca);
    }

    public List<Vehiculo> buscarPorPlacaConteniendo(String partePlaca) {
        return vehiculoRepository.findByPlacaContaining(partePlaca);
    }

    public List<Vehiculo> buscarPorModelo(String modelo) {
        return vehiculoRepository.findByModelo(modelo);
    }
}
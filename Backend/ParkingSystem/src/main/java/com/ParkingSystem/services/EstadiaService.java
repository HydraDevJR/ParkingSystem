package com.ParkingSystem.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ParkingSystem.models.Celda;
import com.ParkingSystem.models.Estadia;
import com.ParkingSystem.models.Tarifa;
import com.ParkingSystem.models.Vehiculo;
import com.ParkingSystem.repositories.IEstadiaRepositorio;

@Service
public class EstadiaService {

    @Autowired
    private IEstadiaRepositorio estadiaRepositorio;

    // Guardar una Estadia
    public Estadia guardarEstadia(Estadia estadia) {
        // Validaciones
        if (estadia.getVehiculo() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El vehículo es obligatorio"
            );
        }

        if (estadia.getCelda() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La celda es obligatoria"
            );
        }

        if (estadia.getFechaInicio() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La fecha de inicio es obligatoria"
            );
        }

        if (estadia.getEstado() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El estado de la estadia es obligatorio"
            );
        }

        if (estadia.getTarifa() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La tarifa es obligatoria para registrar la estadia"
            );
        }

        if (estadia.getFechaFin() != null && estadia.getFechaFin().isBefore(estadia.getFechaInicio())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La fecha de fin no puede ser anterior a la fecha de inicio"
            );
        }

        return estadiaRepositorio.save(estadia);
    }

    // Listar todas las estadias
    public List<Estadia> listarEstadias() {
        return estadiaRepositorio.findAll();
    }

    // Obtener estadia por ID
    public Estadia obtenerEstadiaById(UUID id) {
        return estadiaRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "La estadia no existe"
                ));
    }

    // Eliminar estadia por ID
    public void eliminarEstadia(UUID id) {
        if (!estadiaRepositorio.findById(id).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "La estadia no existe"
            );
        }
        estadiaRepositorio.deleteById(id);
    }

    // Buscar estadias por vehículo
    public List<Estadia> obtenerEstadiasPorVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El vehículo no puede ser nulo"
            );
        }
        return estadiaRepositorio.findByVehiculo(vehiculo);
    }

    // Buscar estadias por celda
    public List<Estadia> obtenerEstadiasPorCelda(Celda celda) {
        if (celda == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La celda no puede ser nula"
            );
        }
        return estadiaRepositorio.findByCelda(celda);
    }

    // Buscar estadias por estado
    public List<Estadia> obtenerEstadiasPorEstado(Estadia.EstadoEstadia estado) {
        if (estado == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El estado no puede ser nulo"
            );
        }
        return estadiaRepositorio.findByEstado(estado);
    }

    // Obtener estadias activas (En_Curso)
    public List<Estadia> obtenerEstadiasActivas() {
        return estadiaRepositorio.findByEstadoOrderByFechaInicio(Estadia.EstadoEstadia.En_Curso);
    }

    // Buscar estadias por rango de fechas
    public List<Estadia> obtenerEstadiasPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Las fechas son obligatorias"
            );
        }

        if (fechaFin.isBefore(fechaInicio)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La fecha fin no puede ser anterior a la fecha inicio"
            );
        }

        return estadiaRepositorio.findByFechaInicioBetween(fechaInicio, fechaFin);
    }

    // Buscar estadias finalizadas por rango de fechas
    public List<Estadia> obtenerEstadiasFinalizadasPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Las fechas son obligatorias"
            );
        }

        if (fechaFin.isBefore(fechaInicio)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La fecha fin no puede ser anterior a la fecha inicio"
            );
        }

        return estadiaRepositorio.findByEstadoAndFechaFinBetween(
                Estadia.EstadoEstadia.Finalizada, fechaInicio, fechaFin
        );
    }

    // Obtener estadias activas de un vehículo
    public List<Estadia> obtenerEstadiasActivasPorVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El vehículo no puede ser nulo"
            );
        }
        return estadiaRepositorio.findByVehiculoAndEstado(vehiculo, Estadia.EstadoEstadia.En_Curso);
    }

    // Obtener estadias por vehículo y estado
    public List<Estadia> obtenerEstadiasPorVehiculoYEstado(Vehiculo vehiculo, Estadia.EstadoEstadia estado) {
        if (vehiculo == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El vehículo no puede ser nulo"
            );
        }

        if (estado == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El estado no puede ser nulo"
            );
        }

        return estadiaRepositorio.findByVehiculoAndEstado(vehiculo, estado);
    }

    // Obtener estadias por celda y estado
    public List<Estadia> obtenerEstadiasPorCeldaYEstado(Celda celda, Estadia.EstadoEstadia estado) {
        if (celda == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La celda no puede ser nula"
            );
        }

        if (estado == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El estado no puede ser nulo"
            );
        }

        return estadiaRepositorio.findByCeldaAndEstado(celda, estado);
    }

    // Actualizar estadia
    public Estadia actualizarEstadia(UUID id, Estadia estadiaActualizada) {
        Estadia estadia = obtenerEstadiaById(id);

        if (estadiaActualizada.getFechaFin() != null) {
            if (estadiaActualizada.getFechaFin().isBefore(estadia.getFechaInicio())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "La fecha de fin no puede ser anterior a la fecha de inicio"
                );
            }
            estadia.setFechaFin(estadiaActualizada.getFechaFin());
        }

        if (estadiaActualizada.getEstado() != null) {
            estadia.setEstado(estadiaActualizada.getEstado());
        }

        return estadiaRepositorio.save(estadia);
    }

    // Obtener estadias por tarifa
    public List<Estadia> obtenerEstadiasPorTarifa(Tarifa tarifa) {
        if (tarifa == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La tarifa no puede ser nula"
            );
        }
        return estadiaRepositorio.findByTarifa(tarifa);
    }

    // Obtener estadias activas por tarifa
    public List<Estadia> obtenerEstadiasActivasPorTarifa(Tarifa tarifa) {
        if (tarifa == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La tarifa no puede ser nula"
            );
        }
        return estadiaRepositorio.findByTarifaAndEstado(tarifa, Estadia.EstadoEstadia.En_Curso);
    }

    // Obtener estadias finalizadas por tarifa
    public List<Estadia> obtenerEstadiasFinalizadasPorTarifa(Tarifa tarifa) {
        if (tarifa == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "La tarifa no puede ser nula"
            );
        }
        return estadiaRepositorio.findByTarifaAndEstado(tarifa, Estadia.EstadoEstadia.Finalizada);
    }
}

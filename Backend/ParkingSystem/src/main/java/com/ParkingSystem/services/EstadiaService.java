package com.ParkingSystem.services;

import com.ParkingSystem.models.*;
import com.ParkingSystem.models.utils.*;
import com.ParkingSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstadiaService {

    @Autowired
    private IEstadiaRepository estadiaRepository;

    @Autowired
    private IVehiculoRepository vehiculoRepository;

    @Autowired
    private ICeldaRepository celdaRepository;

    @Autowired
    private ITarifaRepository tarifaRepository;

    @Transactional
    public Estadia guardarEstadia(Estadia estadia) {
        validarEstadia(estadia);

        Celda celda = celdaRepository.findById(estadia.getCelda().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "La celda no existe"));
        estadia.setCelda(celda);

        // Validar que la celda esté disponible si la estadia inicia ahora
        if (estadia.getEstado() == EstadoEstadia.EN_CURSO) {

            if (celda.getEstado() != EstadoCelda.DISPONIBLE) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "La celda " + celda.getCodigo() + " no está disponible");
            }
            // Marcar celda como ocupada
            celda.setEstado(EstadoCelda.OCUPADA);
            celdaRepository.save(celda);
        }

        return estadiaRepository.save(estadia);
    }

    public List<Estadia> listarEstadias() {
        return estadiaRepository.findAll();
    }

    public Estadia buscarPorId(Integer id) {
        return estadiaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Estadía no encontrada con id: " + id));
    }

    @Transactional
    public Estadia actualizarEstadia(Integer id, Estadia datosNuevos) {
        Estadia existente = buscarPorId(id);

        // Si se cambia la celda y la nueva celda está ocupada (y la estadia está en
        // curso)
        if (datosNuevos.getCelda() != null && !existente.getCelda().equals(datosNuevos.getCelda())) {
            Celda nuevaCelda = datosNuevos.getCelda();
            if (existente.getEstado() == EstadoEstadia.EN_CURSO &&
                    nuevaCelda.getEstado() == EstadoCelda.OCUPADA) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "La nueva celda ya está ocupada");
            }
            // Liberar celda anterior
            Celda viejaCelda = existente.getCelda();
            if (existente.getEstado() == EstadoEstadia.EN_CURSO) {
                viejaCelda.setEstado(EstadoCelda.DISPONIBLE);
                celdaRepository.save(viejaCelda);
            }
            // Marcar nueva celda como ocupada
            if (existente.getEstado() == EstadoEstadia.EN_CURSO) {
                nuevaCelda.setEstado(EstadoCelda.OCUPADA);
                celdaRepository.save(nuevaCelda);
            }
            existente.setCelda(nuevaCelda);
        }

        existente.setVehiculo(datosNuevos.getVehiculo());
        existente.setFechaInicio(datosNuevos.getFechaInicio());
        existente.setFechaFin(datosNuevos.getFechaFin());
        existente.setEstado(datosNuevos.getEstado());
        existente.setTarifa(datosNuevos.getTarifa());

        // Si se está finalizando la estadia, calcular valor y liberar celda
        if (datosNuevos.getEstado() == EstadoEstadia.FINALIZADA &&
                existente.getEstado() != EstadoEstadia.FINALIZADA) {
            calcularYAsignarValorTotal(existente);
            liberarCelda(existente.getCelda());
        }

        return estadiaRepository.save(existente);
    }

    @Transactional
    public void eliminarEstadia(Integer id) {
        Estadia estadia = buscarPorId(id);
        // Si está en curso, liberar la celda antes de eliminar
        if (estadia.getEstado() == EstadoEstadia.EN_CURSO) {
            liberarCelda(estadia.getCelda());
        }
        estadiaRepository.delete(estadia);
    }

    // ==================== OPERACIONES ESPECIALES ====================

    /**
     * Finaliza una estadía activa, calcula el valor total y libera la celda.
     */
    @Transactional
    public Estadia finalizarEstadia(Integer id) {
        Estadia estadia = buscarPorId(id);
        if (estadia.getEstado() != EstadoEstadia.EN_CURSO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Solo se pueden finalizar estadías en curso");
        }
        estadia.setFechaFin(LocalDateTime.now());
        estadia.setEstado(EstadoEstadia.FINALIZADA);
        calcularYAsignarValorTotal(estadia);
        liberarCelda(estadia.getCelda());
        return estadiaRepository.save(estadia);
    }

    private void calcularYAsignarValorTotal(Estadia estadia) {
        if (estadia.getFechaFin() == null || estadia.getFechaInicio() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No se puede calcular el valor sin fecha de inicio y fin");
        }
        long horas = Duration.between(estadia.getFechaInicio(), estadia.getFechaFin()).toHours();
        if (horas < 1)
            horas = 1; // mínimo una hora

        BigDecimal valorHora = estadia.getTarifa().getValor();
        BigDecimal total = valorHora.multiply(BigDecimal.valueOf(horas));
        estadia.setValorTotal(total);
    }

    private void liberarCelda(Celda celda) {
        celda.setEstado(EstadoCelda.DISPONIBLE);
        celdaRepository.save(celda);
    }

    // ==================== VALIDACIONES ====================

    private void validarEstadia(Estadia estadia) {
        if (estadia.getVehiculo() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El vehículo es obligatorio");
        if (estadia.getCelda() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La celda es obligatoria");
        if (estadia.getFechaInicio() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de inicio es obligatoria");
        if (estadia.getEstado() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El estado es obligatorio");
        if (estadia.getTarifa() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La tarifa es obligatoria");
        if (estadia.getFechaFin() != null && estadia.getFechaFin().isBefore(estadia.getFechaInicio()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de fin no puede ser anterior a la fecha de inicio");
    }

    // ==================== MÉTODOS DEL REPOSITORIO EXPUESTOS ====================

    public List<Estadia> buscarPorVehiculo(Integer vehiculoId) {
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Vehículo no encontrado con id: " + vehiculoId));
        return estadiaRepository.findByVehiculo(vehiculo);
    }

    public List<Estadia> buscarPorCelda(Integer celdaId) {
        Celda celda = celdaRepository.findById(celdaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Celda no encontrada con id: " + celdaId));
        return estadiaRepository.findByCelda(celda);
    }

    public List<Estadia> buscarPorEstado(EstadoEstadia estado) {
        return estadiaRepository.findByEstado(estado);
    }

    public List<Estadia> buscarActivas() {
        return estadiaRepository.findByEstadoOrderByFechaInicio(EstadoEstadia.EN_CURSO);
    }

    public List<Estadia> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        if (inicio == null || fin == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas son obligatorias");
        if (fin.isBefore(inicio))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha fin no puede ser anterior a la inicio");
        return estadiaRepository.findByFechaInicioBetween(inicio, fin);
    }

    public List<Estadia> buscarFinalizadasPorRango(LocalDateTime inicio, LocalDateTime fin) {
        if (inicio == null || fin == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Las fechas son obligatorias");
        if (fin.isBefore(inicio))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha fin no puede ser anterior a la inicio");
        return estadiaRepository.findByEstadoAndFechaFinBetween(EstadoEstadia.FINALIZADA, inicio, fin);
    }

    public List<Estadia> buscarActivasPorVehiculo(Integer vehiculoId) {
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehículo no encontrado"));
        return estadiaRepository.findByVehiculoAndEstado(vehiculo, EstadoEstadia.EN_CURSO);
    }

    public List<Estadia> buscarPorTarifa(Integer tarifaId) {
        Tarifa tarifa = tarifaRepository.findById(tarifaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa no encontrada"));
        return estadiaRepository.findByTarifa(tarifa);
    }
}
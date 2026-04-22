package com.ParkingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ParkingSystem.models.Estadia;
import com.ParkingSystem.models.Vehiculo;
import com.ParkingSystem.models.Celda;
import com.ParkingSystem.models.Tarifa;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IEstadiaRepositorio extends JpaRepository<Estadia, UUID> {

    // Buscar estadia por ID
    Optional<Estadia> findById(UUID id);

    // Buscar todas las estadias de un vehículo
    List<Estadia> findByVehiculo(Vehiculo vehiculo);

    // Buscar todas las estadias de una celda
    List<Estadia> findByCelda(Celda celda);

    // Buscar estadias por estado
    List<Estadia> findByEstado(Estadia.EstadoEstadia estado);

    // Buscar estadias activas (En_Curso)
    List<Estadia> findByEstadoOrderByFechaInicio(Estadia.EstadoEstadia estado);

    // Buscar estadias por rango de fechas
    List<Estadia> findByFechaInicioBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Buscar estadias finalizadas por rango de fechas
    List<Estadia> findByEstadoAndFechaFinBetween(Estadia.EstadoEstadia estado, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Buscar por vehículo y estado
    List<Estadia> findByVehiculoAndEstado(Vehiculo vehiculo, Estadia.EstadoEstadia estado);

    // Buscar por celda y estado
    List<Estadia> findByCeldaAndEstado(Celda celda, Estadia.EstadoEstadia estado);

    // Buscar estadias por tarifa
    List<Estadia> findByTarifa(Tarifa tarifa);

    // Buscar estadias activas por tarifa
    List<Estadia> findByTarifaAndEstado(Tarifa tarifa, Estadia.EstadoEstadia estado);
}

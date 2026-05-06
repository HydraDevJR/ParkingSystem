package com.ParkingSystem.repositories;

import com.ParkingSystem.models.Celda;
import com.ParkingSystem.models.utils.EstadoCelda;
import com.ParkingSystem.models.utils.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ICeldaRepositorio extends JpaRepository<Celda, UUID> {

    // Buscar por Código de celda
    List<Celda> findByCodigo(String codigo);

    // Buscar por estado
    List<Celda> findByEstado(EstadoCelda estado);

    // Buscar por tipo de vehículo
    List<Celda> findByTipoVehiculo(TipoVehiculo tipoVehiculo);

    // Buscar por código y estado
    List<Celda> findByCodigoAndEstado(String codigo, EstadoCelda estado);

    // Buscar por tipo y estado
    List<Celda> findByTipoVehiculoAndEstado(TipoVehiculo tipoVehiculo, EstadoCelda estado);
}
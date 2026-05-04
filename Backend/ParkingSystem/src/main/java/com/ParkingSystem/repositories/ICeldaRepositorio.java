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

    // Buscar por número de Celda
    Optional<Celda> findByNumero(Integer numero);

    // Buscar por piso
    List<Celda> findByPiso(Integer piso);

    // Buscar por estado
    List<Celda> findByEstado(EstadoCelda estado);

    // Buscar por tipo de vehículo
    List<Celda> findByTipoVehiculo(TipoVehiculo tipoVehiculo);

    // Buscar por piso y estado
    List<Celda> findByPisoAndEstado(Integer piso, EstadoCelda estado);

    // Buscar por tipo y estado
    List<Celda> findByTipoVehiculoAndEstado(TipoVehiculo tipoVehiculo, EstadoCelda estado);
}
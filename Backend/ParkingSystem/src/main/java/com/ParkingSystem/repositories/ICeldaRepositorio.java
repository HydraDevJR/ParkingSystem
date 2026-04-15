package com.ParkingSystem.repositories;

import org.springframework.stereotype.Repository;
import com.ParkingSystem.models.Celda;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ICeldaRepositorio  extends JpaRepository<Celda, Integer> {

    // Buscar por número de Celda
    Optional<Celda> findByNumero(Integer numero);

    // Buscar por piso
    List<Celda> findByPiso(Integer piso);

    // Buscar por estado
    List<Celda> findByEstado(Celda.EstadoCelda estado);

    // Buscar por tipo de vehículo
    List<Celda> findByTipo(TipoVehiculo tipo);

    // Buscar por parqueadero
    List<Celda> findByParqueadero(Parqueadero parqueadero);

    // Buscar por piso y estado 
    List<Celda> findByPisoAndEstado(Integer piso, Celda.EstadoCelda estado);

    // Buscar por tipo y estado
    List<Celda> findByTipoAndEstado(TipoVehiculo tipo, Celda.EstadoCelda estado);

}

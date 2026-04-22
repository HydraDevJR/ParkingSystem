package com.ParkingSystem.repositories;

import com.ParkingSystem.models.Tarifa;
import com.ParkingSystem.models.Tarifa.TipoTarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITarifaRepositorie extends JpaRepository<Tarifa, Long> {

    
    List<Tarifa> findByActivoTrue();


    Optional<Tarifa> findByTipo(TipoTarifa tipo);

    
    Optional<Tarifa> findByTipoAndActivoTrue(TipoTarifa tipo);

    
    List<Tarifa> findByValorLessThanEqual(double valor);


    Tarifa save(Tarifa tarifa);
}
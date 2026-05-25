package com.ParkingSystem.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ParkingSystem.models.Tarifa;
import com.ParkingSystem.models.utils.TipoTarifa;


@Repository
public interface ITarifaRepository extends JpaRepository<Tarifa, Integer> {

    // 1. DEFINO QUE ATRIBUTOS TIENE MI MODELO Y SOLO SOBRE ESOS ATRIBUTOS PUEDO IMPLEMENTAR LAS BUSQUEDAS

    // BUSCAR POR ESTADO ACTIVO
    List<Tarifa> findByActivoTrue();

    // BUSCAR POR TIPO DE TARIFA
    Optional<Tarifa> findByTipo(TipoTarifa tipo);

    // BUSCAR POR TIPO Y ESTADO ACTIVO
    Optional<Tarifa> findByTipoAndActivoTrue(TipoTarifa tipo);

    // BUSCAR POR VALOR MENOR O IGUAL
    List<Tarifa> findByValorLessThanEqual(BigDecimal valor);

}
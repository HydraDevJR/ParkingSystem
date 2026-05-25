package com.ParkingSystem.repositories;

import com.ParkingSystem.models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    
    Optional<Vehiculo> findByPlaca(String placa);

    List<Vehiculo> findByMarca(String marca);

    List<Vehiculo> findByPlacaContaining(String placa);

    List<Vehiculo> findByModelo(String modelo);
}
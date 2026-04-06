package com.ParkingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ParkingSystem.models.Vehiculo;

public interface IVehiculoRepositorie extends JpaRepository<Vehiculo, Integer> {
    
}

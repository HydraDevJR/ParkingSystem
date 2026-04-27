package com.ParkingSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ParkingSystem.models.Vehiculo;

@Repository
public interface IVehiculoRepositorie extends JpaRepository<Vehiculo, Integer> {
    
    //Buscar por placa exacta (1)
    public Vehiculo findByPlaca(String placa);

    //Buscar por marca exacta (lista)
    public List<Vehiculo> findByMarca(String marca);

    //Buscar por placa que contenga nnn (lista)
    public List<Vehiculo> findByPlacaContaining(String placa);

    //Buscar por modelo (lista)
    public List<Vehiculo> findByModelo(String modelo);
}

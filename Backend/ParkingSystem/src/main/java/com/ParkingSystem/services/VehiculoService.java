package com.ParkingSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ParkingSystem.repositories.IVehiculoRepositorie;

@Service
public class VehiculoService {
    
    @Autowired
    private IVehiculoRepositorie vehiculoRepositorie;
}

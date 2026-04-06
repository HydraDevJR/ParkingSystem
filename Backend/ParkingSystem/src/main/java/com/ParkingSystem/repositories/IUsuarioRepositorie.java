package com.ParkingSystem.repositories;

import org.springframework.stereotype.Repository;
import com.ParkingSystem.models.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IUsuarioRepositorie extends JpaRepository<Usuario, Integer> {


    //Buscar por Nombre exacto (lista)
    List<Usuario>findByNombre(String nombre);

    //Buscar por documento (1)
    Optional<Usuario>findByDocumento(String documento);

    //Buscar por nombres que contengan nnn (lista)
    List<Usuario>findByNombreContaining(String nombre);

    //Buscar por edad (lista)
    List<Usuario>findByEdad(Integer edad);

}

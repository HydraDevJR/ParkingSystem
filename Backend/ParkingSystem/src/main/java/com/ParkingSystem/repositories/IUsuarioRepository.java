package com.ParkingSystem.repositories;

import org.springframework.stereotype.Repository;
import com.ParkingSystem.models.Usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {


    //Buscar por Nombre exacto (lista)
    List<Usuario>findByNombre(String nombre);

    //Buscar por documento (1)
    Optional<Usuario>findByDocumento(String documento);

    //Buscar por nombres que contengan nnn (lista)
    List<Usuario>findByNombreContaining(String nombre);

    // Buscar por fecha de nacimiento (lista)
    List<Usuario> findByFechaNacimiento(LocalDate fechaNacimiento);

}

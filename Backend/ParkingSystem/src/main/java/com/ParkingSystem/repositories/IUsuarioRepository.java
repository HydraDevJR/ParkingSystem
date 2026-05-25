package com.ParkingSystem.repositories;

import com.ParkingSystem.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Buscar por Nombre
    List<Usuario> findByNombre(String nombre);

    // Buscar por Documento
    Optional<Usuario> findByDocumento(String documento);

    //Buscar por Nombre que contenga
    List<Usuario> findByNombreContaining(String nombre);

    //Buscar por Fecha de Nacimiento
    List<Usuario> findByFechaNacimiento(LocalDate fechaNacimiento);

    //Buscar por Email
    Optional<Usuario> findByEmail(String email);
}
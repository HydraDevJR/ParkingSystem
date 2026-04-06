package com.ParkingSystem.repositories;

import org.springframework.stereotype.Repository;
import com.ParkingSystem.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IUsuarioRepositorie extends JpaRepository<Usuario, Integer> {

}

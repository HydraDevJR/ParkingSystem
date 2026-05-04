package com.ParkingSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ParkingSystem.models.Usuario;
import com.ParkingSystem.services.UsuarioService;

@RestController
@RequestMapping("/parkingsystem/v1/usuarios")
public class UsuarioController {

    //Inyectar el servicio de usuario
    @Autowired
    private UsuarioService usuarioService;

    //Para cada servicio ofrecido se debe programar una funcion en el 
    // controlador, que se encargue de recibir la solicitud, procesarla 
    // y devolver una respuesta.

    //Funcion controladora del servicio de guardar un usuario
    @PostMapping
    public ResponseEntity<?> controllerGuardar(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.OK).body(
            usuarioService.guardarUsuario(usuario)
        );
    }

    //Funcion controladora del servicio de listar todos los usuarios
    @GetMapping
    public ResponseEntity<?> controllerListar(){
        return ResponseEntity.status(HttpStatus.OK).body(
            usuarioService.listarUsuarios()
        );
    }
}

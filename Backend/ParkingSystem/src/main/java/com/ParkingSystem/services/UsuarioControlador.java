package com.ParkingSystem.controladores;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.ParkingSystem.models.Usuario;
import com.ParkingSystem.services.UsuarioService;

@RestController
@RequestMapping("/ParkingSystem/v1/usuarios")
public class UsuarioControlador {
    
    //Inyectar el servicio correspondiente
    @Autowired
    private UsuarioService servicio;

    //para cada servicio ofrecido se debe programar una funcion
    //esa funcion recibira las peticiones del pedido y respondera

    //funcion controladora del servicio guardar usuario
    @PostMapping
    public ResponseEntity<?> controladorGuardar(@RequestBody Usuario datos){
        return ResponseEntity.status(HttpStatus.ok).body(
            servicio.guardarUsuario(datos)
        );
    }
    

    //funcion controladora del servicio de listar todos los usuarios
    @GetMapping
    public ResponseEntity<?>controladorListarTodo(){
        return ResponseEntity.status(HttpStatus.ok).body(
            servicio.listarUsuarios()
        );
    }


}
package com.ParkingSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ParkingSystem.models.Usuario;
import com.ParkingSystem.repositories.IUsuarioRepositorie;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepositorie usuarioRepositorie;

    //Guardar un usuario
    public Usuario guardarUrsuario(Usuario usuario){
        //Validar la operacion

        if(usuarioRepositorie.findById(usuario.getId()).isPresent()){

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El usuario ya existe"
            );

        }

        if(usuario.getDocumento() == null || usuario.getDocumento().isBlank() || usuario.getDocumento().isEmpty()){
            
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El documento del usuario es obligatorio"
            );

        }

        if(usuario.getDocumento().length() < 6 || usuario.getDocumento().length() > 20){
            
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El documento del usuario debe tener entre 6 y 20 caracteres"
            );

        }

        if(usuario.getNombre() == null || usuario.getNombre().isBlank() || usuario.getNombre().isEmpty()){
            
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El nombre del usuario es obligatorio"
            );

        }

        if(usuario.getApellido() == null || usuario.getApellido().isBlank() || usuario.getApellido().isEmpty()){
            
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El apellido del usuario es obligatorio"
            );

        }

        if(usuario.getEmail() == null || usuario.getEmail().isBlank() || usuario.getEmail().isEmpty()){
            
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El email del usuario es obligatorio"
            );

        }

        if(usuario.getPassword() == null || usuario.getPassword().isBlank() || usuario.getPassword().isEmpty()){
            
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El password del usuario es obligatorio"
            );

        }

        if(usuario.getTelefono() == null || usuario.getTelefono().isBlank() || usuario.getTelefono().isEmpty()){
            
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El telefono del usuario es obligatorio"
            );

        }

        if(usuario.getFechaNacimiento() == null){
            
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "La fecha de nacimiento del usuario es obligatoria"
            );

        }

        if(usuario.getGenero() == null || usuario.getGenero().isBlank() || usuario.getGenero().isEmpty()){
            
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El genero del usuario es obligatorio"
            );

        }

        if(usuario.getTipoDocumento() == null){
            
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "El tipo de documento del usuario es obligatorio"
            );

        }

        return usuarioRepositorie.save(usuario);

    }

    //Litar todos los usuarios
    public List<Usuario> listarUsuarios(){
        return usuarioRepositorie.findAll();
    }

    //Eliminar un usuario
    public void eliminarUsuario(Integer id){
        usuarioRepositorie.deleteById(id);
    }

    //Modificar un usuario

    //Buscar un usuario por id

    
}

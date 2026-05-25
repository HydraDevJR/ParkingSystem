package com.ParkingSystem.services;

import com.ParkingSystem.models.Usuario;
import com.ParkingSystem.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    // Guardar usuario (CREAR)
    public Usuario guardarUsuario(Usuario usuario) {
        // Validar que documento no exista
        if (usuarioRepository.findByDocumento(usuario.getDocumento()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe un usuario con el documento: " + usuario.getDocumento());
        }
        // Validar que email no exista
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe un usuario con el email: " + usuario.getEmail());
        }

        // Validaciones de campos obligatorios
        if (usuario.getDocumento() == null || usuario.getDocumento().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El documento es obligatorio");
        }
        if (usuario.getDocumento().length() < 6 || usuario.getDocumento().length() > 20) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El documento debe tener entre 6 y 20 caracteres");
        }
        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
        if (usuario.getApellido() == null || usuario.getApellido().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El apellido es obligatorio");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email es obligatorio");
        }
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contraseña es obligatoria");
        }
        if (usuario.getTelefono() == null || usuario.getTelefono().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El teléfono es obligatorio");
        }
        if (usuario.getFechaNacimiento() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de nacimiento es obligatoria");
        }
        if (usuario.getGenero() == null || usuario.getGenero().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El género es obligatorio");
        }
        if (usuario.getTipoDocumento() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El tipo de documento es obligatorio");
        }

        return usuarioRepository.save(usuario);
    }

    // Listar todos
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar por ID (lanzando excepción si no existe)
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuario no encontrado con id: " + id));
    }

    // Actualizar usuario completo (PUT)
    public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        Usuario existente = buscarPorId(id);

        // Validar documento duplicado (si cambió el documento)
        if (!existente.getDocumento().equals(usuarioActualizado.getDocumento()) &&
                usuarioRepository.findByDocumento(usuarioActualizado.getDocumento()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe otro usuario con el documento: " + usuarioActualizado.getDocumento());
        }
        // Validar email duplicado (si cambió el email)
        if (!existente.getEmail().equals(usuarioActualizado.getEmail()) &&
                usuarioRepository.findByEmail(usuarioActualizado.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ya existe otro usuario con el email: " + usuarioActualizado.getEmail());
        }

        // Actualizar campos
        existente.setDocumento(usuarioActualizado.getDocumento());
        existente.setNombre(usuarioActualizado.getNombre());
        existente.setApellido(usuarioActualizado.getApellido());
        existente.setEmail(usuarioActualizado.getEmail());
        existente.setPassword(usuarioActualizado.getPassword());
        existente.setTelefono(usuarioActualizado.getTelefono());
        existente.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
        existente.setGenero(usuarioActualizado.getGenero());
        existente.setTipoDocumento(usuarioActualizado.getTipoDocumento());
        existente.setTipoUsuario(usuarioActualizado.getTipoUsuario());
        // No se actualiza la lista de vehículos aquí (se maneja aparte)

        return usuarioRepository.save(existente);
    }

    // Eliminar usuario
    public void eliminarUsuario(Integer id) {
        Usuario usuario = buscarPorId(id); // lanza 404 si no existe
        usuarioRepository.delete(usuario);
    }

    // Métodos adicionales que usan los query methods del repositorio
    public Optional<Usuario> buscarPorDocumento(String documento) {
        return usuarioRepository.findByDocumento(documento);
    }

    public List<Usuario> buscarPorNombreConteniendo(String parteNombre) {
        return usuarioRepository.findByNombreContaining(parteNombre);
    }

    public List<Usuario> buscarPorFechaNacimiento(LocalDate fecha) {
        return usuarioRepository.findByFechaNacimiento(fecha);
    }
}
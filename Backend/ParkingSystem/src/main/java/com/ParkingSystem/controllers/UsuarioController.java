package com.ParkingSystem.controllers;

import com.ParkingSystem.models.Usuario;
import com.ParkingSystem.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/parkingsystem/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // CREAR usuario
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.guardarUsuario(usuario));
    }

    // LISTAR todos
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    // BUSCAR por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // BUSCAR por documento (exacto)
    @GetMapping("/documento/{documento}")
    public ResponseEntity<?> buscarPorDocumento(@PathVariable String documento) {
        return usuarioService.buscarPorDocumento(documento)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // BUSCAR por nombre que contenga (ej: /buscar?nombre=Jorge)
    @GetMapping("/buscar")
    public ResponseEntity<List<Usuario>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(usuarioService.buscarPorNombreConteniendo(nombre));
    }

    // BUSCAR por fecha de nacimiento (formato ISO: yyyy-MM-dd)
    @GetMapping("/fecha-nacimiento")
    public ResponseEntity<List<Usuario>> buscarPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(usuarioService.buscarPorFechaNacimiento(fecha));
    }
}
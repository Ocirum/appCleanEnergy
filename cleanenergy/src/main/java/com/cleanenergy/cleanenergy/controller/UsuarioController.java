package com.cleanenergy.cleanenergy.controller;

import com.cleanenergy.cleanenergy.model.Usuario;
import com.cleanenergy.cleanenergy.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /*@PostMapping
    public Usuario insertarUsuario(@RequestBody Usuario usuarios) {
        return usuarioService.insertarUsuario(usuarios);
    }*/

    @PostMapping
    public ResponseEntity<?> insertarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.insertarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public List<Usuario> consultarUsuario() {
        return usuarioService.consultarUsuario();
    }

    @GetMapping("/{nombre}")
    public List<Usuario> consultarPorNombre(@PathVariable String nombre) {
        return usuarioService.consultarPorNombre(nombre);
    }

    @GetMapping("/{id}")
    public Usuario consultarPorId(@PathVariable int id) {
        return usuarioService.consultarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{documento}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int documento, @RequestBody Map<String, Object> cambios) {
        Usuario actualizado = usuarioService.actualizarUsuario(documento, cambios);
        return ResponseEntity.ok(actualizado);
    }
}
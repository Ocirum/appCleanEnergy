package com.cleanenergy.cleanenergy.controller;

import com.cleanenergy.cleanenergy.model.Usuario;
import com.cleanenergy.cleanenergy.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario insertarUsuario(@RequestBody Usuario usuarios){
        return usuarioService.insertarUsuario(usuarios);
    }

    @GetMapping
    public List<Usuario> consultarUsuario(){
        return usuarioService.consultarUsuario();
    }

    @GetMapping("/{nombre}")
    public  List<Usuario> consultarPorNombre(@PathVariable String nombre){
        return usuarioService.consultarPorNombre(nombre);
    }

    @GetMapping("/{id}")
    public Usuario consultarPorId(@PathVariable int id){
        return usuarioService.consultarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable int id){
        usuarioService.eliminarUsuario(id);
    }
}
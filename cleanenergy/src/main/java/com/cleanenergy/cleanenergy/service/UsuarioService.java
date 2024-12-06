package com.cleanenergy.cleanenergy.service;


import com.cleanenergy.cleanenergy.model.Usuario;
import com.cleanenergy.cleanenergy.repository.UsuarioRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.springframework.util.ReflectionUtils.setField;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository UsuarioRepository) {
        this.usuarioRepository = UsuarioRepository;
    }

    public Usuario insertarUsuario(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al insertar usuario");
        }
    }

    public List<Usuario> consultarUsuario() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> consultarPorNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public Usuario consultarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void eliminarUsuario(Integer id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al eliminar usuario");
        }
    }
    public Usuario actualizarUsuario(int documento, Map<String, Object> cambios) {
        Usuario existente = usuarioRepository.findById(documento)
                .orElseThrow(() -> new RuntimeException("Linea de credito no encontrada"));

        cambios.forEach((key, value) -> {
            Field field;
            field = findField(Usuario.class, key);
            field.setAccessible(true);
            setField(field, existente, value);
        });
        return usuarioRepository.save(existente);
    }

    private Field findField(Class<Usuario> usuario, String key) {
        return null;
    }
}
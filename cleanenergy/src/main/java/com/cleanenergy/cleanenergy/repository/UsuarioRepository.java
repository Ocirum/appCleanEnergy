package com.cleanenergy.cleanenergy.repository;

import com.cleanenergy.cleanenergy.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByNombre(String nombre);
}

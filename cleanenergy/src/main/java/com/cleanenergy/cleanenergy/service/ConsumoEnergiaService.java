package com.cleanenergy.cleanenergy.service;

import com.cleanenergy.cleanenergy.model.ConsumoEnergia;
import com.cleanenergy.cleanenergy.model.TipoEnergia;
import com.cleanenergy.cleanenergy.model.Usuario;
import com.cleanenergy.cleanenergy.repository.ConsumoEnergiaRepository;
import com.cleanenergy.cleanenergy.repository.TipoEnergiaRepository;
import com.cleanenergy.cleanenergy.repository.UsuarioRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.springframework.util.ReflectionUtils.setField;

@Service
public class ConsumoEnergiaService {
    private final UsuarioRepository usuarioRepository;
    private final TipoEnergiaRepository tipoEnergiaRepository;
    private final ConsumoEnergiaRepository consumoEnergiaRepository;

    public ConsumoEnergiaService(UsuarioRepository usuarioRepository, TipoEnergiaRepository tipoEnergiaRepository, ConsumoEnergiaRepository consumoEnergiaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tipoEnergiaRepository = tipoEnergiaRepository;
        this.consumoEnergiaRepository = consumoEnergiaRepository;
    }

    public ConsumoEnergia insertarConsumoEnergia(ConsumoEnergia consumoEnergia) {
        Usuario usuario = usuarioRepository.findById(consumoEnergia.getUsuario().getDocumento())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        TipoEnergia tipoEnergia = tipoEnergiaRepository.findById(consumoEnergia.getTipoEnergia().getIdEnergia())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de energia no encontrado"));
        return consumoEnergiaRepository.save(consumoEnergia);
    }

    public List<ConsumoEnergia> consultarConsumoEnergia() {
        return consumoEnergiaRepository.findAll();
    }

    public void eliminarConsumoEnergia(int id_consumo) {
        try {
            consumoEnergiaRepository.deleteById(id_consumo);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al eliminar registro");
        }
    }

    public ConsumoEnergia actualizarConsumoEnergia(int id_consumo, Map<String, Object> cambios) {
        ConsumoEnergia existente = consumoEnergiaRepository.findById(id_consumo)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        cambios.forEach((key, value) -> {
            Field field;
            field = findField(ConsumoEnergia.class, key);
            field.setAccessible(true);
            setField(field, existente, value);
        });
        return consumoEnergiaRepository.save(existente);
    }

    private Field findField(Class<ConsumoEnergia> consumoEnergia, String key) {
        return null;
    }

    public List<ConsumoEnergia> filtrarPorUsuario(int documento) {
        try {
            return consumoEnergiaRepository.findByUsuarioDocumento(documento);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al consultar");
        }
    }

    public List<ConsumoEnergia> filtrarPorTipoEnergia(int idEnergia) {
        try {
            return consumoEnergiaRepository.findByTipoEnergiaIdEnergia(idEnergia);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al consultar");
        }
    }

}
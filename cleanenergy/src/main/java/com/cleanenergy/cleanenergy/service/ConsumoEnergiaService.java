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
import java.util.Optional;

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

    public ConsumoEnergia actualizarConsumoEnergia(int id_consumo, ConsumoEnergia consumoEnergiaParcial) {
        // Buscar al cliente en la base de datos
        Optional<ConsumoEnergia> consumoEnergiaExistente = consumoEnergiaRepository.findById(id_consumo);

        if (consumoEnergiaExistente.isPresent()) {
            ConsumoEnergia consumoEnergia = consumoEnergiaExistente.get();

            // Actualizar solo los campos proporcionados (no null)
            if (consumoEnergiaParcial.getUsuario() != null) {
                consumoEnergia.setUsuario(consumoEnergiaParcial.getUsuario());
            }

            if (consumoEnergiaParcial.getTipoEnergia() != null) {
                consumoEnergia.setTipoEnergia(consumoEnergiaParcial.getTipoEnergia());
            }

            if (consumoEnergiaParcial.getFecha() != null) {
                consumoEnergia.setFecha(consumoEnergiaParcial.getFecha());
            }

            if (consumoEnergiaParcial.getConsumo() != null) {
                consumoEnergia.setConsumo(consumoEnergiaParcial.getConsumo());
            }

            if (consumoEnergiaParcial.getUnidadMedida() != null) {
                consumoEnergia.setUnidadMedida(consumoEnergiaParcial.getUnidadMedida());
            }
            // Guardar los cambios
            return consumoEnergiaRepository.save(consumoEnergia);
        } else {
            throw new RuntimeException("Cliente no encontrado con ID: " + id_consumo);
        }
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

    public ConsumoEnergia consultarConsumoEnergiaPorId(Integer id) {
        return consumoEnergiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }
}
package com.cleanenergy.cleanenergy.service;

import com.cleanenergy.cleanenergy.model.ConsumoEnergia;
import com.cleanenergy.cleanenergy.model.TipoEnergia;
import com.cleanenergy.cleanenergy.model.Usuario;
import com.cleanenergy.cleanenergy.repository.ConsumoEnergiaRepository;
import com.cleanenergy.cleanenergy.repository.TipoEnergiaRepository;
import com.cleanenergy.cleanenergy.repository.UsuarioRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        TipoEnergia tipoEnergia = tipoEnergiaRepository.findById(consumoEnergia.getTipoEnergia().getId_energia())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de energia no encontrado"));
        return consumoEnergiaRepository.save(consumoEnergia);
    }

    public List<ConsumoEnergia> consultarConsumoEnergia() {
        return consumoEnergiaRepository.findAll();
    }

    public List<ConsumoEnergia> consultarConsumoEnergiaPorDocumento(int documento) {
        return consumoEnergiaRepository.findByDocumento(documento);
    }

    public void eliminarConsumoEnergia(int id_consumo) {
        try {
            consumoEnergiaRepository.deleteById(id_consumo);
        }catch (DataAccessException e){
            throw new RuntimeException("No se pudo eliminar el consumo de energia");
        }
    }
}
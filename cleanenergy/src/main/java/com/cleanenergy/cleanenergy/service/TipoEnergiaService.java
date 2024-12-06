package com.cleanenergy.cleanenergy.service;

import com.cleanenergy.cleanenergy.model.TipoEnergia;
import com.cleanenergy.cleanenergy.repository.TipoEnergiaRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.springframework.util.ReflectionUtils.setField;

@Service
public class TipoEnergiaService {
    private final TipoEnergiaRepository tipoEnergiaRepository;

    public TipoEnergiaService(TipoEnergiaRepository tipoEnergiaRepository) {
        this.tipoEnergiaRepository = tipoEnergiaRepository;
    }

    public TipoEnergia insertarTipoEnergia(TipoEnergia tipoEnergia){
        try{
            return tipoEnergiaRepository.save(tipoEnergia);
        }catch (DataAccessException e){
            throw new RuntimeException("Error el insertar tipo de energia");
        }
    }

    public List<TipoEnergia> consultarTipoEnergia(){
        return tipoEnergiaRepository.findAll();
    }

    public TipoEnergia consultarTipoEnergiaPorId(int id){
        return tipoEnergiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de energía no encontrado"));
    }

    public void eliminarTipoEnergia(int id){
        try{
            tipoEnergiaRepository.deleteById(id);
        }catch (DataAccessException e){
            throw new RuntimeException("Error al eliminar tipo de energía");
        }
    }

    public TipoEnergia actualizarTipoEnergia(int id_energia, Map<String, Object> cambios) {
        TipoEnergia existente = tipoEnergiaRepository.findById(id_energia)
                .orElseThrow(() -> new RuntimeException("Linea de credito no encontrada"));

        cambios.forEach((key, value) -> {
            Field field;
            field = findField(TipoEnergia.class, key);
            field.setAccessible(true);
            setField(field, existente, value);
        });
        return tipoEnergiaRepository.save(existente);
    }

    private Field findField(Class<TipoEnergia> tipoEnergia, String key) {
        return null;
    }
}
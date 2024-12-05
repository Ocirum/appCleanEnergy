package com.cleanenergy.cleanenergy.service;

import com.cleanenergy.cleanenergy.model.TipoEnergia;
import com.cleanenergy.cleanenergy.repository.TipoEnergiaRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
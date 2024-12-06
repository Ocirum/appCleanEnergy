package com.cleanenergy.cleanenergy.repository;

import com.cleanenergy.cleanenergy.model.ConsumoEnergia;
import com.cleanenergy.cleanenergy.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumoEnergiaRepository extends JpaRepository<ConsumoEnergia,Integer> {
    List<ConsumoEnergia> findByUsuarioDocumento(Integer documento);
    List<ConsumoEnergia> findByTipoEnergiaIdEnergia(Integer idEnergia);
}
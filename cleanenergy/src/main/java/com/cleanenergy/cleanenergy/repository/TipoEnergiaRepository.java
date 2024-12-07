package com.cleanenergy.cleanenergy.repository;

import com.cleanenergy.cleanenergy.model.TipoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoEnergiaRepository extends JpaRepository<TipoEnergia, Integer> {
    List<TipoEnergia> findByTipoEnergia(String tipoEnergia);
}

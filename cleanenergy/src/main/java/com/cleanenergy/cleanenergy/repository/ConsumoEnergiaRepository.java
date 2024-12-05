package com.cleanenergy.cleanenergy.repository;

import com.cleanenergy.cleanenergy.model.ConsumoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsumoEnergiaRepository extends JpaRepository<ConsumoEnergia, Integer> {
    List<ConsumoEnergia> findByDocumento(int documento);
}

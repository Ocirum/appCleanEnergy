package com.cleanenergy.cleanenergy.repository;

import com.cleanenergy.cleanenergy.model.ConsumoEnergia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumoEnergiaRepository extends JpaRepository<ConsumoEnergia,Integer> {
}
package com.cleanenergy.cleanenergy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TipoEnergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_energia;
    private String tipoEnergia;

    public TipoEnergia(int id_energia, String tipoEnergia) {
        this.id_energia = id_energia;
        this.tipoEnergia = tipoEnergia;
    }

    public TipoEnergia() {

    }

    public int getId_energia() {
        return id_energia;
    }

    public void setId_energia(int id_energia) {
        this.id_energia = id_energia;
    }

    public String getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(String tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }
}
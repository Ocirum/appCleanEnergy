package com.cleanenergy.cleanenergy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TipoEnergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEnergia;
    private String tipoEnergia;

    public TipoEnergia(int idEnergia, String tipoEnergia) {
        this.idEnergia = idEnergia;
        this.tipoEnergia = tipoEnergia;
    }

    public TipoEnergia() {

    }

    public int getIdEnergia() {
        return idEnergia;
    }

    public void setIdEnergia(int idEnergia) {
        this.idEnergia = idEnergia;
    }

    public String getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(String tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }
}
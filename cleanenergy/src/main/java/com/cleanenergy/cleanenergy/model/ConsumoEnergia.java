package com.cleanenergy.cleanenergy.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class ConsumoEnergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_consumo;

    @ManyToOne
    @JoinColumn(name = "documento")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "tipo_energia")
    private TipoEnergia tipoEnergia;

//hola mundo

    private LocalDate fecha;

    private double consumo;
    private String unidadMedida;

    public ConsumoEnergia(int id_consumo, Usuario usuario, TipoEnergia tipoEnergia, LocalDate fecha, double consumo, String unidadMedida) {
        this.id_consumo = id_consumo;
        this.usuario = usuario;
        this.tipoEnergia = tipoEnergia;
        this.fecha = fecha;
        this.consumo = consumo;
        this.unidadMedida = unidadMedida;
    }

    public ConsumoEnergia() {
    }

    public int getId_consumo() {
        return id_consumo;
    }

    public void setId_consumo(int id_consumo) {
        this.id_consumo = id_consumo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoEnergia getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(TipoEnergia tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
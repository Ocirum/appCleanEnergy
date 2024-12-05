package com.cleanenergy.cleanenergy.controller;

import com.cleanenergy.cleanenergy.model.TipoEnergia;
import com.cleanenergy.cleanenergy.service.TipoEnergiaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoEnergia")
public class TipoEnergiaController {

    private final TipoEnergiaService tipoEnergiaService;

    public TipoEnergiaController(TipoEnergiaService tipoEnergiaService) {
        this.tipoEnergiaService = tipoEnergiaService;
    }

    @PostMapping
    public TipoEnergia insertarTiposEnergia(@RequestBody TipoEnergia tiposEnergia){
        return tipoEnergiaService.insertarTipoEnergia(tiposEnergia);
    }

    @GetMapping
    public List<TipoEnergia> consultarTiposEnergia(){
        return tipoEnergiaService.consultarTipoEnergia();
    }

    @GetMapping("/{id}")
    public TipoEnergia consultarTipoEnergiaPorId(@PathVariable int id){
        return tipoEnergiaService.consultarTipoEnergiaPorId(id);
    }
}
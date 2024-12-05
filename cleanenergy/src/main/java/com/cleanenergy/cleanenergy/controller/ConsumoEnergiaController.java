package com.cleanenergy.cleanenergy.controller;

import com.cleanenergy.cleanenergy.model.ConsumoEnergia;
import com.cleanenergy.cleanenergy.service.ConsumoEnergiaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumoEnergia")
public class ConsumoEnergiaController {

    private final ConsumoEnergiaService consumoEnergiaService;

    public ConsumoEnergiaController(ConsumoEnergiaService consumoEnergiaService) {
        this.consumoEnergiaService = consumoEnergiaService;
    }

    @PostMapping
    public ConsumoEnergia insertarConsumoEnergia(@RequestBody ConsumoEnergia consumoEnergia){
        return consumoEnergiaService.insertarConsumoEnergia(consumoEnergia);
    }

    @GetMapping
    public List<ConsumoEnergia> consultarConsumoEnergia(){
        return consumoEnergiaService.consultarConsumoEnergia();
    }

    @GetMapping("/{documento}")
    public List<ConsumoEnergia> consultarConsumoEnergiaPorDocumento(@PathVariable int documento){
        return consumoEnergiaService.consultarConsumoEnergiaPorDocumento(documento);
    }

    @DeleteMapping("/{id_consumo}")
    public void eliminarConsumoEnergia(@PathVariable int id_consumo){
        consumoEnergiaService.eliminarConsumoEnergia(id_consumo);
    }
}
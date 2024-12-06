package com.cleanenergy.cleanenergy.controller;

import com.cleanenergy.cleanenergy.model.TipoEnergia;
import com.cleanenergy.cleanenergy.service.TipoEnergiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PatchMapping("/{id_energia}")
    public ResponseEntity<TipoEnergia> actualizarTipoEnergia(@PathVariable int id_energia, @RequestBody Map<String, Object> cambios){
        TipoEnergia actualizado = tipoEnergiaService.actualizarTipoEnergia(id_energia, cambios);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id_energia}")
    public ResponseEntity<Void> eliminarTipoEnergia(@PathVariable int id_energia){
        tipoEnergiaService.eliminarTipoEnergia(id_energia);
        return ResponseEntity.noContent().build();
    }
}
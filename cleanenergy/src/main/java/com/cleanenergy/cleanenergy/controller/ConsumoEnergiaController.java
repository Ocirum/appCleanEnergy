package com.cleanenergy.cleanenergy.controller;

import com.cleanenergy.cleanenergy.model.ConsumoEnergia;
import com.cleanenergy.cleanenergy.service.ConsumoEnergiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consumoEnergia")
public class ConsumoEnergiaController {

    private final ConsumoEnergiaService consumoEnergiaService;

    public ConsumoEnergiaController(ConsumoEnergiaService consumoEnergiaService) {
        this.consumoEnergiaService = consumoEnergiaService;
    }

    @PostMapping
    public ConsumoEnergia insertarConsumoEnergia(@RequestBody ConsumoEnergia consumoEnergia) {
        return consumoEnergiaService.insertarConsumoEnergia(consumoEnergia);
    }

    @GetMapping
    public List<ConsumoEnergia> consultarConsumoEnergia() {
        return consumoEnergiaService.consultarConsumoEnergia();
    }

    @GetMapping("/{id_consumo}")
    public ConsumoEnergia consultarConsumoEnergiaPorId(@PathVariable int id_consumo) {
        return consumoEnergiaService.consultarConsumoEnergiaPorId(id_consumo);
    }

    @DeleteMapping("/{id_consumo}")
    public ResponseEntity<Void> eliminarConsumoEnergia(@PathVariable int id_consumo) {
        consumoEnergiaService.eliminarConsumoEnergia(id_consumo);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id_consumo}")
    public ResponseEntity<ConsumoEnergia> actualizarConsumoEnergia(@PathVariable int id_consumo, @RequestBody ConsumoEnergia consumoEnergiaParcial) {
        try {
            ConsumoEnergia consumoEnergia = consumoEnergiaService.actualizarConsumoEnergia(id_consumo, consumoEnergiaParcial);
            return new ResponseEntity<>(consumoEnergia, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuario/{documento}")
    public List<ConsumoEnergia> consultarPorDocumento(@PathVariable int documento) {
        return consumoEnergiaService.filtrarPorUsuario(documento);
    }

    @GetMapping("/tipoEnergia/{idEnergia}")
    public List<ConsumoEnergia> consultarPorTipoEnergia(@PathVariable int idEnergia) {
        return consumoEnergiaService.filtrarPorTipoEnergia(idEnergia);
    }

}
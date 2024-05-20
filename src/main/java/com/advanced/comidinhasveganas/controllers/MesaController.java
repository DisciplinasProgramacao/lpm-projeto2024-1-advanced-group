package com.advanced.comidinhasveganas.controllers;

import com.advanced.comidinhasveganas.models.Mesa;
import com.advanced.comidinhasveganas.services.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public List<Mesa> getAllMesas() {
        return mesaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> getMesaById(@PathVariable int id) {
        Optional<Mesa> mesa = mesaService.findById(id);
        return mesa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mesa createMesa(@RequestBody Mesa mesa) {
        return mesaService.save(mesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> updateMesa(@PathVariable int id, @RequestBody Mesa mesaDetails) {
        Optional<Mesa> mesa = mesaService.findById(id);
        if (mesa.isPresent()) {
            Mesa updatedMesa = mesa.get();
            updatedMesa.setCapacidade(mesaDetails.getCapacidade());
            updatedMesa.setClientesSentados(mesaDetails.getClientesSentados());
            return ResponseEntity.ok(mesaService.save(updatedMesa));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMesa(@PathVariable int id) {
        mesaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

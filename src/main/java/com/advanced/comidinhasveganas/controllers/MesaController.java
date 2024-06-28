package com.advanced.comidinhasveganas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advanced.comidinhasveganas.entities.Mesa;
import com.advanced.comidinhasveganas.services.MesaService;

@RestController
@RequestMapping("/mesas")
public class MesaController {

  @Autowired
  private MesaService mesaService;

  @GetMapping
  public ResponseEntity<List<Mesa>> findAll() {
    return ResponseEntity.ok(mesaService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Mesa> findById(@PathVariable Long id) {
    return ResponseEntity.ok()
        .body(mesaService.findById(id).orElseThrow(() -> new RuntimeException("Mesa não encontrada")));
  }

  @PostMapping
  public ResponseEntity<Mesa> insert(@RequestBody Mesa mesa) {
    return ResponseEntity.status(HttpStatus.CREATED).body(mesaService.insert(mesa));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    mesaService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    mesaService.deleteAll();
    return ResponseEntity.noContent().build();
  }
}

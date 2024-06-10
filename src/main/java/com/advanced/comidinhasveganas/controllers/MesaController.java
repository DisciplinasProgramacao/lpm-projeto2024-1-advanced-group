package com.advanced.comidinhasveganas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advanced.comidinhasveganas.entities.Mesa;
import com.advanced.comidinhasveganas.services.MesaService;

@RestController
@RequestMapping("/mesas")
@Validated
public class MesaController {

  @Autowired
  private MesaService mesaService;

  @GetMapping
  public ResponseEntity<List<Mesa>> findAll() {
    List<Mesa> list = mesaService.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Mesa>> findById(@PathVariable Long id) {
    Optional<Mesa> obj = mesaService.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @PostMapping
  public ResponseEntity<Mesa> insert(@RequestBody @Validated Mesa obj) {
    obj = mesaService.insert(obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Mesa> update(@PathVariable Long id, @RequestBody @Validated Mesa obj) {
    obj = mesaService.update(id, obj);
    return ResponseEntity.ok().body(obj);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    mesaService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

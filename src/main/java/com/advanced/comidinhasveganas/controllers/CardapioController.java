package com.advanced.comidinhasveganas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advanced.comidinhasveganas.entities.Cardapio;
import com.advanced.comidinhasveganas.services.CardapioService;

@RestController
@RequestMapping("/cardapios")
public class CardapioController {

  @Autowired
  private CardapioService cardapioService;

  @GetMapping
  public ResponseEntity<List<Cardapio>> findAll() {
    return ResponseEntity.ok(cardapioService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cardapio> findById(@PathVariable Long id) {
    return ResponseEntity.ok()
        .body(cardapioService.findById(id).orElseThrow(() -> new RuntimeException("Cardápio não encontrado")));
  }

  @PostMapping
  public ResponseEntity<Cardapio> insert(@RequestBody Cardapio cardapio) {
    return ResponseEntity.status(HttpStatus.CREATED).body(cardapioService.insert(cardapio));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    cardapioService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cardapio> update(@PathVariable Long id, @RequestBody Cardapio cardapio) {
    return ResponseEntity.ok(cardapioService.update(id, cardapio));
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    cardapioService.deleteAll();
    return ResponseEntity.noContent().build();
  }

}

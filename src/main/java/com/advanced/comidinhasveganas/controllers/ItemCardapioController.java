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

import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.services.ItemCardapioService;

@RestController
@RequestMapping("/itens-cardapio")
public class ItemCardapioController {

  @Autowired
  private ItemCardapioService itemCardapioService;

  @GetMapping
  public ResponseEntity<List<ItemCardapio>> findAll() {
    return ResponseEntity.ok(itemCardapioService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemCardapio> findById(@PathVariable Long id) {
    return ResponseEntity.ok()
        .body(itemCardapioService.findById(id)
            .orElseThrow(() -> new RuntimeException("Item de cardápio não encontrado")));
  }

  @PostMapping
  public ResponseEntity<ItemCardapio> insert(@RequestBody ItemCardapio itemCardapio) {
    return ResponseEntity.status(HttpStatus.CREATED).body(itemCardapioService.insert(itemCardapio));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    itemCardapioService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ItemCardapio> update(@PathVariable Long id, @RequestBody ItemCardapio itemCardapio) {
    return ResponseEntity.ok(itemCardapioService.update(id, itemCardapio));
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    itemCardapioService.deleteAll();
    return ResponseEntity.noContent().build();
  }

}

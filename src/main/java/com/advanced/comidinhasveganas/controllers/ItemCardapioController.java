package com.advanced.comidinhasveganas.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/cardapio")
public class ItemCardapioController {

  @Autowired
  private ItemCardapioService itemCardapioService;

  @GetMapping
  public ResponseEntity<List<ItemCardapio>> findAll() {
    List<ItemCardapio> list = itemCardapioService.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<ItemCardapio>> findById(@PathVariable Long id) {
    Optional<ItemCardapio> obj = itemCardapioService.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @PostMapping
  public ResponseEntity<ItemCardapio> insert(@RequestBody ItemCardapio obj) {
    obj = itemCardapioService.insert(obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ItemCardapio> update(@PathVariable Long id, @RequestBody ItemCardapio obj) {
    obj = itemCardapioService.update(id, obj);
    return ResponseEntity.ok().body(obj);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    itemCardapioService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

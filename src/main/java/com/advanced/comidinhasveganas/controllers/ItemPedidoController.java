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

import com.advanced.comidinhasveganas.entities.ItemPedido;
import com.advanced.comidinhasveganas.services.ItemPedidoService;

@RestController
@RequestMapping("/itens-pedido")
public class ItemPedidoController {

  @Autowired
  private ItemPedidoService itemPedidoService;

  @GetMapping
  public ResponseEntity<List<ItemPedido>> findAll() {
    return ResponseEntity.ok(itemPedidoService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemPedido> findById(@PathVariable Long id) {
    return ResponseEntity.ok()
        .body(itemPedidoService.findById(id)
            .orElseThrow(() -> new RuntimeException("Item de pedido não encontrado")));
  }

  @PostMapping
  public ResponseEntity<ItemPedido> insert(@RequestBody ItemPedido itemPedido) {
    return ResponseEntity.status(HttpStatus.CREATED).body(itemPedidoService.insert(itemPedido));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    itemPedidoService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ItemPedido> update(@PathVariable Long id, @RequestBody ItemPedido itemPedido) {
    return ResponseEntity.ok(itemPedidoService.update(id, itemPedido));
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    itemPedidoService.deleteAll();
    return ResponseEntity.noContent().build();
  }

}

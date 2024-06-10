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

import com.advanced.comidinhasveganas.dto.ItemRequest;
import com.advanced.comidinhasveganas.entities.PedidoItemCardapio;
import com.advanced.comidinhasveganas.entities.pk.PedidoItemCardapioPK;
import com.advanced.comidinhasveganas.services.PedidoItemCardapioService;

@RestController
@RequestMapping("/pedido-itens")
public class PedidoItemCardapioController {

  @Autowired
  private PedidoItemCardapioService service;

  @GetMapping
  public List<PedidoItemCardapio> findAll() {
    return service.findAll();
  }

  @GetMapping("/{pedidoId}/{itemId}")
  public ResponseEntity<PedidoItemCardapio> findById(@PathVariable Long pedidoId, @PathVariable Long itemId) {
    PedidoItemCardapioPK id = new PedidoItemCardapioPK();
    Optional<PedidoItemCardapio> pedidoItem = service.findById(id);
    return pedidoItem.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public PedidoItemCardapio insert(@RequestBody PedidoItemCardapio pedidoItemCardapio) {
    return service.insert(pedidoItemCardapio);
  }

  @PostMapping("/{pedidoId}/multiple")
  public ResponseEntity<List<PedidoItemCardapio>> insertMultiple(@PathVariable Long pedidoId,
      @RequestBody List<ItemRequest> itemRequests) {
    List<PedidoItemCardapio> updatedItens = service.insertMultiple(pedidoId, itemRequests);
    return ResponseEntity.ok().body(updatedItens);
  }

  @DeleteMapping("/{pedidoId}/{itemId}")
  public ResponseEntity<Void> delete(@PathVariable Long pedidoId, @PathVariable Long itemId) {
    PedidoItemCardapioPK id = new PedidoItemCardapioPK();
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping
  public PedidoItemCardapio update(@RequestBody PedidoItemCardapio pedidoItemCardapio) {
    return service.update(pedidoItemCardapio);
  }
}
package com.advanced.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.advanced.entities.PedidoItemCardapio;
import com.advanced.entities.pk.PedidoItemCardapioPK;
import com.advanced.dto.ItemRequest;
import com.advanced.services.PedidoItemCardapioService;

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
package com.advanced.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizpsg.advanced.dto.ErrorResponse;
import com.luizpsg.advanced.entities.Pedido;
import com.luizpsg.advanced.services.PedidoService;
import com.luizpsg.advanced.services.RequisicaoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @Autowired
  private RequisicaoService requisicaoService;

  @GetMapping
  public ResponseEntity<List<Pedido>> findAll() {
    List<Pedido> list = pedidoService.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Pedido>> findById(@PathVariable Long id) {
    Optional<Pedido> obj = pedidoService.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @PostMapping("/{requisicaoId}")
  public ResponseEntity<?> insert(@PathVariable Long requisicaoId, @RequestBody Pedido obj) {
    if (!requisicaoService.isAtendidaEnaoFinalizada(requisicaoId)) {
      ErrorResponse errorResponse = new ErrorResponse("Requisição não atendida ou já finalizada");
      return ResponseEntity.badRequest().body(errorResponse);
    }
    obj = pedidoService.insert(obj, requisicaoId);
    return ResponseEntity.ok().body(obj);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    pedidoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

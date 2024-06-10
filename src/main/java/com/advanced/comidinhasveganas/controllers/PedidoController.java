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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.advanced.comidinhasveganas.dto.ErrorResponse;
import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.entities.Pedido;
import com.advanced.comidinhasveganas.entities.PedidoItemCardapio;
import com.advanced.comidinhasveganas.services.ItemCardapioService;
import com.advanced.comidinhasveganas.services.PedidoItemCardapioService;
import com.advanced.comidinhasveganas.services.PedidoService;
import com.advanced.comidinhasveganas.services.RequisicaoService;
//import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/pedidos")
@Validated
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @Autowired
  private RequisicaoService requisicaoService;

  @Autowired
  private ItemCardapioService itemCardapioService;

  @Autowired
  private PedidoItemCardapioService pedidoItemCardapioService;

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

  @PostMapping("/menu-fechado")
  public ResponseEntity<Pedido> criarPedidoMenuFechado(@RequestBody Pedido pedido,
      @RequestParam List<Long> pratosPrincipaisIds,
      @RequestParam List<Long> bebidasIds) {
    pedido = pedidoService.insert(pedido, pedido.getRequisicao().getId());

    // Adiciona pratos principais
    for (int i = 0; i < pratosPrincipaisIds.size(); i++) {
      Long pratoPrincipalId = pratosPrincipaisIds.get(i);
      ItemCardapio pratoPrincipal = itemCardapioService.findById(pratoPrincipalId)
          .orElseThrow(() -> new RuntimeException("Prato principal não encontrado"));
      Double preco = (i == 0) ? 32.0 : 0.0;
      PedidoItemCardapio pedidoItem = new PedidoItemCardapio(pedido, pratoPrincipal, 1, preco);
      pedidoItemCardapioService.insert(pedidoItem);
    }

    // Adiciona bebidas
    for (Long bebidaId : bebidasIds) {
      ItemCardapio bebida = itemCardapioService.findById(bebidaId)
          .orElseThrow(() -> new RuntimeException("Bebida não encontrada"));
      PedidoItemCardapio pedidoItemBebida = new PedidoItemCardapio(pedido, bebida, 1, 0.0);
      pedidoItemCardapioService.insert(pedidoItemBebida);
    }

    return ResponseEntity.ok().body(pedido);
  }

  // @PutMapping("/pedidos/addProduto{idPedido}/{idProduto}")
  // public String putMethodName(@PathVariable String id, @RequestBody String
  // entity) {
  // Pedido ped = pedidoService.findById(idPedido);
  // ItemCardapio prod = itemCardapioService.findById(idProduto);
  // ped.adicionarItem(prod);

  // pedidoService.update(idPedido, ped);

  // return entity;
  // }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    pedidoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

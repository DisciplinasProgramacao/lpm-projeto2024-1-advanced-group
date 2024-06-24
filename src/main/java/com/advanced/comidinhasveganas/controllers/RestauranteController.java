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

import com.advanced.comidinhasveganas.dto.ItemPedidoDTO;
import com.advanced.comidinhasveganas.entities.Cliente;
import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.entities.Mesa;
import com.advanced.comidinhasveganas.entities.Requisicao;
import com.advanced.comidinhasveganas.entities.Restaurante;
import com.advanced.comidinhasveganas.services.ClienteService;
import com.advanced.comidinhasveganas.services.MesaService;
import com.advanced.comidinhasveganas.services.RequisicaoService;
import com.advanced.comidinhasveganas.services.RestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

  @Autowired
  private RestauranteService restauranteService;

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private MesaService mesaService;

  @Autowired
  private RequisicaoService requisicaoService;

  @GetMapping
  public ResponseEntity<List<Restaurante>> findAll() {
    return ResponseEntity.ok(restauranteService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Restaurante> findById(@PathVariable Long id) {
    return ResponseEntity.ok()
        .body(restauranteService.findById(id).orElseThrow(() -> new RuntimeException("Restaurante não encontrado")));
  }

  @PostMapping
  public ResponseEntity<Restaurante> insert(@RequestBody Restaurante restaurante) {
    return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.insert(restaurante));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    restauranteService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    restauranteService.deleteAll();
    return ResponseEntity.noContent().build();
  }

  ///////////////////////////////////////////////////////////////////////////////////////

  @PostMapping("/{idRestaurante}/mesas")
  public ResponseEntity<Mesa> insertMesa(@PathVariable Long idRestaurante, @RequestBody Mesa mesa) {
    Restaurante restaurante = restauranteService.findById(idRestaurante)
        .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    restaurante.addMesa(mesa);
    return ResponseEntity.status(HttpStatus.CREATED).body(mesaService.insert(mesa));
  }

  @GetMapping("/{idRestaurante}/telcliente/{telefone}")
  public ResponseEntity<Cliente> findClienteByTelefone(@PathVariable Long idRestaurante,
      @PathVariable String telefone) {
    return ResponseEntity.ok().body(clienteService.findByTelefone(telefone)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
  }

  @PostMapping("/{idRestaurante}/clientes")
  public ResponseEntity<Cliente> insertCliente(@PathVariable Long idRestaurante,
      @RequestBody Cliente cliente) {
    Restaurante restaurante = restauranteService.findById(idRestaurante)
        .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    restaurante.addCliente(cliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.insert(cliente));
  }

  @PostMapping("/{idRestaurante}/telcliente/{telefone}/criarrequisicao/{quantidadePessoas}")
  public ResponseEntity<Requisicao> insertRequisicao(@PathVariable Long idRestaurante, @PathVariable String telefone,
      @PathVariable Integer quantidadePessoas) {

    Restaurante restaurante = restauranteService.findById(idRestaurante)
        .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    Cliente cliente = clienteService.findByTelefone(telefone)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    Requisicao requisicao = new Requisicao(cliente, quantidadePessoas);
    restaurante.addRequisicao(requisicao);
    return ResponseEntity.status(HttpStatus.CREATED).body(requisicaoService.insert(requisicao));
  }

  @PutMapping("/{idRestaurante}/atualizarRequisicoes")
  public ResponseEntity<String> atualizarRequisicoes(@PathVariable Long idRestaurante) {
    restauranteService.atualizarRequisicoes(idRestaurante);
    return ResponseEntity.ok().body("Sucesso!");
  }

  @GetMapping("/{idRestaurante}/mesasOcupadas")
  public ResponseEntity<List<Mesa>> findMesasOcupadas(@PathVariable Long idRestaurante) {
    return ResponseEntity.ok().body(restauranteService.findMesasOcupadas(idRestaurante));
  }

  @GetMapping("/{idRestaurante}/requisicoesAtivas")
  public ResponseEntity<List<Requisicao>> findRequisicoesAtivas(@PathVariable Long idRestaurante) {
    return ResponseEntity.ok().body(restauranteService.findRequisicoesAtivas(idRestaurante));
  }

  @GetMapping("/{idRestaurante}/itensCardapio")
  public ResponseEntity<List<ItemCardapio>> findItensCardapio(@PathVariable Long idRestaurante) {
    return ResponseEntity.ok().body(restauranteService.findItensCardapio(idRestaurante));
  }

  @PutMapping("/{idRestaurante}/{idRequisicao}/addPedido/{tipoPedido}")
  public ResponseEntity<Requisicao> addPedido(@PathVariable Long idRestaurante, @PathVariable Long idRequisicao,
      @PathVariable String tipoPedido, @RequestBody List<ItemPedidoDTO> itensDTO) {
    return ResponseEntity.ok().body(requisicaoService.addPedido(idRestaurante, idRequisicao, tipoPedido, itensDTO));
  }

  @PutMapping("/{idRestaurante}/{idRequisicao}/finalizarRequisicao")
  public ResponseEntity<Requisicao> finalizarRequisicao(@PathVariable Long idRestaurante,
      @PathVariable Long idRequisicao) {
    return ResponseEntity.ok().body(restauranteService.finalizarRequisicao(idRestaurante, idRequisicao));
  }
}
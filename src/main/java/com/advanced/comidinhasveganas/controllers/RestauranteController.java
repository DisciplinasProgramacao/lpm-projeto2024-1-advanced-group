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
import com.advanced.comidinhasveganas.entities.Cliente;
import com.advanced.comidinhasveganas.entities.Mesa;
import com.advanced.comidinhasveganas.entities.Requisicao;
import com.advanced.comidinhasveganas.entities.Restaurante;
import com.advanced.comidinhasveganas.services.CardapioService;
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

  @Autowired
  private CardapioService cardapioService;

  @GetMapping
  public ResponseEntity<List<Restaurante>> findAll() {
    return ResponseEntity.ok(restauranteService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Restaurante> findById(@PathVariable Long id) {
    return ResponseEntity.ok()
        .body(restauranteService.findById(id).orElseThrow(() -> new RuntimeException("Restaurante não encontrado")));
  }

  @GetMapping("/{idRestaurante}/clientes")
  public ResponseEntity<List<Cliente>> findClientesByRestauranteId(@PathVariable Long idRestaurante) {
    return ResponseEntity.ok(clienteService.findByRestauranteId(idRestaurante));
  }

  @GetMapping("/{idRestaurante}/clientes/{idCliente}")
  public ResponseEntity<Cliente> findClienteById(@PathVariable Long idRestaurante, @PathVariable Long idCliente) {
    return ResponseEntity.ok(clienteService.findById(idCliente)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
  }

  @GetMapping("/{idRestaurante}/mesas")
  public ResponseEntity<List<Mesa>> findMesasByRestauranteId(@PathVariable Long idRestaurante) {
    return ResponseEntity.ok(mesaService.findByRestauranteId(idRestaurante));
  }

  @GetMapping("/{idRestaurante}/mesas/{idMesa}")
  public ResponseEntity<Mesa> findMesaById(@PathVariable Long idRestaurante, @PathVariable Long idMesa) {
    return ResponseEntity.ok(mesaService.findById(idMesa)
        .orElseThrow(() -> new RuntimeException("Mesa não encontrada")));
  }

  @GetMapping("/{idRestaurante}/requisicoes")
  public ResponseEntity<List<Requisicao>> findRequisicoesByRestauranteId(@PathVariable Long idRestaurante) {
    return ResponseEntity.ok(requisicaoService.findByRestauranteId(idRestaurante));
  }

  @GetMapping("/{idRestaurante}/requicoes/{idRequisicao}")
  public ResponseEntity<Requisicao> findRequisicaoById(@PathVariable Long idRestaurante,
      @PathVariable Long idRequisicao) {
    return ResponseEntity.ok(requisicaoService.findById(idRequisicao)
        .orElseThrow(() -> new RuntimeException("Requisição não encontrada")));
  }

  @GetMapping("/{idRestaurante}/cardapios")
  public ResponseEntity<List<Cardapio>> findCardapiosByRestauranteId(@PathVariable Long idRestaurante) {
    return ResponseEntity.ok(cardapioService.findByRestauranteId(idRestaurante));
  }

  @PostMapping
  public ResponseEntity<Restaurante> insert(@RequestBody Restaurante restaurante) {
    return ResponseEntity.status(HttpStatus.CREATED).body(restauranteService.insert(restaurante));
  }

  @PostMapping("/{idRestaurante}/mesas")
  public ResponseEntity<Mesa> insertMesa(@PathVariable Long idRestaurante, @RequestBody Mesa mesa) {
    Restaurante restaurante = restauranteService.findById(idRestaurante)
        .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    mesa.setRestaurante(restaurante);
    return ResponseEntity.status(HttpStatus.CREATED).body(mesaService.insert(mesa));
  }

  @PostMapping("/{idRestaurante}/clientes")
  public ResponseEntity<Cliente> insertCliente(@PathVariable Long idRestaurante,
      @RequestBody Cliente cliente) {
    Restaurante restaurante = restauranteService.findById(idRestaurante)
        .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    cliente.setRestaurante(restaurante);
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.insert(cliente));
  }

  @PostMapping("/{idRestaurante}/clientes/{idCliente}/requisicoes")
  public ResponseEntity<Requisicao> insertRequisicao(@PathVariable Long idRestaurante, @PathVariable Long idCliente,
      @RequestBody Integer numPessoas) {

    Restaurante restaurante = restauranteService.findById(idRestaurante)
        .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    Cliente cliente = clienteService.findById(idCliente)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    Requisicao requisicao = new Requisicao(cliente, numPessoas, restaurante);
    restaurante.addRequisicao(requisicao);
    // requisicao.setRestaurante(restaurante);
    // requisicao.setCliente(cliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(requisicaoService.insert(requisicao));
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

  @PutMapping("/{id}")
  public ResponseEntity<Restaurante> update(@PathVariable Long id, @RequestBody Restaurante restaurante) {
    return ResponseEntity.ok(restauranteService.update(id, restaurante));
  }

  @PutMapping("/{idRestaurante}/atualizarRequisicoes")
  public ResponseEntity<Void> atualizarRequisicoes(@PathVariable Long idRestaurante) {
    restauranteService.atualizarRequisicoes(idRestaurante);
    return ResponseEntity.noContent().build();
  }

}

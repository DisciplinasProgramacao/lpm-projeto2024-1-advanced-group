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

import com.advanced.comidinhasveganas.entities.Cliente;
import com.advanced.comidinhasveganas.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping
  public ResponseEntity<List<Cliente>> findAll() {
    return ResponseEntity.ok().body(clienteService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> findById(@PathVariable Long id) {
    return ResponseEntity.ok()
        .body(clienteService.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
  }

  @PostMapping
  public ResponseEntity<Cliente> insert(@RequestBody Cliente cliente) {
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.insert(cliente));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    clienteService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
    return ResponseEntity.ok(clienteService.update(id, cliente));
  }

  @GetMapping("/telefone/{telefone}")
  public ResponseEntity<Cliente> findByTelefone(@PathVariable String telefone) {
    return ResponseEntity
        .ok(clienteService.findByTelefone(telefone).orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
  }
}

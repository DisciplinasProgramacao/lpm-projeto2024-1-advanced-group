package com.advanced.comidinhasveganas.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.advanced.comidinhasveganas.entities.Cliente;
import com.advanced.comidinhasveganas.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
@Validated
public class ClienteController {

  @Autowired
  private ClienteService service;

  @GetMapping
  public ResponseEntity<List<Cliente>> findAll() {
    List<Cliente> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping(value = "/{telefone}")
  public ResponseEntity<Optional<Cliente>> findByTelefone(@PathVariable String telefone) {
    Optional<Cliente> obj = service.findByTelefone(telefone);
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<Optional<Cliente>> findById(@PathVariable Long id) {
    Optional<Cliente> obj = service.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @PostMapping
  public ResponseEntity<Cliente> insert(@RequestBody @Validated Cliente obj) {
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).body(obj);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody @Validated Cliente obj) {
    obj = service.update(id, obj);
    return ResponseEntity.ok().body(obj);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}

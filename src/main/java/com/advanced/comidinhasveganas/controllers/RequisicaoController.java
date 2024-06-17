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

import com.advanced.comidinhasveganas.entities.Requisicao;
import com.advanced.comidinhasveganas.services.RequisicaoService;

@RestController
@RequestMapping("/requisicoes")
public class RequisicaoController {

  @Autowired
  private RequisicaoService requisicaoService;

  @GetMapping
  public ResponseEntity<List<Requisicao>> findAll() {
    return ResponseEntity.ok(requisicaoService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Requisicao> findById(@PathVariable Long id) {
    return ResponseEntity.ok()
        .body(requisicaoService.findById(id).orElseThrow(() -> new RuntimeException("Requisição não encontrada")));
  }

  @PostMapping
  public ResponseEntity<Requisicao> insert(@RequestBody Requisicao requisicao) {
    return ResponseEntity.status(HttpStatus.CREATED).body(requisicaoService.insert(requisicao));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Long id) {
    requisicaoService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Requisicao> update(@PathVariable Long id, @RequestBody Requisicao requisicao) {
    return ResponseEntity.ok(requisicaoService.update(id, requisicao));
  }

  @PutMapping("/alocarMesa/{id}/{numMesa}")
  public ResponseEntity<Requisicao> alocarMesa(@PathVariable Long id, @RequestBody Requisicao requisicao) {
    return ResponseEntity.ok(requisicaoService.alocarMesa(idReq, numMesa));
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteAll() {
    requisicaoService.deleteAll();
    return ResponseEntity.noContent().build();
  }

}

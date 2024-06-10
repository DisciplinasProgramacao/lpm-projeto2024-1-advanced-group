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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advanced.comidinhasveganas.entities.Requisicao;
import com.advanced.comidinhasveganas.services.RequisicaoService;

@RestController
@RequestMapping("/requisicoes")
@Validated
public class RequisicaoController {

  @Autowired
  private RequisicaoService requisicaoService;

  @GetMapping
  public ResponseEntity<List<Requisicao>> findAll() {
    List<Requisicao> list = requisicaoService.findAll();
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Requisicao>> findById(@PathVariable Long id) {
    Optional<Requisicao> obj = requisicaoService.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @PostMapping
  public ResponseEntity<Requisicao> insert(@RequestBody @Validated Requisicao obj) {
    obj = requisicaoService.insert(obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Requisicao> update(@PathVariable Long id, @RequestBody @Validated Requisicao obj) {
    obj = requisicaoService.update(id, obj);
    return ResponseEntity.ok().body(obj);
  }

  @PutMapping("/atualizarFilaDeRequisicoes")
  public ResponseEntity<Void> atualizarFilaDeRequisicoes() {
    requisicaoService.atualizarFilaDeRequisicoes();
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/encerrar/{idMesa}")
  public ResponseEntity<Requisicao> encerrarConta(@PathVariable Long idMesa) {
    Requisicao req = requisicaoService.encerrarConta(idMesa);
    return ResponseEntity.ok().body(req);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    requisicaoService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

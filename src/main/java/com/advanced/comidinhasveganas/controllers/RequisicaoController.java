package com.advanced.comidinhasveganas.controllers;

import com.advanced.comidinhasveganas.models.Requisicao;
import com.advanced.comidinhasveganas.services.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requisicoes")
public class RequisicaoController {

    @Autowired
    private RequisicaoService requisicaoService;

    @GetMapping
    public List<Requisicao> getAllRequisicoes() {
        return requisicaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requisicao> getRequisicaoById(@PathVariable int id) {
        Optional<Requisicao> requisicao = requisicaoService.findById(id);
        return requisicao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Requisicao createRequisicao(@RequestBody Requisicao requisicao) {
        return requisicaoService.save(requisicao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Requisicao> updateRequisicao(@PathVariable int id, @RequestBody Requisicao requisicaoDetails) {
        Optional<Requisicao> requisicao = requisicaoService.findById(id);
        if (requisicao.isPresent()) {
            Requisicao updatedRequisicao = requisicao.get();
            updatedRequisicao.setCliente(requisicaoDetails.getCliente());
            updatedRequisicao.setMesa(requisicaoDetails.getMesa());
            updatedRequisicao.setNumPessoas(requisicaoDetails.getNumPessoas());
            updatedRequisicao.setDataHoraEntrada(requisicaoDetails.getDataHoraEntrada());
            updatedRequisicao.setDataHoraSaida(requisicaoDetails.getDataHoraSaida());
            updatedRequisicao.setAtendida(requisicaoDetails.isAtendida());
            updatedRequisicao.setConta(requisicaoDetails.getConta());
            updatedRequisicao.setPedidos(requisicaoDetails.getPedidos());
            updatedRequisicao.setPago(requisicaoDetails.isPago());
            return ResponseEntity.ok(requisicaoService.save(updatedRequisicao));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequisicao(@PathVariable int id) {
        requisicaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


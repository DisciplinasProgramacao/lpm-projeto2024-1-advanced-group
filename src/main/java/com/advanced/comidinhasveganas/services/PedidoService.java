package com.advanced.comidinhasveganas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.Pedido;
import com.advanced.comidinhasveganas.entities.Requisicao;
import com.advanced.comidinhasveganas.repositories.PedidoRepository;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepository pedidoRepository;

  public List<Pedido> findAll() {
    return pedidoRepository.findAll();
  }

  public Optional<Pedido> findById(Long id) {
    return pedidoRepository.findById(id);
  }

  public List<Pedido> findByRequisicaoRestauranteId(Long restauranteId) {
    return pedidoRepository.findByRequisicaoRestauranteId(restauranteId);
  }

  @Transactional
  public Pedido insert(Pedido pedido) {
    return pedidoRepository.save(pedido);
  }

  @Transactional
  public void deleteAll() {
    pedidoRepository.deleteAll();
  }

  @Transactional
  public void deleteById(Long id) {
    pedidoRepository.deleteById(id);
  }

  @Transactional
  public void processarRequisicoes(List<Requisicao> requisicoes) {
    requisicoes.forEach(r -> {
      Pedido p = r.getPedido();
      if (p != null) {
        Optional.ofNullable(p.getId()).ifPresentOrElse(
            id -> update(id, p),
            () -> insert(p));
      }
    });
  }

  @Transactional
  public Pedido update(Long id, Pedido pedido) {
    Pedido entity = pedidoRepository.findById(id).get();
    updateData(entity, pedido);
    return pedidoRepository.save(entity);
  }

  private void updateData(Pedido entity, Pedido pedido) {
    if (pedido.getItens() != null) {
      entity.setItens(pedido.getItens());
    }
    if (pedido.getRequisicao() != null) {
      entity.setRequisicao(pedido.getRequisicao());
    }
  }

}

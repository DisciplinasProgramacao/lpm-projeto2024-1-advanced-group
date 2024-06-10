package com.advanced.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.dto.ItemRequest;
import com.advanced.entities.ItemCardapio;
import com.advanced.entities.Pedido;
import com.advanced.entities.PedidoItemCardapio;
import com.advanced.entities.pk.PedidoItemCardapioPK;
import com.advanced.repositories.ItemCardapioRepository;
import com.advanced.repositories.PedidoItemCardapioRepository;
import com.advanced.repositories.PedidoRepository;

@Service
public class PedidoItemCardapioService {

  @Autowired
  private PedidoItemCardapioRepository repository;

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private ItemCardapioRepository itemCardapioRepository;

  public List<PedidoItemCardapio> findAll() {
    return repository.findAll();
  }

  public Optional<PedidoItemCardapio> findById(PedidoItemCardapioPK id) {
    return repository.findById(id);
  }

  public PedidoItemCardapio insert(PedidoItemCardapio pedidoItemCardapio) {
    return repository.save(pedidoItemCardapio);
  }

  public void delete(PedidoItemCardapioPK id) {
    repository.deleteById(id);
  }

  public PedidoItemCardapio update(PedidoItemCardapio pedidoItemCardapio) {
    return repository.save(pedidoItemCardapio);
  }

  @Transactional
  public List<PedidoItemCardapio> insertMultiple(Long pedidoId, List<ItemRequest> itemRequests) {
    Pedido pedido = pedidoRepository.findById(pedidoId)
        .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

    List<PedidoItemCardapio> pedidoItens = itemRequests.stream()
        .map(itemRequest -> {
          ItemCardapio item = itemCardapioRepository.findById(itemRequest.getItemId())
              .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado"));
          return new PedidoItemCardapio(pedido, item, itemRequest.getQuantidade(), item.getPreco());
        }).toList();

    return repository.saveAll(pedidoItens);
  }
}
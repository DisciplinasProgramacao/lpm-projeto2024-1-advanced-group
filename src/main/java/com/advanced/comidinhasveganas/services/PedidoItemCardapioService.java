package com.advanced.comidinhasveganas.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.dto.ItemRequest;
import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.entities.Pedido;
import com.advanced.comidinhasveganas.entities.PedidoItemCardapio;
import com.advanced.comidinhasveganas.entities.pk.PedidoItemCardapioPK;
import com.advanced.comidinhasveganas.repositories.ItemCardapioRepository;
import com.advanced.comidinhasveganas.repositories.PedidoItemCardapioRepository;
import com.advanced.comidinhasveganas.repositories.PedidoRepository;

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
  public PedidoItemCardapio insert(Long pedidoId, Long itemId, Integer quantidade, Double preco) {
    Pedido pedido = pedidoRepository.findById(pedidoId)
        .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

    ItemCardapio item = itemCardapioRepository.findById(itemId)
        .orElseThrow(() -> new RuntimeException("Item do cardápio não encontrado"));

    PedidoItemCardapio pedidoItemCardapio = new PedidoItemCardapio(pedido, item, quantidade, preco);

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
        }).collect(Collectors.toList());

    return repository.saveAll(pedidoItens);
  }

}

package com.advanced.comidinhasveganas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.ItemPedido;
import com.advanced.comidinhasveganas.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

  @Autowired
  private ItemPedidoRepository itemPedidoRepository;

  public List<ItemPedido> findAll() {
    return itemPedidoRepository.findAll();
  }

  public Optional<ItemPedido> findById(Long id) {
    return itemPedidoRepository.findById(id);
  }

  @Transactional
  public ItemPedido insert(ItemPedido itemPedido) {
    return itemPedidoRepository.save(itemPedido);
  }

  @Transactional
  public List<ItemPedido> insertAll(List<ItemPedido> itensPedido) {
    return itemPedidoRepository.saveAll(itensPedido);
  }

  @Transactional
  public void deleteAll() {
    itemPedidoRepository.deleteAll();
  }

  @Transactional
  public void deleteById(Long id) {
    itemPedidoRepository.deleteById(id);
  }

}

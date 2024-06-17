package com.advanced.comidinhasveganas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.repositories.ItemCardapioRepository;

@Service
public class ItemCardapioService {

  @Autowired
  private ItemCardapioRepository itemCardapioRepository;

  public List<ItemCardapio> findAll() {
    return itemCardapioRepository.findAll();
  }

  public Optional<ItemCardapio> findById(Long id) {
    return itemCardapioRepository.findById(id);
  }

  public List<ItemCardapio> findByCardapioId(Long id) {
    return itemCardapioRepository.findByCardapioId(id);
  }

  public List<ItemCardapio> findByCardapioRestauranteId(Long id) {
    return itemCardapioRepository.findByCardapioRestauranteId(id);
  }

  @Transactional
  public ItemCardapio insert(ItemCardapio itemCardapio) {
    return itemCardapioRepository.save(itemCardapio);
  }

  @Transactional
  public void deleteAll() {
    itemCardapioRepository.deleteAll();
  }

  @Transactional
  public void deleteById(Long id) {
    itemCardapioRepository.deleteById(id);
  }

  @Transactional
  public ItemCardapio update(Long id, ItemCardapio itemCardapio) {
    ItemCardapio entity = itemCardapioRepository.findById(id).get();
    updateData(entity, itemCardapio);
    return itemCardapioRepository.save(entity);
  }

  private void updateData(ItemCardapio entity, ItemCardapio itemCardapio) {
    if (itemCardapio.getNome() != null) {
      entity.setNome(itemCardapio.getNome());
    }
    if (itemCardapio.getPreco() != null) {
      entity.setPreco(itemCardapio.getPreco());
    }
    if (itemCardapio.getCardapio() != null) {
      entity.setCardapio(itemCardapio.getCardapio());
    }
  }

}

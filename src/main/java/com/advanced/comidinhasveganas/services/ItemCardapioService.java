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

  @Transactional
  public ItemCardapio insert(ItemCardapio itemCardapio) {
    return itemCardapioRepository.save(itemCardapio);
  }

  @Transactional
  public List<ItemCardapio> insertAll(List<ItemCardapio> itemCardapios) {
    return itemCardapioRepository.saveAll(itemCardapios);
  }

  @Transactional
  public void deleteAll() {
    itemCardapioRepository.deleteAll();
  }

  @Transactional
  public void deleteById(Long id) {
    itemCardapioRepository.deleteById(id);
  }

}

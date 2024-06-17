package com.advanced.comidinhasveganas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.Cardapio;
import com.advanced.comidinhasveganas.repositories.CardapioRepository;

@Service
public class CardapioService {

  @Autowired
  private CardapioRepository cardapioRepository;

  public List<Cardapio> findAll() {
    return cardapioRepository.findAll();
  }

  public Optional<Cardapio> findById(Long id) {
    return cardapioRepository.findById(id);
  }

  public List<Cardapio> findByRestauranteId(Long id) {
    return cardapioRepository.findByRestauranteId(id);
  }

  @Transactional
  public Cardapio insert(Cardapio cardapio) {
    return cardapioRepository.save(cardapio);
  }

  @Transactional
  public void deleteAll() {
    cardapioRepository.deleteAll();
  }

  @Transactional
  public void deleteById(Long id) {
    cardapioRepository.deleteById(id);
  }

  @Transactional
  public Cardapio update(Long id, Cardapio cardapio) {
    Cardapio entity = cardapioRepository.findById(id).get();
    updateData(entity, cardapio);
    return cardapioRepository.save(entity);
  }

  private void updateData(Cardapio entity, Cardapio cardapio) {
    if (cardapio.getNome() != null) {
      entity.setNome(cardapio.getNome());
    }
    if (cardapio.getRestaurante() != null) {
      entity.setRestaurante(cardapio.getRestaurante());
    }
    if (cardapio.getItens() != null) {
      entity.setItens(cardapio.getItens());
    }
  }

}

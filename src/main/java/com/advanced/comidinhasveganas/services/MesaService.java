package com.advanced.comidinhasveganas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.Mesa;
import com.advanced.comidinhasveganas.repositories.MesaRepository;

@Service
public class MesaService {

  @Autowired
  private MesaRepository repository;

  public List<Mesa> findAll() {
    return repository.findAll();
  }

  public Optional<Mesa> findById(Long id) {
    return repository.findById(id);
  }

  @Transactional
  public Mesa insert(Mesa mesa) {
    return repository.save(mesa);
  }

  @Transactional
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Transactional
  public Mesa update(Long id, Mesa mesa) {
    Mesa entity = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
    updateData(entity, mesa);
    return repository.save(entity);
  }

  private void updateData(Mesa entity, Mesa mesa) {
    entity.setLugares(mesa.getLugares());
    entity.setIsOcupada(mesa.getIsOcupada());
  }

  public Optional<Mesa> findMesaDisponivel(int quantidadePessoas) {
    return repository.findAll().stream()
        .filter(m -> !m.getIsOcupada() && m.getLugares() >= quantidadePessoas)
        .findFirst();
  }

  @Transactional
  public void deleteAll() {
    repository.deleteAll();
  }
}

package com.advanced.comidinhasveganas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.Mesa;
import com.advanced.comidinhasveganas.exceptions.ResourceNotFoundException;
import com.advanced.comidinhasveganas.repositories.MesaRepository;

@Service
public class MesaService {

  @Autowired
  private MesaRepository mesaRepository;

  public List<Mesa> findAll() {
    return mesaRepository.findAll();
  }

  public Optional<Mesa> findById(Long id) {
    return mesaRepository.findById(id);
  }

  public List<Mesa> findByRestauranteId(Long id) {
    return mesaRepository.findByRestauranteId(id);
  }

  @Transactional
  public Mesa insert(Mesa mesa) {
    return mesaRepository.save(mesa);
  }

  @Transactional
  public void deleteAll() {
    mesaRepository.deleteAll();
  }

  @Transactional
  public void deleteById(Long id) {
    mesaRepository.deleteById(id);
  }

  @Transactional
  public Mesa update(Long id, Mesa mesa) {
    Mesa entity = mesaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Mesa não encontrada"));
    updateData(entity, mesa);
    return mesaRepository.save(entity);
  }

  private void updateData(Mesa entity, Mesa mesa) {
    if (mesa.getLugares() != null) {
      entity.setLugares(mesa.getLugares());
    }
    if (mesa.getIsOcupada() != null) {
      entity.setIsOcupada(mesa.getIsOcupada());
    }
    if (mesa.getRestaurante() != null) {
      entity.setRestaurante(mesa.getRestaurante());
    }
  }

}

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
  private MesaRepository mesaRepository;

  public List<Mesa> findAll() {
    return mesaRepository.findAll();
  }

  public Optional<Mesa> findById(Long id) {
    return mesaRepository.findById(id);
  }

  @Transactional
  public Mesa insert(Mesa mesa) {
    return mesaRepository.save(mesa);
  }

  @Transactional
  public List<Mesa> insertAll(List<Mesa> mesas) {
    return mesaRepository.saveAll(mesas);
  }

  @Transactional
  public void deleteAll() {
    mesaRepository.deleteAll();
  }

  @Transactional
  public void deleteById(Long id) {
    mesaRepository.deleteById(id);
  }

}

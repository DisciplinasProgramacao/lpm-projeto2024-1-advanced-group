package com.advanced.comidinhasveganas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.entities.Mesa;
import com.advanced.comidinhasveganas.entities.Requisicao;
import com.advanced.comidinhasveganas.entities.Restaurante;
import com.advanced.comidinhasveganas.exceptions.ResourceNotFoundException;
import com.advanced.comidinhasveganas.repositories.RestauranteRepository;

@Service
public class RestauranteService {

  @Autowired
  private RestauranteRepository restauranteRepository;

  public List<Restaurante> findAll() {
    return restauranteRepository.findAll();
  }

  public Optional<Restaurante> findById(Long id) {
    return restauranteRepository.findById(id);
  }

  @Transactional
  public Restaurante insert(Restaurante restaurante) {
    return restauranteRepository.save(restaurante);
  }

  @Transactional
  public void deleteAll() {
    restauranteRepository.deleteAll();
  }

  @Transactional
  public void deleteById(Long id) {
    restauranteRepository.deleteById(id);
  }

  @Transactional
  public void atualizarRequisicoes(Long restauranteId) {
    Restaurante restaurante = restauranteRepository.findById(restauranteId)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));

    restaurante.atualizarRequisicoes();

    restauranteRepository.save(restaurante);
  }

  public List<Mesa> findMesasOcupadas(Long restauranteId) {
    Restaurante restaurante = restauranteRepository.findById(restauranteId)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));

    return restaurante.getMesasOcupadas();
  }

  public List<Requisicao> findRequisicoesAtivas(Long restauranteId) {
    Restaurante restaurante = restauranteRepository.findById(restauranteId)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));

    return restaurante.getRequisicoesAtivas();
  }

  public List<ItemCardapio> findItensCardapio(Long restauranteId) {
    Restaurante restaurante = restauranteRepository.findById(restauranteId)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));

    return restaurante.getItensCardapio();
  }

  @Transactional
  public Requisicao finalizarRequisicao(Long restauranteId, Long requisicaoId) {
    Restaurante restaurante = restauranteRepository.findById(restauranteId)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));

    Requisicao requisicao = restaurante.getRequisicaoPorId(requisicaoId);

    if (requisicao == null) {
      throw new ResourceNotFoundException("Requisição não encontrada");
    }

    restaurante.finalizarRequisicao(requisicao);

    restauranteRepository.save(restaurante);
    return requisicao;
  }

}
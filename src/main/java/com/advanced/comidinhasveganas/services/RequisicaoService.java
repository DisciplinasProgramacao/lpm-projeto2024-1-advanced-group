package com.advanced.comidinhasveganas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.Requisicao;
import com.advanced.comidinhasveganas.exceptions.ResourceNotFoundException;
import com.advanced.comidinhasveganas.repositories.RequisicaoRepository;

@Service
public class RequisicaoService {

  @Autowired
  private RequisicaoRepository requisicaoRepository;

  public List<Requisicao> findAll() {
    return requisicaoRepository.findAll();
  }

  public Optional<Requisicao> findById(Long id) {
    return requisicaoRepository.findById(id);
  }

  public List<Requisicao> findByRestauranteId(Long id) {
    return requisicaoRepository.findByRestauranteId(id);
  }

  @Transactional
  public Requisicao insert(Requisicao requisicao) {
    return requisicaoRepository.save(requisicao);
  }

  @Transactional
  public void deleteAll() {
    requisicaoRepository.deleteAll();
  }

  @Transactional
  public void deleteById(Long id) {
    requisicaoRepository.deleteById(id);
  }

  @Transactional
  public Requisicao alocarMesa(Long id, Requisicao requisicao) {
    Requisicao entity = requisicaoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Requisição não encontrada"));
    Mesa mesa = mesaRepository.findById(numMesa)
        .orElseThrow(() -> new ResourceNotFoundException("Mesa não encontrada"));

    entity.iniciarRequisicao(mesa);
    // updateData(entity, requisicao);
    return requisicaoRepository.save(entity);
  }

  private void updateData(Requisicao entity, Requisicao requisicao) {
    if (requisicao.getCliente() != null) {
      entity.setCliente(requisicao.getCliente());
    }
    if (requisicao.getQuantidadePessoas() != null) {
      entity.setQuantidadePessoas(requisicao.getQuantidadePessoas());
    }
    if (requisicao.getMesa() != null) {
      entity.setMesa(requisicao.getMesa());
    }
    if (requisicao.getIsAtendida() != null) {
      entity.setIsAtendida(requisicao.getIsAtendida());
    }
    if (requisicao.getIsFinalizada() != null) {
      entity.setIsFinalizada(requisicao.getIsFinalizada());
    }
    if (requisicao.getDataHoraInicio() != null) {
      entity.setDataHoraInicio(requisicao.getDataHoraInicio());
    }
    if (requisicao.getDataHoraFim() != null) {
      entity.setDataHoraFim(requisicao.getDataHoraFim());
    }
    if (requisicao.getTotalConta() != null) {
      entity.setTotalConta(requisicao.getTotalConta());
    }
    if (requisicao.getTotalPorPessoa() != null) {
      entity.setTotalPorPessoa(requisicao.getTotalPorPessoa());
    }
    if (requisicao.getRestaurante() != null) {
      entity.setRestaurante(requisicao.getRestaurante());
    }
    if (requisicao.getPedido() != null) {
      entity.setPedido(requisicao.getPedido());
    }
  }
}

package com.advanced.comidinhasveganas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.dto.ItemPedidoDTO;
import com.advanced.comidinhasveganas.entities.ItemPedido;
import com.advanced.comidinhasveganas.entities.Pedido;
import com.advanced.comidinhasveganas.entities.Requisicao;
import com.advanced.comidinhasveganas.entities.Restaurante;
import com.advanced.comidinhasveganas.exceptions.ResourceNotFoundException;
import com.advanced.comidinhasveganas.repositories.ItemPedidoRepository;
import com.advanced.comidinhasveganas.repositories.PedidoRepository;
import com.advanced.comidinhasveganas.repositories.RequisicaoRepository;
import com.advanced.comidinhasveganas.repositories.RestauranteRepository;

@Service
public class RequisicaoService {

  @Autowired
  private RequisicaoRepository requisicaoRepository;

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private ItemPedidoRepository itemPedidoRepository;

  public List<Requisicao> findAll() {
    return requisicaoRepository.findAll();
  }

  public Requisicao findById(Long id) {
    return requisicaoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Requisição não encontrada"));
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

  private Restaurante findRestauranteById(Long id) {
    return restauranteRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));
  }

  @Transactional
  public Requisicao addPedido(Long restauranteId, Long requisicaoId, String tipoPedido,
      List<ItemPedidoDTO> itensDTO) {

    Restaurante restaurante = findRestauranteById(restauranteId);
    Requisicao requisicao = findById(requisicaoId);

    List<ItemPedido> itensPedido = restaurante.criarItensPedido(itensDTO);
    itemPedidoRepository.saveAll(itensPedido);

    Pedido pedido = new Pedido(tipoPedido);
    pedido.addItens(itensPedido);
    pedido.setPrecoTotal();
    pedidoRepository.save(pedido);

    requisicao.addPedido(pedido);
    return requisicaoRepository.save(requisicao);
  }

}

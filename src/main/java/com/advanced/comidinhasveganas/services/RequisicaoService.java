package com.advanced.comidinhasveganas.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.Cliente;
import com.advanced.comidinhasveganas.entities.Mesa;
import com.advanced.comidinhasveganas.entities.Requisicao;
import com.advanced.comidinhasveganas.repositories.RequisicaoRepository;

@Service
public class RequisicaoService {

  private static final Double SERVICE_TAX = 1.1;

  @Autowired
  private RequisicaoRepository repository;

  @Autowired
  private MesaService mesaService;

  public List<Requisicao> findAll() {
    return repository.findAll();
  }

  public Optional<Requisicao> findById(Long id) {
    return repository.findById(id);
  }

  @Transactional
  public Requisicao insert(Requisicao requisicao) {
    return repository.save(requisicao);
  }

  @Transactional
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Transactional
  public Requisicao update(Long id, Requisicao requisicao) {
    Requisicao entity = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Requisição não encontrada"));
    updateData(entity, requisicao);
    return repository.save(entity);
  }

  private void updateData(Requisicao entity, Requisicao requisicao) {
    entity.setAtendida(requisicao.isAtendida());
    entity.setFinalizada(requisicao.isFinalizada());
    entity.setMesa(requisicao.getMesa());
    entity.setQuantidadePessoas(requisicao.getQuantidadePessoas());
    entity.setCliente(requisicao.getCliente());
    entity.setDataHoraInicio(requisicao.getDataHoraInicio());
    entity.setDataHoraFim(requisicao.getDataHoraFim());
    entity.setPedidos(requisicao.getPedidos());
    entity.setTotalConta(requisicao.getTotalConta());
    entity.setTotalPorPessoa(requisicao.getTotalPorPessoa());
  }

  public Optional<Requisicao> findRequisicaoByMesaId(Long mesaId) {
    return repository.findAll().stream()
        .filter(r -> r.getMesa().getId().equals(mesaId) && !r.isFinalizada())
        .findFirst();
  }

  public Requisicao criarRequisicao(Cliente cliente, int quantidadePessoas) {
    Requisicao requisicao = new Requisicao();
    requisicao.setCliente(cliente);
    requisicao.setQuantidadePessoas(quantidadePessoas);
    requisicao.setDataHoraInicio(LocalDateTime.now());
    return insert(requisicao);
  }

  public void atualizarFilaDeRequisicoes() {
    findAll().stream()
        .filter(req -> !req.isAtendida())
        .forEach(req -> {
          Mesa mesa = mesaService.findMesaDisponivel(req.getQuantidadePessoas())
              .orElse(null);
          if (mesa != null) {
            atribuirMesaARequisicao(req, mesa);
          }
        });
  }

  private void atribuirMesaARequisicao(Requisicao req, Mesa mesa) {
    mesa.setOcupada(true);
    req.setMesa(mesa);
    req.setAtendida(true);
    req.setDataHoraInicio(LocalDateTime.now());
    update(req.getId(), req);
    mesaService.update(mesa.getId(), mesa);
  }

  @Transactional
  public Requisicao encerrarConta(Long idMesa) {
    Requisicao req = findRequisicaoByMesaId(idMesa)
        .orElseThrow(() -> new RuntimeException("Requisição não encontrada para a mesa selecionada"));

    double totalConta = req.getPedidos().stream()
        .flatMap(pedido -> pedido.getItens().stream())
        .mapToDouble(item -> item.getQuantidade() * item.getPreco() * SERVICE_TAX)
        .sum();
    double totalPorPessoa = totalConta / req.getQuantidadePessoas();

    req.setTotalConta(totalConta);
    req.setTotalPorPessoa(totalPorPessoa);
    req.setFinalizada(true);
    req.setDataHoraFim(LocalDateTime.now());
    update(req.getId(), req);

    Mesa mesa = req.getMesa();
    mesa.setOcupada(false);
    mesaService.update(mesa.getId(), mesa);
    return req;
  }

  public boolean isAtendidaEnaoFinalizada(Long requisicaoId) {
    Optional<Requisicao> requisicao = findById(requisicaoId);
    return requisicao.isPresent() && requisicao.get().isAtendida() && !requisicao.get().isFinalizada();
  }
}

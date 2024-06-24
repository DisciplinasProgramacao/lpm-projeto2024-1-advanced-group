package com.advanced.comidinhasveganas.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.advanced.comidinhasveganas.dto.ItemPedidoDTO;
import com.advanced.comidinhasveganas.exceptions.ResourceNotFoundException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_restaurantes")
public class Restaurante {

  private static final Double TAXA_SERVICO = 1.1;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String endereco;

  @OneToMany
  @JoinColumn(name = "restaurante_id")
  private List<Mesa> mesas = new ArrayList<>();

  @OneToMany
  @JoinColumn(name = "restaurante_id")
  private List<Cliente> clientes = new ArrayList<>();

  @OneToMany
  @JoinColumn(name = "restaurante_id")
  private List<Requisicao> requisicoes = new ArrayList<>();

  @OneToMany
  @JoinColumn(name = "restaurante_id")
  private List<ItemCardapio> itensCardapio = new ArrayList<>();

  public Restaurante() {
  }

  public Restaurante(String nome, String endereco) {
    setNome(nome);
    setEndereco(endereco);
  }

  public Long getId() {
    return id;
  }

  public Double getTaxaServico() {
    return TAXA_SERVICO;
  }

  public String getNome() {
    return nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public List<Mesa> getMesas() {
    return mesas;
  }

  public List<Cliente> getClientes() {
    return clientes;
  }

  public List<Requisicao> getRequisicoes() {
    return requisicoes;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public List<Cliente> setClientes(List<Cliente> clientes) {
    return this.clientes = clientes;
  }

  public List<Mesa> setMesas(List<Mesa> mesas) {
    return this.mesas = mesas;
  }

  public List<Requisicao> setRequisicoes(List<Requisicao> requisicoes) {
    return this.requisicoes = requisicoes;
  }

  public List<ItemCardapio> getItensCardapio() {
    return itensCardapio;
  }

  public void addItemCardapio(ItemCardapio itemCardapio) {
    itensCardapio.add(itemCardapio);
  }

  public void removeItemCardapio(ItemCardapio itemCardapio) {
    itensCardapio.remove(itemCardapio);
  }

  public void addMesa(Mesa mesa) {
    mesas.add(mesa);
  }

  public void removeMesa(Mesa mesa) {
    mesas.remove(mesa);
  }

  public Optional<Cliente> getClienteByTelefone(String telefone) {
    return clientes.stream().filter(c -> c.getTelefone().equals(telefone)).findFirst();
  }

  public void addCliente(Cliente cliente) {
    clientes.add(cliente);
  }

  public void removeCliente(Cliente cliente) {
    clientes.remove(cliente);
  }

  public void addRequisicao(Requisicao requisicao) {
    requisicoes.add(requisicao);
  }

  public void removeRequisicao(Requisicao requisicao) {
    requisicoes.remove(requisicao);
  }

  // Métodos para filtrar listas com base em predicados
  private List<Requisicao> filtrarRequisicoes(Predicate<Requisicao> predicate) {
    return requisicoes.stream().filter(predicate).collect(Collectors.toList());
  }

  // Métodos específicos para filtrar requisicoes e mesas
  public List<Requisicao> getRequisicoesNaoAtendidas() {
    return filtrarRequisicoes(r -> !r.getIsAtendida());
  }

  public List<Requisicao> getRequisicoesAtendidas() {
    return filtrarRequisicoes(Requisicao::getIsAtendida);
  }

  public List<Requisicao> getRequisicoesNaoFinalizadas() {
    return filtrarRequisicoes(r -> !r.getIsFinalizada());
  }

  public List<Requisicao> getRequisicoesFinalizadas() {
    return filtrarRequisicoes(Requisicao::getIsFinalizada);
  }

  public List<Requisicao> getRequisicoesAtivas() {
    return filtrarRequisicoes(r -> !r.getIsFinalizada() && r.getIsAtendida());
  }

  public Requisicao getRequisicaoPorId(Long id) {
    return requisicoes.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
  }

  public Requisicao getRequisicaoPorMesaId(Long mesaId) {
    return requisicoes.stream().filter(r -> r.getMesa().getId().equals(mesaId) && !r.getIsFinalizada()).findFirst()
        .orElse(null);
  }

  private List<Mesa> filtrarMesas(Predicate<Mesa> predicate) {
    return mesas.stream().filter(predicate).collect(Collectors.toList());
  }

  public List<Mesa> getMesasDisponiveis() {
    return filtrarMesas(m -> !m.getIsOcupada());
  }

  public List<Mesa> getMesasOcupadas() {
    return filtrarMesas(Mesa::getIsOcupada);
  }

  // Método para atualizar requisições
  public void atualizarRequisicoes() {
    getRequisicoesNaoAtendidas().forEach(req -> {
      getMesasDisponiveis().stream()
          .filter(m -> m.cabe(req.getQuantidadePessoas()))
          .findFirst()
          .ifPresent(mesa -> req.iniciarRequisicao(mesa));
    });
  }

  public List<ItemPedido> criarItensPedido(List<ItemPedidoDTO> itensDTO) {
    return itensDTO.stream()
        .map(itemDTO -> {
          Long itemId = itemDTO.getItemId();
          Integer quantidade = itemDTO.getQuantidade();

          return getItensCardapio().stream()
              .filter(item -> item.getId().equals(itemId))
              .findFirst()
              .map(item -> new ItemPedido(item, quantidade))
              .orElseThrow(() -> new ResourceNotFoundException("Item de cardápio não encontrado"));
        })
        .collect(Collectors.toList());
  }

  public void finalizarRequisicao(Requisicao requisicao) {
    requisicao.finalizarRequisicao();
  }

  @Override
  public String toString() {
    return "Restaurante [id=" + id + ", nome=" + nome + ", endereco=" + endereco + "]";
  }

}

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

/**
 * Entidade que representa um restaurante.
 */
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

  /**
   * Adiciona um item ao cardápio.
   * 
   * @param itemCardapio O item a ser adicionado.
   */
  public void addItemCardapio(ItemCardapio itemCardapio) {
    itensCardapio.add(itemCardapio);
  }

  /**
   * Remove um item do cardápio.
   * 
   * @param itemCardapio O item a ser removido.
   */
  public void removeItemCardapio(ItemCardapio itemCardapio) {
    itensCardapio.remove(itemCardapio);
  }

  /**
   * Adiciona uma mesa ao restaurante.
   * 
   * @param mesa A mesa a ser adicionada.
   */
  public void addMesa(Mesa mesa) {
    mesas.add(mesa);
  }

  /**
   * Remove uma mesa do restaurante.
   * 
   * @param mesa A mesa a ser removida.
   */
  public void removeMesa(Mesa mesa) {
    mesas.remove(mesa);
  }

  /**
   * Obtém um cliente pelo telefone.
   * 
   * @param telefone O telefone do cliente.
   * @return Um Optional contendo o cliente, se encontrado.
   */
  public Optional<Cliente> getClienteByTelefone(String telefone) {
    return clientes.stream().filter(c -> c.getTelefone().equals(telefone)).findFirst();
  }

  /**
   * Adiciona um cliente ao restaurante.
   * 
   * @param cliente O cliente a ser adicionado.
   */
  public void addCliente(Cliente cliente) {
    clientes.add(cliente);
  }

  /**
   * Remove um cliente do restaurante.
   * 
   * @param cliente O cliente a ser removido.
   */
  public void removeCliente(Cliente cliente) {
    clientes.remove(cliente);
  }

  /**
   * Adiciona uma requisição ao restaurante.
   * 
   * @param requisicao A requisição a ser adicionada.
   */
  public void addRequisicao(Requisicao requisicao) {
    requisicoes.add(requisicao);
  }

  /**
   * Remove uma requisição do restaurante.
   * 
   * @param requisicao A requisição a ser removida.
   */
  public void removeRequisicao(Requisicao requisicao) {
    requisicoes.remove(requisicao);
  }

  /**
   * Filtra a lista de requisições com base em um predicado.
   * 
   * @param predicate O predicado para filtrar as requisições.
   * @return A lista de requisições filtradas.
   */
  private List<Requisicao> filtrarRequisicoes(Predicate<Requisicao> predicate) {
    return requisicoes.stream().filter(predicate).collect(Collectors.toList());
  }

  /**
   * Obtém as requisições que não foram atendidas.
   * 
   * @return A lista de requisições não atendidas.
   */
  public List<Requisicao> getRequisicoesNaoAtendidas() {
    return filtrarRequisicoes(r -> !r.getIsAtendida());
  }

  /**
   * Obtém as requisições que foram atendidas.
   * 
   * @return A lista de requisições atendidas.
   */
  public List<Requisicao> getRequisicoesAtendidas() {
    return filtrarRequisicoes(Requisicao::getIsAtendida);
  }

  /**
   * Obtém as requisições que não foram finalizadas.
   * 
   * @return A lista de requisições não finalizadas.
   */
  public List<Requisicao> getRequisicoesNaoFinalizadas() {
    return filtrarRequisicoes(r -> !r.getIsFinalizada());
  }

  /**
   * Obtém as requisições que foram finalizadas.
   * 
   * @return A lista de requisições finalizadas.
   */
  public List<Requisicao> getRequisicoesFinalizadas() {
    return filtrarRequisicoes(Requisicao::getIsFinalizada);
  }

  /**
   * Obtém as requisições ativas (atendidas e não finalizadas).
   * 
   * @return A lista de requisições ativas.
   */
  public List<Requisicao> getRequisicoesAtivas() {
    return filtrarRequisicoes(r -> !r.getIsFinalizada() && r.getIsAtendida());
  }

  /**
   * Obtém uma requisição pelo seu ID.
   * 
   * @param id O ID da requisição.
   * @return A requisição correspondente, ou null se não encontrada.
   */
  public Requisicao getRequisicaoPorId(Long id) {
    return requisicoes.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
  }

  /**
   * Obtém uma requisição pelo ID da mesa.
   * 
   * @param mesaId O ID da mesa.
   * @return A requisição correspondente, ou null se não encontrada.
   */
  public Requisicao getRequisicaoPorMesaId(Long mesaId) {
    return requisicoes.stream().filter(r -> r.getMesa().getId().equals(mesaId) && !r.getIsFinalizada()).findFirst()
        .orElse(null);
  }

  /**
   * Filtra a lista de mesas com base em um predicado.
   * 
   * @param predicate O predicado para filtrar as mesas.
   * @return A lista de mesas filtradas.
   */
  private List<Mesa> filtrarMesas(Predicate<Mesa> predicate) {
    return mesas.stream().filter(predicate).collect(Collectors.toList());
  }

  /**
   * Obtém as mesas disponíveis.
   * 
   * @return A lista de mesas disponíveis.
   */
  public List<Mesa> getMesasDisponiveis() {
    return filtrarMesas(m -> !m.getIsOcupada());
  }

  /**
   * Obtém as mesas ocupadas.
   * 
   * @return A lista de mesas ocupadas.
   */
  public List<Mesa> getMesasOcupadas() {
    return filtrarMesas(Mesa::getIsOcupada);
  }

  /**
   * Atualiza as requisições, associando mesas disponíveis às requisições não
   * atendidas.
   */
  public void atualizarRequisicoes() {
    getRequisicoesNaoAtendidas().forEach(req -> {
      getMesasDisponiveis().stream()
          .filter(m -> m.cabe(req.getQuantidadePessoas()))
          .findFirst()
          .ifPresent(mesa -> req.iniciarRequisicao(mesa));
    });
  }

  /**
   * Cria uma lista de itens de pedido a partir de uma lista de DTOs.
   * 
   * @param itensDTO A lista de DTOs dos itens de pedido.
   * @return A lista de itens de pedido correspondentes.
   */
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

  /**
   * Finaliza uma requisição.
   * 
   * @param requisicao A requisição a ser finalizada.
   */
  public void finalizarRequisicao(Requisicao requisicao) {
    requisicao.finalizarRequisicao();
  }

  @Override
  public String toString() {
    return "Restaurante [id=" + id + ", nome=" + nome + ", endereco=" + endereco + "]";
  }

}

package com.advanced.comidinhasveganas.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

  @OneToMany(mappedBy = "restaurante")
  private List<Mesa> mesas = new ArrayList<>();

  @OneToMany(mappedBy = "restaurante")
  private List<Cliente> clientes = new ArrayList<>();

  @OneToMany(mappedBy = "restaurante")
  private List<Requisicao> requisicoes = new ArrayList<>();

  @OneToMany(mappedBy = "restaurante")
  private List<Cardapio> cardapios = new ArrayList<>();

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

  public List<Cardapio> getCardapios() {
    return cardapios;
  }

  public void setCardapios(List<Cardapio> cardapios) {
    this.cardapios = cardapios;
  }

  public void addMesa(Mesa mesa) {
    mesas.add(mesa);
  }

  public void removeMesa(Mesa mesa) {
    mesas.remove(mesa);
  }

  public Cliente getClienteByTelefone(String telefone) {
    return clientes.stream().filter(c -> c.getTelefone().equals(telefone)).findFirst().orElse(null);
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

  public void addCardapio(Cardapio cardapio) {
    cardapios.add(cardapio);
  }

  public void removeCardapio(Cardapio cardapio) {
    cardapios.remove(cardapio);
  }

  public List<Requisicao> getRequisicoesNaoAtendidas() {
    return requisicoes.stream().filter(r -> !r.getIsAtendida()).toList();
  }

  public List<Requisicao> getRequisicoesNaoFinalizadas() {
    return requisicoes.stream().filter(r -> !r.getIsFinalizada()).toList();
  }

  public List<Mesa> getMesasDisponiveis() {
    return mesas.stream().filter(m -> !m.getIsOcupada()).toList();
  }

  public List<Mesa> getMesasOcupadas() {
    return mesas.stream().filter(m -> m.getIsOcupada()).toList();
  }

  public void atualizarRequisicoes() {
    getRequisicoesNaoAtendidas().forEach(req -> {
      getMesasDisponiveis().stream().filter(m -> m.getLugares() >= req.getQuantidadePessoas()).findFirst()
          .ifPresent(mesa -> {
            req.iniciarRequisicao(mesa);
            mesa.ocupar();
          });
    });
  }

  public void finalizarRequisicao(Requisicao requisicao) {
    requisicao.finalizarRequisicao();
    requisicao.getMesa().desocupar();
  }

  @Override
  public String toString() {
    return "Restaurante [id=" + id + ", nome=" + nome + ", endereco=" + endereco + "]";
  }

}

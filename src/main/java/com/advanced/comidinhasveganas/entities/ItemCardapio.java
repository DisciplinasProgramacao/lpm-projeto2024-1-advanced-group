package com.advanced.comidinhasveganas.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cardapio")
public class ItemCardapio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private Double preco;

  @OneToMany(mappedBy = "id.item")
  private Set<PedidoItemCardapio> pedidos = new HashSet<>();

  public ItemCardapio() {
  }

  public ItemCardapio(Long id, String nome, Double preco) {
    this.id = id;
    this.nome = nome;
    this.preco = preco;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Double getPreco() {
    return preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  @JsonIgnore
  public Set<Pedido> getPedidos() {
    Set<Pedido> set = new HashSet<>();
    for (PedidoItemCardapio x : pedidos) {
      set.add(x.getPedido());
    }
    return set;
  }

  @Override
  public String toString() {
    return "ItemCardapio [id=" + id + ", nome=" + nome + ", preco=" + preco + "]";
  }
}
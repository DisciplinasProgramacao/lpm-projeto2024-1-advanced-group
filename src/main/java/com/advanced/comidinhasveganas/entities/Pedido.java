package com.advanced.comidinhasveganas.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedidos")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "pedido")
  private List<ItemPedido> itens = new ArrayList<>();

  @JsonIgnore
  @OneToOne(mappedBy = "pedido")
  private Requisicao requisicao;

  public Pedido() {
  }

  public Pedido(Requisicao requisicao) {
    this.requisicao = requisicao;
  }

  public Pedido(List<ItemPedido> itens, Requisicao requisicao) {
    this.itens = itens;
    this.requisicao = requisicao;
  }

  public Long getId() {
    return id;
  }

  public List<ItemPedido> getItens() {
    return itens;
  }

  public void setItens(List<ItemPedido> itens) {
    this.itens = itens;
  }

  public Requisicao getRequisicao() {
    return requisicao;
  }

  public void setRequisicao(Requisicao requisicao) {
    this.requisicao = requisicao;
  }

  public void addItem(ItemPedido item) {
    itens.add(item);
  }

  public void addItens(List<ItemPedido> itens) {
    this.itens.addAll(itens);
  }

  public void removeItem(ItemPedido item) {
    itens.remove(item);
  }

  public Double getTotal() {
    return itens.stream().mapToDouble(ItemPedido::getSubTotal).sum();
  }

  @Override
  public String toString() {
    return "Pedido [id=" + id + ", itens=" + itens + ", requisicao=" + requisicao + "]";
  }

}

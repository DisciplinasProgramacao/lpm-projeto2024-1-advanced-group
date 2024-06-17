package com.advanced.comidinhasveganas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_itens_pedidos")
public class ItemPedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "item_cardapio_id")
  @JsonIgnore
  private ItemCardapio itemCardapio;

  private Integer quantidade;

  @ManyToOne
  @JoinColumn(name = "pedido_id")
  @JsonIgnore
  private Pedido pedido;

  public ItemPedido() {
  }

  public ItemPedido(ItemCardapio itemCardapio, Integer quantidade) {
    this.itemCardapio = itemCardapio;
    this.quantidade = quantidade;
  }

  public ItemPedido(ItemCardapio itemCardapio, Integer quantidade, Pedido pedido) {
    this.itemCardapio = itemCardapio;
    this.quantidade = quantidade;
    this.pedido = pedido;
  }

  public Long getId() {
    return id;
  }

  public ItemCardapio getItemCardapio() {
    return itemCardapio;
  }

  public void setItemCardapio(ItemCardapio itemCardapio) {
    this.itemCardapio = itemCardapio;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public Double getSubTotal() {
    return itemCardapio.getPreco() * quantidade;
  }

  public Pedido getPedido() {
    return pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }

  @Override
  public String toString() {
    return "ItemPedido [id=" + id + ", itemCardapio=" + itemCardapio + ", quantidade=" + quantidade + "]";
  }
}

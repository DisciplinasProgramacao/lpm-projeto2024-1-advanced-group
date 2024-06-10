package com.advanced.comidinhasveganas.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

import com.advanced.comidinhasveganas.entities.pk.PedidoItemCardapioPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_pedido_item_cardapio")
public class PedidoItemCardapio implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private PedidoItemCardapioPK id = new PedidoItemCardapioPK();

  private Integer quantidade;
  private Double preco;

  public PedidoItemCardapio() {
  }

  public PedidoItemCardapio(Pedido pedido, ItemCardapio item, Integer quantidade, Double preco) {
    id.setPedido(pedido);
    id.setItem(item);
    this.quantidade = quantidade;
    this.preco = preco;
  }

  public PedidoItemCardapioPK getId() {
    return id;
  }

  @JsonIgnore
  public Pedido getPedido() {
    return id.getPedido();
  }

  public void setPedido(Long pedidoId) {
    Pedido pedido = new Pedido();
    pedido.setId(pedidoId);
    id.setPedido(pedido);
  }

  public ItemCardapio getItem() {
    return id.getItem();
  }

  public void setItem(ItemCardapio item) {
    id.setItem(item);
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public Double getPreco() {
    return preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public Double getSubTotal() {
    return preco * quantidade;
  }

  @Override
  public String toString() {
    return getItem().getNome() + ", R$ " + String.format("%.2f", preco) + ", Quantidade: " + quantidade
        + ", Subtotal: R$ " + String.format("%.2f", getSubTotal());
  }
}

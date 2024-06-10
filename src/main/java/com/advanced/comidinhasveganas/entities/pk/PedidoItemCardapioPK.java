package com.advanced.comidinhasveganas.entities.pk;

import java.io.Serializable;

import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.entities.Pedido;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class PedidoItemCardapioPK implements Serializable {
  private static final long serialVersionUID = 1L;

  @ManyToOne
  @JoinColumn(name = "pedido_id")
  private Pedido pedido;

  @ManyToOne
  @JoinColumn(name = "item_id")
  private ItemCardapio item;

  public Pedido getPedido() {
    return pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }

  public ItemCardapio getItem() {
    return item;
  }

  public void setItem(ItemCardapio item) {
    this.item = item;
  }
}
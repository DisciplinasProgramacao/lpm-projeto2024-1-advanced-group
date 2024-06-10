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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((item == null) ? 0 : item.hashCode());
    result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PedidoItemCardapioPK other = (PedidoItemCardapioPK) obj;
    if (item == null) {
      if (other.item != null)
        return false;
    } else if (!item.equals(other.item))
      return false;
    if (pedido == null) {
      if (other.pedido != null)
        return false;
    } else if (!pedido.equals(other.pedido))
      return false;
    return true;
  }
}

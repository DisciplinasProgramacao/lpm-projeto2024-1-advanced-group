package com.advanced.comidinhasveganas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Representa um item de um pedido em um restaurante vegano.
 * Cada item de pedido tem um ID único, refere-se a um item do cardápio e uma quantidade.
 */
@Entity
@Table(name = "tb_itens_pedidos")
public class ItemPedido {

  /**
   * Identificador único do item do pedido.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Item do cardápio ao qual este item de pedido se refere.
   */
  @ManyToOne
  @JoinColumn(name = "item_cardapio_id")
  @JsonIgnore
  private ItemCardapio itemCardapio;

  /**
   * Quantidade do item do cardápio neste pedido.
   */
  private Integer quantidade;

  /**
   * Construtor padrão.
   */
  public ItemPedido() {
  }

  /**
   * Construtor para inicializar o item do pedido com um item do cardápio e uma quantidade específicas.
   *
   * @param itemCardapio Item do cardápio ao qual este item de pedido se refere.
   * @param quantidade Quantidade do item do cardápio neste pedido.
   */
  public ItemPedido(ItemCardapio itemCardapio, Integer quantidade) {
    this.itemCardapio = itemCardapio;
    this.quantidade = quantidade;
  }

  /**
   * Obtém o identificador único do item do pedido.
   *
   * @return Identificador único do item do pedido.
   */
  public Long getId() {
    return id;
  }

  /**
   * Obtém o item do cardápio ao qual este item de pedido se refere.
   *
   * @return Item do cardápio ao qual este item de pedido se refere.
   */
  public ItemCardapio getItemCardapio() {
    return itemCardapio;
  }

  /**
   * Define o item do cardápio ao qual este item de pedido se refere.
   *
   * @param itemCardapio Item do cardápio ao qual este item de pedido se refere.
   */
  public void setItemCardapio(ItemCardapio itemCardapio) {
    this.itemCardapio = itemCardapio;
  }

  /**
   * Obtém a quantidade do item do cardápio neste pedido.
   *
   * @return Quantidade do item do cardápio neste pedido.
   */
  public Integer getQuantidade() {
    return quantidade;
  }

  /**
   * Define a quantidade do item do cardápio neste pedido.
   *
   * @param quantidade Quantidade do item do cardápio neste pedido.
   */
  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  /**
   * Calcula o subtotal do item do pedido, multiplicando o preço do item do cardápio pela quantidade.
   *
   * @return Subtotal do item do pedido.
   */
  @JsonIgnore
  public Double getSubTotal() {
    return itemCardapio.getPreco() * quantidade;
  }

  /**
   * Retorna uma representação em string do item do pedido.
   *
   * @return Uma representação em string do item do pedido.
   */
  @Override
  public String toString() {
    return "ItemPedido [id=" + id + ", itemCardapio=" + itemCardapio + ", quantidade=" + quantidade + "]";
  }
}

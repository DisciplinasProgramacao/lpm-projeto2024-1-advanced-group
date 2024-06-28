package com.advanced.comidinhasveganas.dto;

public class ItemPedidoDTO {
  private Long itemId;
  private Integer quantidade;

  // Getters and Setters
  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }
}

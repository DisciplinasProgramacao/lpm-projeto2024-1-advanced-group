package com.advanced.comidinhasveganas.dto;

public class ItemRequest {
  private Long itemId;
  private Integer quantidade;

  public ItemRequest() {
  }

  public ItemRequest(Long itemId, Integer quantidade) {
    this.itemId = itemId;
    this.quantidade = quantidade;
  }

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

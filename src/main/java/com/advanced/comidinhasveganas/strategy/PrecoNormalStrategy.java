package com.advanced.comidinhasveganas.strategy;

import com.advanced.comidinhasveganas.entities.ItemPedido;

import java.util.List;

/**
 * Implementação da estratégia de cálculo do preço para pedidos normais.
 */
public class PrecoNormalStrategy implements PrecoStrategy {

  private List<ItemPedido> itens;

  public PrecoNormalStrategy(List<ItemPedido> itens) {
    this.itens = itens;
  }

  @Override
  public double calcularPreco() {
    return itens.stream().mapToDouble(ItemPedido::getSubTotal).sum();
  }
}

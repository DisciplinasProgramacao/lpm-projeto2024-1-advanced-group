package com.advanced.comidinhasveganas.strategy;

/**
 * Implementação da estratégia de cálculo do preço para pedidos fechados.
 */
public class PrecoFechadoStrategy implements PrecoStrategy {

  @Override
  public double calcularPreco() {
    return 32.0;
  }
}

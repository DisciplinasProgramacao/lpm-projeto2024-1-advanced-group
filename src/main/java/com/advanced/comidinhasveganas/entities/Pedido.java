package com.advanced.comidinhasveganas.entities;

import java.util.ArrayList;
import java.util.List;

import com.advanced.comidinhasveganas.converters.PrecoStrategyConverter;
import com.advanced.comidinhasveganas.strategy.PrecoFechadoStrategy;
import com.advanced.comidinhasveganas.strategy.PrecoNormalStrategy;
import com.advanced.comidinhasveganas.strategy.PrecoStrategy;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Representa um pedido no restaurante.
 * Cada pedido possui um ID único, uma lista de itens, um tipo e um preço total.
 */
@Entity
@Table(name = "tb_pedidos")
public class Pedido {

  /**
   * Identificador único do pedido.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Lista de itens do pedido.
   */
  @OneToMany
  @JoinColumn(name = "pedido_id")
  private List<ItemPedido> itens = new ArrayList<>();

  /**
   * Tipo do pedido (normal ou fechado).
   */
  private String tipoPedido;

  /**
   * Preço total do pedido.
   */
  private double precoTotal;

  /**
   * Estratégia de cálculo do preço do pedido.
   */
  @Convert(converter = PrecoStrategyConverter.class)
  private PrecoStrategy precoStrategy;

  /**
   * Construtor padrão.
   */
  public Pedido() {
  }

  /**
   * Construtor para inicializar o pedido com um tipo específico.
   * 
   * @param tipoPedido Tipo do pedido.
   */
  public Pedido(String tipoPedido) {
    setTipoPedido(tipoPedido);
  }

  /**
   * Obtém o identificador único do pedido.
   * 
   * @return Identificador único do pedido.
   */
  public Long getId() {
    return id;
  }

  /**
   * Obtém a lista de itens do pedido.
   * 
   * @return Lista de itens do pedido.
   */
  public List<ItemPedido> getItens() {
    return itens;
  }

  /**
   * Obtém o tipo do pedido.
   * 
   * @return Tipo do pedido.
   */
  public String getTipoPedido() {
    return tipoPedido;
  }

  /**
   * Define o tipo do pedido.
   * 
   * @param tipoPedido Tipo do pedido a ser definido.
   */
  public void setTipoPedido(String tipoPedido) {
    this.tipoPedido = tipoPedido;
    definirStrategy();
  }

  /**
   * Obtém o preço total do pedido.
   * 
   * @return Preço total do pedido.
   */
  public double getPrecoTotal() {
    return precoTotal;
  }

  /**
   * Adiciona um item ao pedido.
   * 
   * @param item Item a ser adicionado.
   */
  public void addItem(ItemPedido item) {
    itens.add(item);
  }

  /**
   * Adiciona uma lista de itens ao pedido.
   * 
   * @param itens Lista de itens a serem adicionados.
   */
  public void addItens(List<ItemPedido> itens) {
    this.itens.addAll(itens);
  }

  /**
   * Define o preço total do pedido com base no tipo de pedido.
   * Para pedidos do tipo "normal", o preço total é a soma dos subtotais dos
   * itens.
   * Para pedidos do tipo "fechado", o preço total é fixo em 32.0.
   * 
   * @throws IllegalArgumentException Se o tipo de pedido for desconhecido.
   */
  private void definirStrategy() {
    if (tipoPedido.equalsIgnoreCase("normal")) {
      this.precoStrategy = new PrecoNormalStrategy(itens);
    } else if (tipoPedido.equalsIgnoreCase("fechado")) {
      this.precoStrategy = new PrecoFechadoStrategy();
    } else {
      throw new IllegalArgumentException("Tipo de pedido desconhecido.");
    }
  }

  /**
   * Calcula o preço total do pedido com base na estratégia de cálculo de preço.
   */
  public void setPrecoTotal() {
    precoTotal = precoStrategy.calcularPreco();
  }

  /**
   * Retorna uma representação em string do pedido.
   * 
   * @return Uma representação em string do pedido.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (ItemPedido item : itens) {
      sb.append(item.toString()).append("\n");
    }
    sb.append("Preço total: R$ ").append(getPrecoTotal());
    return sb.toString();
  }
}

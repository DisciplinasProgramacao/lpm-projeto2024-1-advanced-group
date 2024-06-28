package com.advanced.comidinhasveganas.entities;

import com.advanced.comidinhasveganas.entities.enums.TipoItem;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa um item do cardápio em um restaurante vegano.
 * Cada item do cardápio tem um ID único, um nome, um preço e um tipo.
 */
@Entity
@Table(name = "tb_itens_cardapio")
public class ItemCardapio {

  /**
   * Identificador único do item do cardápio.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nome do item do cardápio.
   */
  private String nome;

  /**
   * Preço do item do cardápio.
   */
  private Double preco;

  /**
   * Tipo do item do cardápio.
   */
  @Enumerated(EnumType.STRING)
  private TipoItem tipo;

  /**
   * Construtor padrão.
   */
  public ItemCardapio() {
  }

  /**
   * Construtor para inicializar o item do cardápio com nome, preço e tipo específicos.
   *
   * @param nome Nome do item do cardápio.
   * @param preco Preço do item do cardápio.
   * @param tipo Tipo do item do cardápio.
   */
  public ItemCardapio(String nome, Double preco, TipoItem tipo) {
    this.nome = nome;
    this.preco = preco;
    this.tipo = tipo;
  }

  /**
   * Obtém o identificador único do item do cardápio.
   *
   * @return Identificador único do item do cardápio.
   */
  public Long getId() {
    return id;
  }

  /**
   * Obtém o nome do item do cardápio.
   *
   * @return Nome do item do cardápio.
   */
  public String getNome() {
    return nome;
  }

  /**
   * Define o nome do item do cardápio.
   *
   * @param nome Nome do item do cardápio.
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Obtém o preço do item do cardápio.
   *
   * @return Preço do item do cardápio.
   */
  public Double getPreco() {
    return preco;
  }

  /**
   * Define o preço do item do cardápio.
   *
   * @param preco Preço do item do cardápio.
   */
  public void setPreco(Double preco) {
    this.preco = preco;
  }

  /**
   * Obtém o tipo do item do cardápio.
   *
   * @return Tipo do item do cardápio.
   */
  public TipoItem getTipo() {
    return tipo;
  }

  /**
   * Define o tipo do item do cardápio.
   *
   * @param tipo Tipo do item do cardápio.
   */
  public void setTipo(TipoItem tipo) {
    this.tipo = tipo;
  }

  /**
   * Retorna uma representação em string do item do cardápio.
   *
   * @return Uma representação em string do item do cardápio.
   */
  @Override
  public String toString() {
    return "ItemCardapio [id=" + id + ", nome=" + nome + ", preco=" + preco + ", tipo=" + tipo + "]";
  }
}

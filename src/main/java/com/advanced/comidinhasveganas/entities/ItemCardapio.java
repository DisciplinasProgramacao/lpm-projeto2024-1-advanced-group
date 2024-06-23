package com.advanced.comidinhasveganas.entities;

<<<<<<< HEAD
import com.advanced.comidinhasveganas.entities.enums.TipoItem;
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> 0348321f99be6ca0621945cf2b55d854530f6bc4
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
<<<<<<< HEAD
 * Representa um item do cardápio em um restaurante vegano.
 * Cada item do cardápio tem um ID único, nome, preço e tipo.
=======
 * Representa um item de um pedido em um restaurante vegano.
 * Cada item de pedido tem um ID único, refere-se a um item do cardápio e uma quantidade.
>>>>>>> 0348321f99be6ca0621945cf2b55d854530f6bc4
 */
@Entity
@Table(name = "tb_itens_pedidos")
public class ItemPedido {

  /**
<<<<<<< HEAD
   * Identificador único do item do cardápio.
=======
   * Identificador único do item do pedido.
>>>>>>> 0348321f99be6ca0621945cf2b55d854530f6bc4
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
<<<<<<< HEAD
   * Nome do item do cardápio.
   */
  private String nome;

  /**
   * Preço do item do cardápio.
   */
  private Double preco;

  /**
   * Tipo do item do cardápio, representado por uma enumeração.
   */
  @Enumerated(EnumType.STRING)
  private TipoItem tipo;
=======
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
>>>>>>> 0348321f99be6ca0621945cf2b55d854530f6bc4

  /**
   * Construtor padrão.
   */
<<<<<<< HEAD
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
=======
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
>>>>>>> 0348321f99be6ca0621945cf2b55d854530f6bc4
   */
  public Long getId() {
    return id;
  }

  /**
<<<<<<< HEAD
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
   * @param nome Nome do item do cardápio a ser definido.
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
   * @param preco Preço do item do cardápio a ser definido.
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
   * @param tipo Tipo do item do cardápio a ser definido.
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
=======
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
>>>>>>> 0348321f99be6ca0621945cf2b55d854530f6bc4
  }
}

package com.advanced.comidinhasveganas.entities;

import com.advanced.comidinhasveganas.entities.enums.TipoItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_itens_cardapio")
public class ItemCardapio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private Double preco;

  @Enumerated(EnumType.STRING)
  private TipoItem tipo;

  @ManyToOne
  @JoinColumn(name = "cardapio_id")
  @JsonIgnore
  private Cardapio cardapio;

  public ItemCardapio() {
  }

  public ItemCardapio(String nome, Double preco, TipoItem tipo, Cardapio cardapio) {
    this.nome = nome;
    this.preco = preco;
    this.tipo = tipo;
    this.cardapio = cardapio;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public Double getPreco() {
    return preco;
  }

  public TipoItem getTipo() {
    return tipo;
  }

  public Cardapio getCardapio() {
    return cardapio;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public void setTipo(TipoItem tipo) {
    this.tipo = tipo;
  }

  public void setCardapio(Cardapio cardapio) {
    this.cardapio = cardapio;
  }

  @Override
  public String toString() {
    return "ItemCardapio [id=" + id + ", nome=" + nome + ", preco=" + preco + ", tipo=" + tipo + ", cardapio="
        + cardapio + "]";
  }
}

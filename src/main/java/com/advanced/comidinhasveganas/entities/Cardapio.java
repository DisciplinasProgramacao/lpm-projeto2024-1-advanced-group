package com.advanced.comidinhasveganas.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cardapios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cardapio")
public class Cardapio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @ManyToOne
  @JoinColumn(name = "restaurante_id")
  @JsonIgnore
  private Restaurante restaurante;

  @OneToMany(mappedBy = "cardapio")
  private List<ItemCardapio> itens = new ArrayList<>();

  public Cardapio() {
  }

  public Cardapio(String nome, Restaurante restaurante) {
    this.nome = nome;
    this.restaurante = restaurante;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public Restaurante getRestaurante() {
    return restaurante;
  }

  public List<ItemCardapio> getItens() {
    return itens;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setRestaurante(Restaurante restaurante) {
    this.restaurante = restaurante;
  }

  public void setItens(List<ItemCardapio> itens) {
    this.itens = itens;
  }

  public void addItem(ItemCardapio item) {
    itens.add(item);
  }

  public void removeItem(ItemCardapio item) {
    itens.remove(item);
  }

  @Override
  public String toString() {
    return "Cardapio [id=" + id + ", nome=" + nome + "]";
  }
}

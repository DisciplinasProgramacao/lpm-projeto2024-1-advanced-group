package com.advanced.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "requisicao_id")
  private Requisicao requisicao;

  @OneToMany(mappedBy = "id.pedido")
  @JsonIgnore
  private Set<PedidoItemCardapio> itens = new HashSet<>();

  public Pedido() {
  }

  public Pedido(Requisicao requisicao) {
    this.requisicao = requisicao;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Requisicao getRequisicao() {
    return requisicao;
  }

  public void setRequisicao(Requisicao requisicao) {
    this.requisicao = requisicao;
  }

  public Set<PedidoItemCardapio> getItens() {
    return itens;
  }

  public Double getTotal() {
    double sum = 0.0;
    for (PedidoItemCardapio x : itens) {
      sum += x.getSubTotal();
    }
    return sum;
  }

  @Override
  public String toString() {
    return "Pedido [id=" + id + ", requisicao=" + requisicao + "]";
  }
}

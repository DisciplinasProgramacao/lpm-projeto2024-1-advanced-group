package com.advanced.comidinhasveganas.entities;

import java.util.List;

import com.advanced.comidinhasveganas.entities.enums.TipoItem;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MENU_FECHADO")
public class MenuFechado extends Cardapio {

  private Double precoFixo;

  public MenuFechado() {
  }

  public MenuFechado(String nome, Restaurante restaurante, Double precoFixo) {
    super(nome, restaurante);
    this.precoFixo = precoFixo;
  }

  public Double getPrecoFixo() {
    return precoFixo;
  }

  public void setPrecoFixo(Double precoFixo) {
    this.precoFixo = precoFixo;
  }

  public List<ItemCardapio> getPratos() {
    return getItens().stream()
        .filter(item -> item.getTipo() == TipoItem.COMIDA)
        .toList();
  }

  public List<ItemCardapio> getBebidas() {
    return getItens().stream()
        .filter(item -> item.getTipo() == TipoItem.BEBIDA)
        .toList();
  }

  @Override
  public String toString() {
    return "MenuFechado [precoFixo=" + precoFixo + ", toString()=" + super.toString() + "]";
  }
}

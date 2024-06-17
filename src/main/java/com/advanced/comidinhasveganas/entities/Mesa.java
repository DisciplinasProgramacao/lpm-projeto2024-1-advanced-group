package com.advanced.comidinhasveganas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_mesas")
public class Mesa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer lugares;

  private Boolean isOcupada = false;

  @ManyToOne
  @JoinColumn(name = "restaurante_id")
  @JsonIgnore
  private Restaurante restaurante;

  public Mesa() {
  }

  public Mesa(Integer lugares) {
    setLugares(lugares);
  }

  public Mesa(Integer lugares, Restaurante restaurante) {
    setLugares(lugares);
    setRestaurante(restaurante);
  }

  public Long getId() {
    return id;
  }

  public Integer getLugares() {
    return lugares;
  }

  public Boolean getIsOcupada() {
    return isOcupada;
  }

  public void setLugares(Integer lugares) {
    this.lugares = lugares;
  }

  public void setIsOcupada(Boolean isOcupada) {
    this.isOcupada = isOcupada;
  }

  public void ocupar() {
    this.isOcupada = true;
  }

  public void desocupar() {
    this.isOcupada = false;
  }

  public Restaurante getRestaurante() {
    return restaurante;
  }

  public void setRestaurante(Restaurante restaurante) {
    this.restaurante = restaurante;
  }

  @Override
  public String toString() {
    return "Mesa [id=" + id + ", lugares=" + lugares + ", isOcupada=" + isOcupada + "]";
  }

}

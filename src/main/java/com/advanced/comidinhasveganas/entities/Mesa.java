package com.advanced.comidinhasveganas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_mesas")
public class Mesa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer lugares;

  private Boolean isOcupada;

  public Mesa() {
  }

  public Mesa(Long id, Integer lugares, Boolean isOcupada) {
    this.id = id;
    this.lugares = lugares;
    this.isOcupada = isOcupada;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getLugares() {
    return lugares;
  }

  public void setLugares(Integer lugares) {
    this.lugares = lugares;
  }

  public Boolean getIsOcupada() {
    return isOcupada;
  }

  public void setIsOcupada(Boolean isOcupada) {
    this.isOcupada = isOcupada;
  }

  @Override
  public String toString() {
    return "Mesa [id=" + id + ", lugares=" + lugares + ", isOcupada=" + isOcupada + "]";
  }
}

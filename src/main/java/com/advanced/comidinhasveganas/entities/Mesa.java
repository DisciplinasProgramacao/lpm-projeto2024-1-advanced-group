package com.advanced.comidinhasveganas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa uma mesa no restaurante.
 * Cada mesa tem um ID único, uma quantidade de lugares e um status indicando se está ocupada ou não.
 */
@Entity
@Table(name = "tb_mesas")
public class Mesa {

  /**
   * Identificador único da mesa.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Número de lugares disponíveis na mesa.
   */
  private Integer lugares;

  /**
   * Status indicando se a mesa está ocupada.
   */
  private Boolean isOcupada = false;

  /**
   * Construtor padrão.
   */
  public Mesa() {
  }

  /**
   * Construtor para inicializar a mesa com um número específico de lugares.
   * 
   * @param lugares Número de lugares da mesa.
   */
  public Mesa(Integer lugares) {
    setLugares(lugares);
  }

  /**
   * Obtém o identificador único da mesa.
   * 
   * @return Identificador único da mesa.
   */
  public Long getId() {
    return id;
  }

  /**
   * Obtém o número de lugares disponíveis na mesa.
   * 
   * @return Número de lugares da mesa.
   */
  public Integer getLugares() {
    return lugares;
  }

  /**
   * Obtém o status indicando se a mesa está ocupada.
   * 
   * @return true se a mesa está ocupada, false caso contrário.
   */
  public Boolean getIsOcupada() {
    return isOcupada;
  }

  /**
   * Define o número de lugares disponíveis na mesa.
   * 
   * @param lugares Número de lugares a ser definido.
   */
  public void setLugares(Integer lugares) {
    this.lugares = lugares;
  }

  /**
   * Define o status indicando se a mesa está ocupada.
   * 
   * @param isOcupada true para marcar a mesa como ocupada, false caso contrário.
   */
  public void setIsOcupada(Boolean isOcupada) {
    this.isOcupada = isOcupada;
  }

  /**
   * Marca a mesa como ocupada.
   */
  public void ocupar() {
    this.isOcupada = true;
  }

  /**
   * Marca a mesa como desocupada.
   */
  public void desocupar() {
    this.isOcupada = false;
  }

  /**
   * Verifica se a mesa pode acomodar uma determinada quantidade de pessoas.
   * 
   * @param quantidadePessoas Número de pessoas a serem acomodadas.
   * @return true se a mesa pode acomodar a quantidade de pessoas, false caso contrário.
   */
  public Boolean cabe(Integer quantidadePessoas) {
    return lugares >= quantidadePessoas;
  }

  /**
   * Retorna uma representação em string da mesa.
   * 
   * @return Uma representação em string da mesa.
   */
  @Override
  public String toString() {
    return "Mesa [id=" + id + ", lugares=" + lugares + ", isOcupada=" + isOcupada + "]";
  }

}

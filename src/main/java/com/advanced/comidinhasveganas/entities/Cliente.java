package com.advanced.comidinhasveganas.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_clientes")
public class Cliente implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String telefone;

  @ManyToOne
  @JoinColumn(name = "restaurante_id")
  @JsonIgnore
  private Restaurante restaurante;

  public Cliente() {
  }

  public Cliente(String nome, String telefone) {
    setNome(nome);
    setTelefone(telefone);
  }

  public Cliente(String nome, String telefone, Restaurante restaurante) {
    setNome(nome);
    setTelefone(telefone);
    setRestaurante(restaurante);
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public Restaurante getRestaurante() {
    return restaurante;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public void setRestaurante(Restaurante restaurante) {
    this.restaurante = restaurante;
  }

  @Override
  public String toString() {
    return "Cliente [id=" + id + ", nome=" + nome + ", telefone=" + telefone + "]";
  }

}

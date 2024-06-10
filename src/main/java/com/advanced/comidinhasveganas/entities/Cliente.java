package com.advanced.comidinhasveganas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_clientes")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String telefone;

  public Cliente() {
  }

  public Cliente(Long id, String nome, String telefone) {
    this.id = id;
    this.nome = nome;
    this.telefone = telefone;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  @Override
  public String toString() {
    return "Cliente [id=" + id + ", nome=" + nome + ", telefone=" + telefone + "]";
  }

}

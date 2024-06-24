package com.advanced.comidinhasveganas.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa um cliente no sistema.
 * Esta classe é mapeada para a tabela "tb_clientes" no banco de dados.
 * 
 * @version 1.0
 * @since 2023
 */
@Entity
@Table(name = "tb_clientes")
public class Cliente implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Identificador único do cliente.
   * Gerado automaticamente com a estratégia de identidade.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nome do cliente.
   */
  private String nome;

  /**
   * Telefone do cliente.
   */
  private String telefone;

  /**
   * Construtor padrão.
   * Utilizado pelo JPA.
   */
  public Cliente() {
  }

  /**
   * Construtor com parâmetros.
   * 
   * @param nome Nome do cliente.
   * @param telefone Telefone do cliente.
   */
  public Cliente(String nome, String telefone) {
    setNome(nome);
    setTelefone(telefone);
  }

  /**
   * Obtém o identificador do cliente.
   * 
   * @return O identificador do cliente.
   */
  public Long getId() {
    return id;
  }

  /**
   * Obtém o nome do cliente.
   * 
   * @return O nome do cliente.
   */
  public String getNome() {
    return nome;
  }

  /**
   * Obtém o telefone do cliente.
   * 
   * @return O telefone do cliente.
   */
  public String getTelefone() {
    return telefone;
  }

  /**
   * Define o nome do cliente.
   * 
   * @param nome O nome do cliente.
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Define o telefone do cliente.
   * 
   * @param telefone O telefone do cliente.
   */
  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  /**
   * Retorna uma representação em string do cliente.
   * 
   * @return Uma string com o identificador, nome e telefone do cliente.
   */
  @Override
  public String toString() {
    return "Cliente [id=" + id + ", nome=" + nome + ", telefone=" + telefone + "]";
  }

}

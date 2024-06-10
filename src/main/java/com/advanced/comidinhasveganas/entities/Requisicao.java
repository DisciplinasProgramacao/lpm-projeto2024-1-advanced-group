package com.advanced.comidinhasveganas.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_requisicoes")
public class Requisicao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  private int quantidadePessoas;

  @ManyToOne
  @JoinColumn(name = "mesa_id")
  private Mesa mesa;

  private Boolean isAtendida = false;

  private Boolean isFinalizada = false;

  private LocalDateTime dataHoraInicio;

  private LocalDateTime dataHoraFim;

  private Double totalConta = 0.0;

  private Double totalPorPessoa = 0.0;

  @JsonIgnore
  @OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Pedido> pedidos = new ArrayList<>();

  public Requisicao() {
  }

  public Requisicao(int quantidadePessoas, String nome, String telefone) {
    this.cliente = new Cliente(null, nome, telefone);
    this.quantidadePessoas = quantidadePessoas;
    this.mesa = null;
    this.dataHoraInicio = null;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public int getQuantidadePessoas() {
    return quantidadePessoas;
  }

  public void setQuantidadePessoas(int quantidadePessoas) {
    this.quantidadePessoas = quantidadePessoas;
  }

  public Mesa getMesa() {
    return mesa;
  }

  public void setMesa(Mesa mesa) {
    this.mesa = mesa;
  }

  public Boolean getIsAtendida() {
    return isAtendida;
  }

  public void setIsAtendida(Boolean isAtendida) {
    this.isAtendida = isAtendida;
  }

  public Boolean getIsFinalizada() {
    return isFinalizada;
  }

  public void setIsFinalizada(Boolean isFinalizada) {
    this.isFinalizada = isFinalizada;
  }

  public LocalDateTime getDataHoraInicio() {
    return dataHoraInicio;
  }

  public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
    this.dataHoraInicio = dataHoraInicio;
  }

  public LocalDateTime getDataHoraFim() {
    return dataHoraFim;
  }

  public void setDataHoraFim(LocalDateTime dataHoraFim) {
    this.dataHoraFim = dataHoraFim;
  }

  public List<Pedido> getPedidos() {
    return pedidos;
  }

  public void setPedidos(List<Pedido> pedidos) {
    this.pedidos = pedidos;
  }

  public void addPedido(Pedido pedido) {
    pedido.setRequisicao(this);
    this.pedidos.add(pedido);
  }

  public Double getTotalConta() {
    return totalConta;
  }

  public void setTotalConta(Double totalConta) {
    this.totalConta = totalConta;
  }

  public Double getTotalPorPessoa() {
    return totalPorPessoa;
  }

  public void setTotalPorPessoa(Double totalPorPessoa) {
    this.totalPorPessoa = totalPorPessoa;
  }

  @Override
  public String toString() {
    return "Requisicao [id=" + id + ", cliente=" + cliente + ", quantidadePessoas=" + quantidadePessoas + ", mesa="
        + mesa + ", isAtendida=" + isAtendida + ", isFinalizada=" + isFinalizada + ", dataHoraInicio=" + dataHoraInicio
        + ", dataHoraFim=" + dataHoraFim + ", totalConta=" + totalConta + ", totalPorPessoa=" + totalPorPessoa
        + ", pedidos=" + pedidos + "]";
  }
}

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

  private boolean isAtendida;

  private boolean isFinalizada;

  private LocalDateTime dataHoraInicio;

  private LocalDateTime dataHoraFim;

  private Double totalConta;

  private Double totalPorPessoa;

  @JsonIgnore
  @OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Pedido> pedidos = new ArrayList<>();

  public Requisicao() {
  }

  public Requisicao(int quantidadePessoas) {
    this.cliente = new Cliente(null, "Luiz Paulo", "12345678");
    this.quantidadePessoas = quantidadePessoas;
    this.mesa = null;
    this.isAtendida = false;
    this.isFinalizada = false;
    this.dataHoraInicio = LocalDateTime.now();
    this.totalConta = 0.0;
    this.totalPorPessoa = 0.0;
  }

  public Requisicao(Cliente cliente, int quantidadePessoas, Mesa mesa, boolean isAtendida,
      boolean isFinalizada, LocalDateTime dataHoraInicio) {
    this.cliente = cliente;
    this.quantidadePessoas = quantidadePessoas;
    this.mesa = mesa;
    this.isAtendida = isAtendida;
    this.isFinalizada = isFinalizada;
    this.dataHoraInicio = dataHoraInicio;
    this.totalConta = 0.0;
    this.totalPorPessoa = 0.0;
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

  public boolean isAtendida() {
    return isAtendida;
  }

  public void setAtendida(boolean isAtendida) {
    this.isAtendida = isAtendida;
  }

  public boolean isFinalizada() {
    return isFinalizada;
  }

  public void setFinalizada(boolean isFinalizada) {
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
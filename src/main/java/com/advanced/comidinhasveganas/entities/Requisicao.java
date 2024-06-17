package com.advanced.comidinhasveganas.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

  private Integer quantidadePessoas;

  @ManyToOne
  @JoinColumn(name = "mesa_id")
  private Mesa mesa = null;

  private Boolean isAtendida = false;

  private Boolean isFinalizada = false;

  private LocalDateTime dataHoraInicio = null;

  private LocalDateTime dataHoraFim = null;

  private Double totalConta = 0.0;

  private Double totalPorPessoa = 0.0;

  @OneToOne
  private Pedido pedido;

  @ManyToOne
  @JoinColumn(name = "restaurante_id")
  @JsonIgnore
  private Restaurante restaurante;

  public Requisicao() {
  }

  public Requisicao(Cliente cliente, Integer quantidadePessoas, Restaurante restaurante) {
    setCliente(cliente);
    setQuantidadePessoas(quantidadePessoas);
    setRestaurante(restaurante);
  }

  public Long getId() {
    return id;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Integer getQuantidadePessoas() {
    return quantidadePessoas;
  }

  public void setQuantidadePessoas(Integer quantidadePessoas) {
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

  public Restaurante getRestaurante() {
    return restaurante;
  }

  public void setRestaurante(Restaurante restaurante) {
    this.restaurante = restaurante;
  }

  public Pedido getPedido() {
    return pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }

  public void iniciarRequisicao(Mesa mesa) {
    this.mesa = mesa;
    this.dataHoraInicio = LocalDateTime.now();
    this.isAtendida = true;
    setPedido(new Pedido());
  }

  public void finalizarRequisicao() {
    this.dataHoraFim = LocalDateTime.now();
    this.isFinalizada = true;
    this.totalConta = calcularTotal() * restaurante.getTaxaServico();
    this.totalPorPessoa = totalConta / quantidadePessoas;
  }

  private Double calcularTotal() {
    double total = 0.0;
    List<Long> menuFechadoIds = pedido.getItens().stream()
        .map(item -> item.getItemCardapio().getCardapio())
        .filter(cardapio -> cardapio instanceof MenuFechado)
        .map(Cardapio::getId)
        .distinct()
        .collect(Collectors.toList());

    for (ItemPedido item : pedido.getItens()) {
      if (item.getItemCardapio().getCardapio() instanceof MenuFechado) {
        Long cardapioId = item.getItemCardapio().getCardapio().getId();
        if (menuFechadoIds.contains(cardapioId)) {
          total += ((MenuFechado) item.getItemCardapio().getCardapio()).getPrecoFixo();
          menuFechadoIds.remove(cardapioId);
        }
      } else {
        total += item.getSubTotal();
      }
    }
    return total;
  }

  public void cancelarRequisicao() {
    isFinalizada = true;
  }

  @Override
  public String toString() {
    return "Requisicao [cliente=" + cliente + ", dataHoraFim=" + dataHoraFim + ", dataHoraInicio=" + dataHoraInicio
        + ", id=" + id + ", isAtendida=" + isAtendida + ", isFinalizada=" + isFinalizada + ", mesa=" + mesa
        + ", quantidadePessoas=" + quantidadePessoas + ", totalConta=" + totalConta
        + ", totalPorPessoa=" + totalPorPessoa + "]";
  }

}

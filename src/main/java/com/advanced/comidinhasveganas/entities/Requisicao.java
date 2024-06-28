package com.advanced.comidinhasveganas.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * A classe Requisicao representa um pedido feito por um cliente em um restaurante.
 * Ela inclui informações sobre o cliente, mesa, quantidade de pessoas, status do pedido,
 * horários de início e fim, total da conta, total por pessoa e os pedidos realizados.
 */
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

  @OneToMany
  @JoinColumn(name = "requisicao_id")
  private List<Pedido> pedidos;

  /**
   * Construtor padrão.
   */
  public Requisicao() {
  }

  /**
   * Construtor que inicializa a Requisicao com um cliente e quantidade de pessoas.
   *
   * @param cliente           O cliente que fez a requisição.
   * @param quantidadePessoas A quantidade de pessoas na mesa.
   */
  public Requisicao(Cliente cliente, Integer quantidadePessoas) {
    setCliente(cliente);
    setQuantidadePessoas(quantidadePessoas);
  }

  /**
   * Retorna o ID da requisição.
   *
   * @return O ID da requisição.
   */
  public Long getId() {
    return id;
  }

  /**
   * Retorna o cliente que fez a requisição.
   *
   * @return O cliente.
   */
  public Cliente getCliente() {
    return cliente;
  }

  /**
   * Define o cliente que fez a requisição.
   *
   * @param cliente O cliente a ser definido.
   */
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  /**
   * Retorna a quantidade de pessoas na mesa.
   *
   * @return A quantidade de pessoas.
   */
  public Integer getQuantidadePessoas() {
    return quantidadePessoas;
  }

  /**
   * Define a quantidade de pessoas na mesa.
   *
   * @param quantidadePessoas A quantidade de pessoas a ser definida.
   */
  public void setQuantidadePessoas(Integer quantidadePessoas) {
    this.quantidadePessoas = quantidadePessoas;
  }

  /**
   * Retorna a mesa associada à requisição.
   *
   * @return A mesa.
   */
  public Mesa getMesa() {
    return mesa;
  }

  /**
   * Define a mesa associada à requisição.
   *
   * @param mesa A mesa a ser definida.
   */
  public void setMesa(Mesa mesa) {
    this.mesa = mesa;
  }

  /**
   * Retorna se a requisição foi atendida.
   *
   * @return Verdadeiro se a requisição foi atendida, falso caso contrário.
   */
  public Boolean getIsAtendida() {
    return isAtendida;
  }

  /**
   * Define se a requisição foi atendida.
   *
   * @param isAtendida Verdadeiro se a requisição foi atendida, falso caso contrário.
   */
  public void setIsAtendida(Boolean isAtendida) {
    this.isAtendida = isAtendida;
  }

  /**
   * Define a requisição como atendida.
   */
  public void setAtendida() {
    this.isAtendida = true;
  }

  /**
   * Retorna se a requisição foi finalizada.
   *
   * @return Verdadeiro se a requisição foi finalizada, falso caso contrário.
   */
  public Boolean getIsFinalizada() {
    return isFinalizada;
  }

  /**
   * Define se a requisição foi finalizada.
   *
   * @param isFinalizada Verdadeiro se a requisição foi finalizada, falso caso contrário.
   */
  public void setIsFinalizada(Boolean isFinalizada) {
    this.isFinalizada = isFinalizada;
  }

  /**
   * Define a requisição como finalizada.
   */
  public void setFinalizada() {
    this.isFinalizada = true;
  }

  /**
   * Retorna a data e hora de início da requisição.
   *
   * @return A data e hora de início.
   */
  public LocalDateTime getDataHoraInicio() {
    return dataHoraInicio;
  }

  /**
   * Define a data e hora de início da requisição.
   *
   * @param dataHoraInicio A data e hora de início a ser definida.
   */
  public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
    this.dataHoraInicio = dataHoraInicio;
  }

  /**
   * Define a data e hora de início da requisição como o momento atual.
   */
  public void setDataHoraInicioLocal() {
    this.dataHoraInicio = LocalDateTime.now();
  }

  /**
   * Retorna a data e hora de fim da requisição.
   *
   * @return A data e hora de fim.
   */
  public LocalDateTime getDataHoraFim() {
    return dataHoraFim;
  }

  /**
   * Define a data e hora de fim da requisição.
   *
   * @param dataHoraFim A data e hora de fim a ser definida.
   */
  public void setDataHoraFim(LocalDateTime dataHoraFim) {
    this.dataHoraFim = dataHoraFim;
  }

  /**
   * Define a data e hora de fim da requisição como o momento atual.
   */
  public void setDataHoraFimLocal() {
    this.dataHoraFim = LocalDateTime.now();
  }

  /**
   * Retorna o total da conta.
   *
   * @return O total da conta.
   */
  public Double getTotalConta() {
    return totalConta;
  }

  /**
   * Define o total da conta.
   *
   * @param totalConta O total da conta a ser definido.
   */
  public void setTotalConta(Double totalConta) {
    this.totalConta = totalConta;
  }

  /**
   * Retorna o total por pessoa.
   *
   * @return O total por pessoa.
   */
  public Double getTotalPorPessoa() {
    return totalPorPessoa;
  }

  /**
   * Define o total por pessoa.
   *
   * @param totalPorPessoa O total por pessoa a ser definido.
   */
  public void setTotalPorPessoa(Double totalPorPessoa) {
    this.totalPorPessoa = totalPorPessoa;
  }

  /**
   * Retorna a lista de pedidos associados à requisição.
   *
   * @return A lista de pedidos.
   */
  public List<Pedido> getPedidos() {
    return pedidos;
  }

  /**
   * Define a lista de pedidos associados à requisição.
   *
   * @param pedidos A lista de pedidos a ser definida.
   */
  public void setPedidos(List<Pedido> pedidos) {
    this.pedidos = pedidos;
  }

  /**
   * Adiciona um pedido à lista de pedidos.
   *
   * @param pedido O pedido a ser adicionado.
   */
  public void addPedido(Pedido pedido) {
    pedidos.add(pedido);
  }

  /**
   * Remove um pedido da lista de pedidos.
   *
   * @param pedido O pedido a ser removido.
   */
  public void removePedido(Pedido pedido) {
    pedidos.remove(pedido);
  }

  /**
   * Inicia a requisição associando-a a uma mesa e marcando o horário de início.
   *
   * @param mesa A mesa a ser associada.
   */
  public void iniciarRequisicao(Mesa mesa) {
    setMesa(mesa);
    setDataHoraInicioLocal();
    setAtendida();
    mesa.ocupar();
  }

  /**
   * Finaliza a requisição marcando o horário de fim, calculando os totais e liberando a mesa.
   */
  public void finalizarRequisicao() {
    setDataHoraFimLocal();
    setFinalizada();
    mesa.desocupar();
    calcularTotais();
  }

  /**
   * Calcula os totais da conta e por pessoa, incluindo um acréscimo de 10%.
   */
  public void calcularTotais() {
    Double tc = pedidos.stream().mapToDouble(Pedido::getPrecoTotal).sum() * 1.1;

    Double tp = tc / getQuantidadePessoas();

    setTotalConta(tc);
    setTotalPorPessoa(tp);
  }

  /**
   * Cancela a requisição.
   */
  public void cancelarRequisicao() {
    isFinalizada = true;
  }

  @Override
  public String toString() {
    return String.format(
        "Requisicao [id=%d, cliente=%s, quantidadePessoas=%d, mesa=%s, isAtendida=%s, isFinalizada=%s, dataHoraInicio=%s, dataHoraFim=%s, totalConta=%.2f, totalPorPessoa=%.2f, pedidos=%s]",
        id, cliente, quantidadePessoas, mesa, isAtendida, isFinalizada, dataHoraInicio, dataHoraFim, totalConta,
        totalPorPessoa, pedidos);
  }

}

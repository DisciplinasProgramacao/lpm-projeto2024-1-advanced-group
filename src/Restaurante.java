import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Restaurante {

  private static final Double TAXA_SERVICO = 1.1;
  private static final AtomicLong idGenerator = new AtomicLong(0);

  private Long id;
  private String nome;
  private String endereco;
  private List<Mesa> mesas = new ArrayList<>();
  private List<Cliente> clientes = new ArrayList<>();
  private List<Requisicao> requisicoes = new ArrayList<>();
  private List<Cardapio> cardapios = new ArrayList<>();

  public Restaurante() {
    this.id = idGenerator.incrementAndGet();
  }

  public Restaurante(String nome, String endereco) {
    this.id = idGenerator.incrementAndGet();
    this.nome = nome;
    this.endereco = endereco;
  }

  public Long getId() {
    return id;
  }

  public Double getTaxaServico() {
    return TAXA_SERVICO;
  }

  public String getNome() {
    return nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public List<Mesa> getMesas() {
    return mesas;
  }

  public List<Cliente> getClientes() {
    return clientes;
  }

  public List<Requisicao> getRequisicoes() {
    return requisicoes;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public void setClientes(List<Cliente> clientes) {
    this.clientes = clientes;
  }

  public void setMesas(List<Mesa> mesas) {
    this.mesas = mesas;
  }

  public void setRequisicoes(List<Requisicao> requisicoes) {
    this.requisicoes = requisicoes;
  }

  public List<Cardapio> getCardapios() {
    return cardapios;
  }

  public void setCardapios(List<Cardapio> cardapios) {
    this.cardapios = cardapios;
  }

  public void addMesa(Mesa mesa) {
    mesas.add(mesa);
  }

  public void removeMesa(Mesa mesa) {
    mesas.remove(mesa);
  }

  public void addCliente(Cliente cliente) {
    clientes.add(cliente);
  }

  public void removeCliente(Cliente cliente) {
    clientes.remove(cliente);
  }

  public void addRequisicao(Requisicao requisicao) {
    requisicoes.add(requisicao);
  }

  public void removeRequisicao(Requisicao requisicao) {
    requisicoes.remove(requisicao);
  }

  public void addCardapio(Cardapio cardapio) {
    cardapios.add(cardapio);
  }

  public void removeCardapio(Cardapio cardapio) {
    cardapios.remove(cardapio);
  }

  public Optional<Cliente> getClienteByTelefone(String telefone) {
    return clientes.stream().filter(c -> c.getTelefone().equals(telefone)).findFirst();
  }

  // Métodos para filtrar listas com base em predicados
  private List<Requisicao> filtrarRequisicoes(Predicate<Requisicao> predicate) {
    return requisicoes.stream().filter(predicate).collect(Collectors.toList());
  }

  // Métodos específicos para filtrar requisicoes e mesas
  public List<Requisicao> getRequisicoesNaoAtendidas() {
    return filtrarRequisicoes(r -> !r.getIsAtendida());
  }

  public List<Requisicao> getRequisicoesAtendidas() {
    return filtrarRequisicoes(Requisicao::getIsAtendida);
  }

  public List<Requisicao> getRequisicoesNaoFinalizadas() {
    return filtrarRequisicoes(r -> !r.getIsFinalizada());
  }

  public List<Requisicao> getRequisicoesFinalizadas() {
    return filtrarRequisicoes(Requisicao::getIsFinalizada);
  }

  public Requisicao getRequisicaoPorMesaId(Long mesaId) {
    return requisicoes.stream().filter(r -> r.getMesa().getId().equals(mesaId) && !r.getIsFinalizada()).findFirst()
        .orElse(null);
  }

  private List<Mesa> filtrarMesas(Predicate<Mesa> predicate) {
    return mesas.stream().filter(predicate).collect(Collectors.toList());
  }

  public List<Mesa> getMesasDisponiveis() {
    return filtrarMesas(m -> !m.getIsOcupada());
  }

  public List<Mesa> getMesasOcupadas() {
    return filtrarMesas(Mesa::getIsOcupada);
  }

  // Método para atualizar requisições
  public void atualizarRequisicoes() {
    getRequisicoesNaoAtendidas().forEach(req -> {
      getMesasDisponiveis().stream()
          .filter(m -> m.cabe(req.getQuantidadePessoas()))
          .findFirst()
          .ifPresent(mesa -> req.iniciarRequisicao(mesa));
    });
  }

  public void finalizarRequisicao(Requisicao requisicao) {
    requisicao.finalizarRequisicao();
    requisicao.getMesa().desocupar();
  }

  // public ItemCardapio getItemCardapioPorNome(String nome) {
  // for (Cardapio cardapio : cardapios) {
  // for (ItemCardapio item : cardapio.getItens()) {
  // if (item.getNome().equalsIgnoreCase(nome)) {
  // return item;
  // }
  // }
  // }
  // return null;
  // }

  // public ItemPedido criarItemPedidoPorNome(String nomeItemCardapio, int
  // quantidade) {
  // ItemCardapio itemCardapio = getItemCardapioPorNome(nomeItemCardapio);
  // if (itemCardapio != null) {
  // return new ItemPedido(itemCardapio, quantidade);
  // }
  // throw new IllegalArgumentException("Item do cardápio não encontrado: " +
  // nomeItemCardapio);
  // }

  @Override
  public String toString() {
    return "Restaurante [id=" + id + ", nome=" + nome + ", endereco=" + endereco + "]";
  }
}

import enums.TipoItem;
import java.util.concurrent.atomic.AtomicLong;

public class ItemCardapio {

  private static final AtomicLong idGenerator = new AtomicLong(0);
  private Long id;
  private String nome;
  private Double preco;
  private TipoItem tipo;

  public ItemCardapio() {
    this.id = idGenerator.incrementAndGet();
  }

  public ItemCardapio(String nome, Double preco, TipoItem tipo) {
    this.id = idGenerator.incrementAndGet();
    this.nome = nome;
    this.preco = preco;
    this.tipo = tipo;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public Double getPreco() {
    return preco;
  }

  public TipoItem getTipo() {
    return tipo;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public void setTipo(TipoItem tipo) {
    this.tipo = tipo;
  }

  @Override
  public String toString() {
    return "ItemCardapio [id=" + id + ", nome=" + nome + ", preco=" + preco + ", tipo=" + tipo + "]";
  }
}

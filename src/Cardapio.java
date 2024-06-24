import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Cardapio {

  private static final AtomicLong idGenerator = new AtomicLong(0);
  private Long id;
  private String nome;
  private List<ItemCardapio> itens = new ArrayList<>();

  public Cardapio() {
    this.id = idGenerator.incrementAndGet();
  }

  public Cardapio(String nome) {
    this.id = idGenerator.incrementAndGet();
    this.nome = nome;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public List<ItemCardapio> getItens() {
    return itens;
  }

  public ItemCardapio getItemById(Long id) {
    return itens.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setItens(List<ItemCardapio> itens) {
    this.itens = itens;
  }

  public void addItem(ItemCardapio item) {
    itens.add(item);
  }

  public void removeItem(ItemCardapio item) {
    itens.remove(item);
  }

  @Override
  public String toString() {
    return "Cardapio [id=" + id + ", nome=" + nome + "]";
  }
}

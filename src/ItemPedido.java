import java.util.concurrent.atomic.AtomicLong;

public class ItemPedido {

  private static final AtomicLong idGenerator = new AtomicLong(0);
  private Long id;
  private ItemCardapio itemCardapio;
  private Integer quantidade;

  public ItemPedido() {
    this.id = idGenerator.incrementAndGet();
  }

  public ItemPedido(ItemCardapio itemCardapio, Integer quantidade) {
    this.id = idGenerator.incrementAndGet();
    this.itemCardapio = itemCardapio;
    this.quantidade = quantidade;
  }

  public Long getId() {
    return id;
  }

  public ItemCardapio getItemCardapio() {
    return itemCardapio;
  }

  public void setItemCardapio(ItemCardapio itemCardapio) {
    this.itemCardapio = itemCardapio;
  }

  public Integer getQuantidade() {
    return quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public Double getSubTotal() {
    return itemCardapio.getPreco() * quantidade;
  }

  @Override
  public String toString() {
    return "ItemPedido [id=" + id + ", itemCardapio=" + itemCardapio + ", quantidade=" + quantidade + "]";
  }
}

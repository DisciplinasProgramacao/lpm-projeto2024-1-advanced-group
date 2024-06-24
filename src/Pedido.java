import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Pedido {

  private static final AtomicLong idGenerator = new AtomicLong(0);
  private Long id;
  private List<ItemPedido> itens = new ArrayList<>();
  private PrecoStrategy precoStrategy;

  public Pedido() {
    this.id = idGenerator.incrementAndGet();
  }

  public Pedido(PrecoStrategy precoStrategy) {
    this.id = idGenerator.incrementAndGet();
    this.precoStrategy = precoStrategy;
  }

  public Long getId() {
    return id;
  }

  public void addItemPedido(ItemPedido item) {
    itens.add(item);
  }

  public void adicionarItemPorId(Cardapio cardapio, Long itemId, Integer quantidade) {
    ItemCardapio item = cardapio.getItemById(itemId);
    if (item != null) {
      this.addItemPedido(new ItemPedido(item, quantidade));
    } else {
      System.out.println("Item não encontrado no cardápio.");
    }
  }

  public void removeItemPedido(ItemPedido item) {
    itens.remove(item);
  }

  public double calcularPreco() {
    return precoStrategy.calcularPreco(itens);
  }

  public List<ItemPedido> getItens() {
    return itens;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (ItemPedido item : itens) {
      sb.append(item.toString()).append("\n");
    }
    sb.append("Preço total: R$ ").append(calcularPreco());
    return sb.toString();
  }

}
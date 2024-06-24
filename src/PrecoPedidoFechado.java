import java.util.List;

public class PrecoPedidoFechado implements PrecoStrategy {

  private double precoFixo;

  public PrecoPedidoFechado(double precoFixo) {
    this.precoFixo = precoFixo;
  }

  @Override
  public double calcularPreco(List<ItemPedido> itens) {
    return precoFixo;
  }
}
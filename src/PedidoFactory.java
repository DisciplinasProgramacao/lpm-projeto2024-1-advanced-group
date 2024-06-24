import java.util.HashMap;

public class PedidoFactory {
  HashMap<String, Pedido> pedidos = new HashMap<String, Pedido>();

  public PedidoFactory(){
    pedidos.put("normal", new Pedido(new PrecoPedidoNormal()));
    pedidos.put("fechado", new Pedido(new PrecoPedidoFechado(10.0)));
    
  }
  public Pedido criarPedido(String tipo, double... precoFixo) {
    Pedido pedido = pedidos.get(tipo);
    if(pedido=null)
      throw new IllegalArgumentException("Tipo de pedido desconhecido.");
  }
}
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class Restaurante {

  private List<Mesa> mesas;
  private List<Requisicao> filaRequisicoes;
   //private Cardapio cardapio;

  public Restaurante() {
    this.mesas = new LinkedList<>();
    this.filaRequisicoes = new LinkedList<>();
   // this.cardapio = new LinkedList<>();
  }

  public List<Mesa> getMesas() {
    return mesas;
  }

  public List<Requisicao> getFilaRequisicoes() {
    return filaRequisicoes;
  }

  // public String getCardapio() {
  //   return cardapio;
  // }

  public void adicionarMesa(int idMesa, int capacidade) {
    Mesa mesa = new Mesa(idMesa, capacidade);
    mesas.add(mesa);
  }

  // public void adicionarItemCardapio(int idItem, String nome, double preco) {
  //   ItemCardapio item = new ItemCardapio(idItem, nome, preco);
  //  cardapio.adicionarItem (item);
  // }

  public void adicionarRequisicao(Cliente cliente, int numeroDePessoas) {
    Requisicao requisicao = new Requisicao(numeroDePessoas, cliente, null, numeroDePessoas, null);
    filaRequisicoes.add(requisicao);
  }

//   public String toStringCardapio() {
//     StringBuilder resultado = new StringBuilder();
//     for (ItemCardapio item : cardapio) {
//         resultado.append(item.toString()+"\n")
//     }
//     return cardapio.toString();
// }

  public String printListaRequisicoes() {
    StringBuilder resultado = new StringBuilder("Requisições: \n");
    for(Requisicao req : filaRequisicoes){
      resultado.append(req.toString()+"\n");
    }
    return resultado.toString();
  }
    public String printListaMesas(){
 StringBuilder resultado = new StringBuilder("Mesas \n");
       for(Mesa m : mesas){
        resultado.append(m.toString()+"\n");
       }
return resultado.toString();
    }

  // public void addPedidos(int m, Pedido pedido) {
  //   for (Mesa mesa : mesas) {
  //     if (mesa.getIdMesa() == m) {
  //       mesa.addPedidos(pedido);
  //       for (ItemCardapio item : cardapio) {
  //         if (item.getIdItem() == pedido.getIdItem()) {
  //           mesa.addTotalConta(item.getPreco() * pedido.getQuantidade() * 1.1);
  //         }
  //       }
  //     }
  //   }
  // }

  public void alocarCliente() {
    for (Requisicao requisicao : filaRequisicoes) {
      if (!requisicao.isAtendida()) {
        for (Mesa mesa : mesas) {
          if (mesa.getCapacidade() >= requisicao.getNumPessoas() && mesa.getClientesSentados() == 0) {
            mesa.setClientesSentados(requisicao.getNumPessoas());
            requisicao.setAtendida(true);
            requisicao.setMesa(mesa);
            break;
          }
        }
      }
    }
  }

//   public double totalConta(int idMesa) {
 
//     for(Requisicao req : filaRequisicoes){
//       if(req.ehDaMesa(idMesa)){
//          req.encerrarRequisicao();
//       }

//         return req.valorConta();
//     }
// }



  public void iniciarRestaurante() {
    for (int i = 0; i < 10; i++) {
      if (i < 4) {
        adicionarMesa(i, 4);
      } else if (i < 8) {
        adicionarMesa(i, 6);
      } else {
        adicionarMesa(i, 8);
      }
    }
  
    // adicionarItemCardapio(1, "Moqueca de Palmito", 32.0);
    // adicionarItemCardapio(2, "Falafel Assado", 20.0);
    // adicionarItemCardapio(3, "Salada Primavera com Macarrão Konjac", 25.0);
    // adicionarItemCardapio(4, "Escondidinho de Inhame", 18.0);
    // adicionarItemCardapio(5, "Strogonoff de Cogumelos", 35.0);
    // adicionarItemCardapio(6, "Caçarola de legumes", 22.0);
    // adicionarItemCardapio(7, "Água", 3.0);
    // adicionarItemCardapio(8, "Copo de suco", 7.0);
    // adicionarItemCardapio(9, "Refrigerante orgânico", 7.0);
    // adicionarItemCardapio(10, "Cerveja vegana", 9.0);
    // adicionarItemCardapio(11, "Taça de vinho vegano", 18.0);

    // System.out.println("Bem-vindo ao Restaurante Vegano!");
    // System.out.println("Cardápio:");
  }
}



//requisicao tem q fazer o pagarconta(metodo)
//eu tenho que esperar o metodo do total da conta na requisicao pra fazer o meu metodo de devoluçao de valor
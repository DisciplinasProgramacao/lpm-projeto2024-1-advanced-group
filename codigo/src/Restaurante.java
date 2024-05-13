import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Restaurante {

  private List<Mesa> mesas;
  private List<Requisicao> filaRequisicoes;
  private List<ItemCardapio> cardapio;

  public Restaurante() {
    this.mesas = new LinkedList<>();
    this.filaRequisicoes = new LinkedList<>();
    this.cardapio = new LinkedList<>();
  }

  public List<Mesa> getMesas() {
    return mesas;
  }

  public List<Requisicao> getFilaRequisicoes() {
    return filaRequisicoes;
  }

  public List<ItemCardapio> getCardapio() {
    return cardapio;
  }

  public void adicionarMesa(int idMesa, int capacidade) {
    Mesa mesa = new Mesa(idMesa, capacidade);
    mesas.add(mesa);
  }

  public void adicionarItemCardapio(int idItem, String nome, double preco) {
    ItemCardapio item = new ItemCardapio(idItem, nome, preco);
    cardapio.add(item);
  }

  public void adicionarRequisicao(Cliente cliente, int numeroDePessoas) {
    Requisicao requisicao = new Requisicao(cliente, numeroDePessoas);
    filaRequisicoes.add(requisicao);
  }

  public void printCardapio() {
    for (ItemCardapio item : cardapio) {
      System.out.println("Item: " + item.getNome() + " - Preço: R$ " + item.getPreco());
    }
  }

  public void printListaMesas() {
    for (Mesa mesa : mesas) {
      System.out.println("Mesa " + mesa.getIdMesa() + " - Capacidade: " + mesa.getCapacidade() + " pessoas"
          + " - Clientes sentados: " + mesa.getClientesSentados() + " - Total da conta: R$ " + mesa.getTotalConta());
    }
  }

  public String printListaRequisicoes() {
    StringBuilder resultado = new StringBuilder("Requisições: \n");
    for(Requisicao req : filaRequisicoes){
      resultado.append(req.toString()+"\n");
    }
    return resultado.toString();
  }
  

  public void printPedidos(int idMesa) {
    for (Mesa mesa : mesas) {
      if (mesa.getIdMesa() == idMesa) {
        System.out.println("Pedidos da mesa " + mesa.getIdMesa() + ":");
        for (Pedido pedido : mesa.getPedidos()) {
          for (ItemCardapio item : cardapio) {
            if (item.getIdItem() == pedido.getIdItem()) {
              System.out.println("Item: " + item.getNome() + " - Quantidade: " + pedido.getQuantidade());
            }
          }
        }
      }
    }
  }

  public void addPedidos(int m, Pedido pedido) {
    for (Mesa mesa : mesas) {
      if (mesa.getIdMesa() == m) {
        mesa.addPedidos(pedido);
        for (ItemCardapio item : cardapio) {
          if (item.getIdItem() == pedido.getIdItem()) {
            mesa.addTotalConta(item.getPreco() * pedido.getQuantidade() * 1.1);
          }
        }
      }
    }
  }

  public void alocarCliente() {
    for (Requisicao requisicao : filaRequisicoes) {
      if (!requisicao.isAtendida()) {
        for (Mesa mesa : mesas) {
          if (mesa.getCapacidade() >= requisicao.getNumeroDePessoas() && mesa.getClientesSentados() == 0) {
            mesa.setClientesSentados(requisicao.getNumeroDePessoas());
            requisicao.setAtendida(true);
            requisicao.setMesa(mesa);
            break;
          }
        }
      }
    }
  }

  public void printTotalConta(int idMesa) {
    for (Mesa mesa : mesas) {
      if (mesa.getIdMesa() == idMesa) {
        System.out.println("Total da conta: R$ " + mesa.getTotalConta());
        System.out.println("Total da conta por pessoa: R$ " + mesa.getTotalConta() / mesa.getClientesSentados());
      }
    }
  }

  public void pagarConta(int idMesa) {
    for (Mesa mesa : mesas) {
      if (mesa.getIdMesa() == idMesa) {
        for (Requisicao req : filaRequisicoes) {
          if (req.getMesa().getIdMesa() == mesa.getIdMesa()) {
            req.setMesa(null);
            req.setDataHoraSaida(LocalDateTime.now());
            req.setConta(mesa.getTotalConta());

            
        mesa.pagarConta();
            break;
          }
        }
        break;
      }
    }
  }

  public void iniciarRestaurante(Restaurante restaurante) {
    for (int i = 0; i < 10; i++) {
      if (i < 4) {
        restaurante.adicionarMesa(i, 4);
      } else if (i < 8) {
        restaurante.adicionarMesa(i, 6);
      } else {
        restaurante.adicionarMesa(i, 8);
      }
    }

    restaurante.adicionarItemCardapio(1, "Moqueca de Palmito", 32.0);
    restaurante.adicionarItemCardapio(2, "Falafel Assado", 20.0);
    restaurante.adicionarItemCardapio(3, "Salada Primavera com Macarrão Konjac", 25.0);
    restaurante.adicionarItemCardapio(4, "Escondidinho de Inhame", 18.0);
    restaurante.adicionarItemCardapio(5, "Strogonoff de Cogumelos", 35.0);
    restaurante.adicionarItemCardapio(6, "Caçarola de legumes", 22.0);
    restaurante.adicionarItemCardapio(7, "Água", 3.0);
    restaurante.adicionarItemCardapio(8, "Copo de suco", 7.0);
    restaurante.adicionarItemCardapio(9, "Refrigerante orgânico", 7.0);
    restaurante.adicionarItemCardapio(10, "Cerveja vegana", 9.0);
    restaurante.adicionarItemCardapio(11, "Taça de vinho vegano", 18.0);

    System.out.println("Bem-vindo ao Restaurante Vegano!");
    System.out.println("Cardápio:");
    restaurante.printCardapio();
  }
}
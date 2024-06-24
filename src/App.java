import java.util.Scanner;

import enums.TipoItem;

public class App {
  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    Restaurante restaurante = initializeRest();
    Cardapio cardapio = restaurante.getCardapios().get(0);
    Cardapio cardapioFechado = restaurante.getCardapios().get(1);

    while (true) {
      exibirMenuPrincipal();
      int opcao = Integer.parseInt(lerString(scanner, "Escolha uma opção: "));

      switch (opcao) {
        case 1:
          String telefone = lerString(scanner, "Digite o telefone do cliente: ");
          Cliente cliente = restaurante.getClienteByTelefone(telefone)
              .orElseGet(() -> new Cliente(lerString(scanner, "Digite o nome do cliente"), telefone));

          restaurante.addCliente(cliente);
          int qtdPessoas = Integer.parseInt(lerString(scanner, "Digite a quantidade de pessoas: "));
          Requisicao requisicao = new Requisicao(cliente, qtdPessoas);
          restaurante.addRequisicao(requisicao);
          restaurante.atualizarRequisicoes();
          break;

        case 2:
          System.out.println("Requisições:");
          restaurante.getRequisicoes().forEach(System.out::println);
          break;

        case 3:
          System.out.println("Selecione a mesa para add pedido");
          restaurante.getMesasOcupadas().forEach(System.out::println);
          Long idMesa = Long.parseLong(lerString(scanner, "Digite o id da mesa: "));

          System.out.println("Adicionando itens ao pedido");
          Pedido pedidoNormal = PedidoFactory.criarPedido("normal");
          restaurante.getRequisicaoPorMesaId(idMesa).addPedido(pedidoNormal);

          int continuar = 1;
          while (continuar == 1) {
            cardapio.getItens().forEach(System.out::println);
            Long id = Long.parseLong(lerString(scanner, "Digite o id do item que deseja adicionar ao pedido: "));
            int quantidade = Integer.parseInt(lerString(scanner, "Digite a quantidade: "));
            pedidoNormal.adicionarItemPorId(cardapio, id, quantidade);
            continuar = Integer.parseInt(lerString(scanner, "Deseja adicionar mais itens? (1 - sim, 0 - não)"));
          }
          break;

        case 4:
          System.out.println("Selecione a mesa para add pedido fechado");
          restaurante.getMesasOcupadas().forEach(System.out::println);
          Long idMesa1 = Long.parseLong(lerString(scanner, "Digite o id da mesa: "));

          System.out.println("Adicionando itens ao pedido");
          Pedido pedidoFechado = PedidoFactory.criarPedido("fechado", 32.0);
          restaurante.getRequisicaoPorMesaId(idMesa1).addPedido(pedidoFechado);

          cardapioFechado.getItens().stream().filter(i -> i.getTipo().equals(TipoItem.COMIDA))
              .forEach(System.out::println);
          Long id = Long.parseLong(lerString(scanner, "Digite o id da comida que deseja adicionar ao pedido: "));
          pedidoFechado.adicionarItemPorId(cardapioFechado, id, 1);
          for (int j = 1; j < 3; j++) {
            cardapioFechado.getItens().stream().filter(i -> i.getTipo().equals(TipoItem.BEBIDA))
                .forEach(System.out::println);
            id = Long.parseLong(
                lerString(scanner, "Digite o id da " + j + "ª" + " bebida que deseja adicionar ao pedido: "));
            pedidoFechado.adicionarItemPorId(cardapioFechado, id, 1);
          }
          break;

        case 5:
          System.out.println("Selecione a mesa para fechar a conta");
          restaurante.getMesasOcupadas().forEach(System.out::println);
          Long idMesa2 = Long.parseLong(lerString(scanner, "Digite o id da mesa: "));
          Requisicao req = restaurante.getRequisicaoPorMesaId(idMesa2);
          restaurante.finalizarRequisicao(req);
          restaurante.atualizarRequisicoes();
          break;

        case 7:
          System.out.println("Saindo...");
          scanner.close();
          System.exit(0);
          break;

        default:
          System.out.println("Opção inválida. Tente novamente.");
      }

    }
  }

  public static Restaurante initializeRest() {
    Restaurante restaurante = new Restaurante("Restaurante do Zé", "Rua do Zé, 123");

    Cliente[] clientes = {
        new Cliente("Victor", "1"),
        new Cliente("Marcos Rocha", "2"),
        new Cliente("Leonardo Silva", "3"),
        new Cliente("Réver", "4"),
        new Cliente("Junior César", "6"),
        new Cliente("Pierre", "5"),
        new Cliente("Leandro Donizete", "8"),
        new Cliente("Ronaldinho", "10"),
        new Cliente("Diego Tardelli", "9"),
        new Cliente("Bernard", "11"),
        new Cliente("Jô", "7")
    };

    for (Cliente cliente : clientes) {
      restaurante.addCliente(cliente);
    }

    Mesa[] mesas = {
        new Mesa(4),
        new Mesa(4),
        new Mesa(4),
        new Mesa(4),
        new Mesa(6),
        new Mesa(6),
        new Mesa(6),
        new Mesa(6),
        new Mesa(8),
        new Mesa(8)
    };

    for (Mesa mesa : mesas) {
      restaurante.addMesa(mesa);
    }

    Cardapio cardapio = new Cardapio("Cardapio");
    Cardapio cardapioFechado = new Cardapio("Cardapio Fechado");

    ItemCardapio[] itens = {
        new ItemCardapio("Moqueca de Palmito", 32.0, TipoItem.COMIDA),
        new ItemCardapio("Falafel Assado", 20.0, TipoItem.COMIDA),
        new ItemCardapio("Salada Primavera com Macarrão Konjac", 25.0, TipoItem.COMIDA),
        new ItemCardapio("Escondidinho de Inhame", 18.0, TipoItem.COMIDA),
        new ItemCardapio("Strogonoff de Cogumelos", 35.0, TipoItem.COMIDA),
        new ItemCardapio("Caçarola de legumes", 22.0, TipoItem.COMIDA),
        new ItemCardapio("Água", 3.0, TipoItem.BEBIDA),
        new ItemCardapio("Copo de Suco", 7.0, TipoItem.BEBIDA),
        new ItemCardapio("Refrigerante Orgânico", 7.0, TipoItem.BEBIDA),
        new ItemCardapio("Cerveja Vegana", 9.0, TipoItem.BEBIDA),
        new ItemCardapio("Taça de Vinho Vegano", 18.0, TipoItem.BEBIDA)
    };

    for (ItemCardapio item : itens) {
      cardapio.addItem(item);
    }

    cardapioFechado.addItem(itens[1]);
    cardapioFechado.addItem(itens[5]);
    cardapioFechado.addItem(itens[7]);
    cardapioFechado.addItem(itens[8]);
    cardapioFechado.addItem(itens[9]);

    restaurante.addCardapio(cardapio);
    restaurante.addCardapio(cardapioFechado);

    return restaurante;
  }

  public static void exibirMenuPrincipal() {
    System.out.println("1 - Adicionar cliente");
    System.out.println("2 - Listar requisições");
    System.out.println("3 - Adicionar pedido");
    System.out.println("4 - Aicionar pedido fechado");
    System.out.println("5 - Fechar conta");
    System.out.println("7 - Sair");
  }

  public static String lerString(Scanner sc, String mensagem) {
    System.out.println(mensagem);
    String nome = sc.nextLine();
    return nome;
  }
}

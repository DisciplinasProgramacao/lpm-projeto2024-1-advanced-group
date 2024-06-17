package com.advanced.comidinhasveganas.runners;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.advanced.comidinhasveganas.entities.*;
import com.advanced.comidinhasveganas.exceptions.ResourceNotFoundException;
import com.advanced.comidinhasveganas.services.*;

@Component
@Order(12)
public class InitializeRestauranteRunner implements CommandLineRunner {

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private MesaService mesaService;

  @Autowired
  private RestauranteService restauranteService;

  @Autowired
  private RequisicaoService requisicaoService;

  @Autowired
  private CardapioService cardapioService;

  @Autowired
  private ItemCardapioService itemCardapioService;

  @Autowired
  private PedidoService pedidoService;

  @Autowired
  private ItemPedidoService itemPedidoService;

  @Override
  public void run(String... args) throws Exception {
    Long restauranteId = 1L;
    Restaurante restaurante = carregarRestaurante(restauranteId);

    try (Scanner scanner = new Scanner(System.in)) {
      boolean exit = false;

      while (!exit) {
        System.out.println("Menu Principal:");
        System.out.println("1. Criar Requisição");
        System.out.println("2. Adicionar Pedido");
        System.out.println("3. Finalizar Requisição");
        System.out.println("4. Atualizar e Persistir Entidades");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");

        int escolha = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (escolha) {
          case 1:
            criarRequisicao(scanner, restaurante);
            restaurante.atualizarRequisicoes();
            System.out.println("Requisições atualizadas.");
            break;
          case 2:
            adicionarPedido(scanner, restaurante);
            break;
          case 3:
            finalizarRequisicao(scanner, restaurante);
            restaurante.atualizarRequisicoes();
            break;
          case 4:
            atualizarEPersistirEntidades(restaurante);
            restaurante = carregarRestaurante(restauranteId); // Refresh entities
            System.out.println("Entidades atualizadas e persistidas.");
            break;
          case 5:
            exit = true;
            break;
          default:
            System.out.println("Opção inválida. Tente novamente.");
        }
      }
    }
  }

  private Restaurante carregarRestaurante(Long restauranteId) {
    Restaurante restaurante = restauranteService.findById(restauranteId)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurante não encontrado"));

    carregarEntidadesAssociadas(restaurante);
    return restaurante;
  }

  private void carregarEntidadesAssociadas(Restaurante restaurante) {
    List<Mesa> mesas = mesaService.findByRestauranteId(restaurante.getId());
    restaurante.setMesas(mesas);

    List<Cliente> clientes = clienteService.findByRestauranteId(restaurante.getId());
    restaurante.setClientes(clientes);

    List<Requisicao> requisicoes = requisicaoService.findByRestauranteId(restaurante.getId());
    restaurante.setRequisicoes(requisicoes);

    List<ItemCardapio> itensCardapio = itemCardapioService.findByCardapioRestauranteId(restaurante.getId());
    List<Cardapio> cardapios = cardapioService.findByRestauranteId(restaurante.getId());
    restaurante.setCardapios(cardapios);

    List<Pedido> pedidos = pedidoService.findByRequisicaoRestauranteId(restaurante.getId());
    List<ItemPedido> itensPedido = itemPedidoService.findByPedidoRequisicaoRestauranteId(restaurante.getId());

    associarEntidades(requisicoes, pedidos, cardapios, itensCardapio, itensPedido);
  }

  private void associarEntidades(List<Requisicao> requisicoes, List<Pedido> pedidos,
      List<Cardapio> cardapios, List<ItemCardapio> itensCardapio,
      List<ItemPedido> itensPedido) {
    requisicoes.forEach(r -> {
      Pedido pedido = pedidos.stream()
          .filter(p -> p.getRequisicao().getId().equals(r.getId()))
          .findFirst()
          .orElse(null);
      r.setPedido(pedido);
    });

    cardapios.forEach(c -> {
      List<ItemCardapio> itens = itensCardapio.stream()
          .filter(i -> i.getCardapio().getId().equals(c.getId()))
          .collect(Collectors.toList());
      c.setItens(itens);
    });

    pedidos.forEach(p -> {
      List<ItemPedido> itens = itensPedido.stream()
          .filter(i -> i.getPedido().getId().equals(p.getId()))
          .collect(Collectors.toList());
      p.setItens(itens);
    });
  }

  private void criarRequisicao(Scanner scanner, Restaurante restaurante) {
    System.out.print("Telefone do cliente: ");
    String telefone = scanner.nextLine();
    Cliente cliente = restaurante.getClienteByTelefone(telefone);

    if (cliente == null) {
      System.out.print("Nome do cliente: ");
      String nome = scanner.nextLine();
      cliente = new Cliente(nome, telefone, restaurante);
      restaurante.addCliente(cliente);
    } else {
      System.out.println("Cliente encontrado: " + cliente.getNome());
    }

    System.out.print("Quantidade de pessoas: ");
    int quantidadePessoas = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    Requisicao requisicao = new Requisicao(cliente, quantidadePessoas, restaurante);
    restaurante.addRequisicao(requisicao);

    System.out.println("Requisição criada com sucesso.");
  }

  private void adicionarPedido(Scanner scanner, Restaurante restaurante) {
    List<Mesa> mesasOcupadas = restaurante.getMesasOcupadas();
    if (mesasOcupadas.isEmpty()) {
      System.out.println("Nenhuma mesa ocupada no momento.");
      return;
    }

    System.out.println("Mesas Ocupadas:");
    for (int i = 0; i < mesasOcupadas.size(); i++) {
      System.out.println(
          (i + 1) + ". Mesa " + mesasOcupadas.get(i).getId() + " - " + mesasOcupadas.get(i).getLugares() + " lugares");
    }

    System.out.print("Escolha uma mesa pelo número: ");
    int mesaNumero = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (mesaNumero < 1 || mesaNumero > mesasOcupadas.size()) {
      System.out.println("Número de mesa inválido.");
      return;
    }

    Mesa mesaSelecionada = mesasOcupadas.get(mesaNumero - 1);
    Requisicao requisicao = restaurante.getRequisicoes().stream()
        .filter(r -> r.getMesa().equals(mesaSelecionada))
        .findFirst()
        .orElse(null);

    if (requisicao == null) {
      System.out.println("Requisição não encontrada para a mesa selecionada.");
      return;
    }

    System.out.println("Escolha o tipo de cardápio:");
    System.out.println("1. Cardápio Aberto");
    System.out.println("2. Menu Fechado");
    int tipoCardapio = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (tipoCardapio == 1) {
      adicionarPedidoAberto(scanner, requisicao, restaurante);
    } else if (tipoCardapio == 2) {
      adicionarPedidoFechado(scanner, requisicao, restaurante);
    } else {
      System.out.println("Tipo de cardápio inválido.");
    }
  }

  private void adicionarPedidoAberto(Scanner scanner, Requisicao requisicao, Restaurante restaurante) {
    Cardapio cardapioAberto = restaurante.getCardapios().stream().filter(c -> c.getNome().equals("Principal"))
        .findFirst().orElse(null);

    if (cardapioAberto == null) {
      System.out.println("Cardápio aberto não encontrado.");
      return;
    }

    boolean continuar = true;
    while (continuar) {
      System.out.println("Itens disponíveis no cardápio:");
      List<ItemCardapio> itens = cardapioAberto.getItens();
      for (int i = 0; i < itens.size(); i++) {
        System.out.println((i + 1) + ". " + itens.get(i).getNome() + " - R$ " + itens.get(i).getPreco());
      }

      System.out.print("Escolha o item pelo número: ");
      int itemNumero = scanner.nextInt();
      scanner.nextLine(); // Consume newline
      System.out.print("Quantidade: ");
      int quantidade = scanner.nextInt();
      scanner.nextLine(); // Consume newline

      if (itemNumero < 1 || itemNumero > itens.size()) {
        System.out.println("Item inválido.");
        return;
      }

      ItemCardapio itemCardapio = itens.get(itemNumero - 1);
      ItemPedido itemPedido = new ItemPedido(itemCardapio, quantidade, requisicao.getPedido());
      requisicao.getPedido().addItem(itemPedido);

      System.out.print("Deseja adicionar mais itens? (s/n): ");
      String resposta = scanner.nextLine();
      continuar = resposta.equalsIgnoreCase("s");
    }

    System.out.println("Itens adicionados ao pedido.");
  }

  private void adicionarPedidoFechado(Scanner scanner, Requisicao requisicao, Restaurante restaurante) {
    MenuFechado menuFechado = (MenuFechado) restaurante.getCardapios().get(1);

    if (menuFechado == null) {
      System.out.println("Menu fechado não encontrado.");
      return;
    }

    System.out.println("Selecione 1 prato:");
    List<ItemCardapio> pratos = menuFechado.getPratos();
    for (int i = 0; i < pratos.size(); i++) {
      System.out.println((i + 1) + ". " + pratos.get(i).getNome());
    }
    int pratoNumero = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (pratoNumero < 1 || pratoNumero > pratos.size()) {
      System.out.println("Prato inválido.");
      return;
    }
    ItemCardapio prato = pratos.get(pratoNumero - 1);

    System.out.println("Selecione a bebida 1:");
    List<ItemCardapio> bebidas = menuFechado.getBebidas();
    for (int i = 0; i < bebidas.size(); i++) {
      System.out.println((i + 1) + ". " + bebidas.get(i).getNome());
    }
    int bebida1Numero = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (bebida1Numero < 1 || bebida1Numero > bebidas.size()) {
      System.out.println("Bebida inválida.");
      return;
    }
    ItemCardapio bebida1 = bebidas.get(bebida1Numero - 1);

    System.out.println("Selecione a bebida 2:");
    for (int i = 0; i < bebidas.size(); i++) {
      System.out.println((i + 1) + ". " + bebidas.get(i).getNome());
    }
    int bebida2Numero = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (bebida2Numero < 1 || bebida2Numero > bebidas.size()) {
      System.out.println("Bebida inválida.");
      return;
    }
    ItemCardapio bebida2 = bebidas.get(bebida2Numero - 1);

    requisicao.getPedido().addItem(new ItemPedido(prato, 1, requisicao.getPedido()));
    requisicao.getPedido().addItem(new ItemPedido(bebida1, 1, requisicao.getPedido()));
    requisicao.getPedido().addItem(new ItemPedido(bebida2, 1, requisicao.getPedido()));

    System.out.println("Itens adicionados ao pedido fechado.");
  }

  private void finalizarRequisicao(Scanner scanner, Restaurante restaurante) {
    List<Mesa> mesasOcupadas = restaurante.getMesasOcupadas();
    if (mesasOcupadas.isEmpty()) {
      System.out.println("Nenhuma mesa ocupada no momento.");
      return;
    }

    System.out.println("Mesas Ocupadas:");
    for (int i = 0; i < mesasOcupadas.size(); i++) {
      System.out.println(
          (i + 1) + ". Mesa " + mesasOcupadas.get(i).getId() + " - " + mesasOcupadas.get(i).getLugares() + " lugares");
    }

    System.out.print("Escolha uma mesa pelo número: ");
    int mesaNumero = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    if (mesaNumero < 1 || mesaNumero > mesasOcupadas.size()) {
      System.out.println("Número de mesa inválido.");
      return;
    }

    Mesa mesaSelecionada = mesasOcupadas.get(mesaNumero - 1);
    Requisicao requisicao = restaurante.getRequisicoes().stream()
        .filter(r -> r.getMesa().equals(mesaSelecionada))
        .findFirst()
        .orElse(null);

    if (requisicao == null) {
      System.out.println("Requisição não encontrada para a mesa selecionada.");
      return;
    }

    restaurante.finalizarRequisicao(requisicao);
    System.out.println("Requisição finalizada. Total da conta: R$ " + requisicao.getTotalConta());
  }

  private void atualizarEPersistirEntidades(Restaurante restaurante) {
    restaurante.getClientes().forEach(c -> Optional.ofNullable(c.getId()).ifPresentOrElse(
        id -> clienteService.update(id, c),
        () -> clienteService.insert(c)));

    restaurante.getMesas().forEach(m -> Optional.ofNullable(m.getId()).ifPresentOrElse(
        id -> mesaService.update(id, m),
        () -> mesaService.insert(m)));

    restaurante.getCardapios().forEach(c -> c.getItens().forEach(i -> Optional.ofNullable(i.getId()).ifPresentOrElse(
        id -> itemCardapioService.update(id, i),
        () -> itemCardapioService.insert(i))));

    restaurante.getCardapios().forEach(c -> Optional.ofNullable(c.getId()).ifPresentOrElse(
        id -> cardapioService.update(id, c),
        () -> cardapioService.insert(c)));

    restaurante.getRequisicoes().forEach(r -> {
      Pedido p = r.getPedido();
      if (p != null) {
        Optional.ofNullable(p.getId()).ifPresentOrElse(
            id -> pedidoService.update(id, p),
            () -> pedidoService.insert(p));
      }
    });

    restaurante.getRequisicoes().forEach(r -> {
      Pedido p = r.getPedido();
      if (p != null) {
        p.getItens().forEach(i -> Optional.ofNullable(i.getId()).ifPresentOrElse(
            id -> itemPedidoService.update(id, i),
            () -> itemPedidoService.insert(i)));
      }
    });

    restaurante.getRequisicoes().forEach(r -> Optional.ofNullable(r.getId()).ifPresentOrElse(
        id -> requisicaoService.update(id, r),
        () -> requisicaoService.insert(r)));

    restauranteService.update(restaurante.getId(), restaurante);
  }
}

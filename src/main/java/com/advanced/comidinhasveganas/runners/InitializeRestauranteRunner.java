package com.advanced.comidinhasveganas.runners;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.advanced.comidinhasveganas.dto.ItemRequest;
import com.advanced.comidinhasveganas.entities.Cliente;
import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.entities.Mesa;
import com.advanced.comidinhasveganas.entities.Pedido;
import com.advanced.comidinhasveganas.entities.Requisicao;
import com.advanced.comidinhasveganas.services.ClienteService;
import com.advanced.comidinhasveganas.services.ItemCardapioService;
import com.advanced.comidinhasveganas.services.MesaService;
import com.advanced.comidinhasveganas.services.PedidoItemCardapioService;
import com.advanced.comidinhasveganas.services.PedidoService;
import com.advanced.comidinhasveganas.services.RequisicaoService;

@Component
@Order(4)
public class InitializeRestauranteRunner implements CommandLineRunner {
  private static final Logger logger = LoggerFactory.getLogger(InitializeRestauranteRunner.class);

  private static final Integer NUMERO_COMIDAS_MENU_FECHADO = 1;
  private static final Integer NUMERO_BEBIDAS_MENU_FECHADO = 2;
  private static final Double PRECO_MENU_FECHADO = 32.0;

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private RequisicaoService requisicaoService;

  @Autowired
  private MesaService mesaService;

  @Autowired
  private ItemCardapioService itemCardapioService;

  @Autowired
  private PedidoService pedidoService;

  @Autowired
  private PedidoItemCardapioService pedidoItemCardapioService;

  @Override
  public void run(String... args) throws Exception {
    logger.info("Inicializando restaurante...");
    try (Scanner sc = new Scanner(System.in)) {
      boolean sair = false;
      while (!sair) {
        exibirMenu();
        int opcao = Integer.parseInt(String.valueOf(sc.nextLine().charAt(0)));

        switch (opcao) {
          case 1:
            menuClientes(sc);
            break;

          case 2:
            menuRequisicoes(sc);
            break;

          case 3:
            menuMesas(sc);
            break;

          case 4:
            menuCardapio(sc);
          case 0:
            System.out.println("Saindo...");
            sair = true;
            break;

          default:
            System.out.println("Opção inválida.");
        }
      }

    } catch (Exception e) {
      System.out.println("Ocorreu um erro: " + e.getMessage());
      e.printStackTrace();
    } finally {
      logger.info("Restaurante finalizado.");
      ((AutoCloseable) applicationContext).close();
    }
  }

  private void exibirMenu() {
    System.out.println("=== Sistema de Gerenciamento de Restaurante ===");
    System.out.println("1 - Menu de Clientes");
    System.out.println("2 - Menu de Requisições");
    System.out.println("3 - Menu de Mesas");
    System.out.println("4 - Menu de Cardapio");
    System.out.println("5 - Financeiro");
    System.out.println("0 - Sair");
    System.out.print("Digite a opção desejada: ");
  }

  private void menuClientes(Scanner sc) {
    System.out.println("=== Menu de Clientes ===");
    System.out.println("1 - Listar todos os clientes");
    System.out.println("2 - Buscar cliente por telefone");
    System.out.println("3 - Inserir novo cliente");
    System.out.println("4 - Atualizar cliente");
    System.out.println("5 - Deletar cliente");
    System.out.println("0 - Voltar");
    System.out.print("Digite a opção desejada: ");

    int opcao = Integer.parseInt(String.valueOf(sc.nextLine().charAt(0)));

    switch (opcao) {
      case 1:
        handleListarTodosClientes();
        break;

      case 2:
        String telefone = receberTelefoneString(sc);
        Cliente cliente = handleBuscarClientePorTelefone(telefone);
        System.out.println(cliente == null ? "Cliente não encontrado." : "Cliente encontrado: " + cliente);
        break;

      case 3:
        handleInserirNovoCliente(sc);
        break;

      case 4:
        handleAtualizarCliente(sc);
        break;

      case 5:
        handleDeletarCliente(sc);
        break;

      case 0:
        break;

      default:
        System.out.println("Opção inválida.");
    }
  }

  private void menuRequisicoes(Scanner sc) {
    System.out.println("=== Menu de Requisições ===");
    System.out.println("1 - Criar Requisição");
    System.out.println("2 - Atualizar Fila de Requisições");
    System.out.println("3 - Apagar Requisicao");
    System.out.println("0 - Voltar");

    System.out.print("Digite a opção desejada: ");
    int opcao = Integer.parseInt(String.valueOf(sc.nextLine().charAt(0)));

    switch (opcao) {
      case 1:
        handleCriarRequisicao(sc);
        handleAtualizarFila(sc);
        break;

      case 2:
        handleAtualizarFila(sc);
        break;

      case 3:
        handleExcluirRequisicao(sc);
        break;

      case 0:
        break;

      default:
        System.out.println("Opção inválida.");
    }
  }

  private void menuMesas(Scanner sc) {
    System.out.println("=== Menu de Mesas ===");
    System.out.println("1 - Listar Mesas Ocupadas");
    System.out.println("2 - Criar Pedido Menu Aberto");
    System.out.println("3 - Criar Pedido Menu Fechado");
    System.out.println("4 - Fechar Conta");
    System.out.println("0 - Voltar");

    System.out.print("Digite a opção desejada: ");
    int opcao = Integer.parseInt(String.valueOf(sc.nextLine().charAt(0)));

    switch (opcao) {
      case 1:
        handleListarMesasOcupadas(sc).forEach(System.out::println);
        break;

      case 2:
        handleAddItensNoPedido(sc);
        break;

      case 3:
        handlePedidoMenuFechado(sc);
        break;

      case 4:
        handleFecharConta(sc);
        handleAtualizarFila(sc);
      case 0:
        break;

      default:
        System.out.println("Opção inválida.");
    }
  }

  private void menuCardapio(Scanner sc) {
    System.out.println("=== Menu de Cardápio ===");
    System.out.println("1 - Listar Itens do Cardápio");
    System.out.println("2 - Adicionar Item ao Cardápio");
    System.out.println("3 - Atualizar Item do Cardápio");
    System.out.println("4 - Deletar Item do Cardápio");
    System.out.println("0 - Voltar");

    System.out.print("Digite a opção desejada: ");
    int opcao = Integer.parseInt(String.valueOf(sc.nextLine().charAt(0)));

    switch (opcao) {
      case 1:
        handleListarItensCardapio();
        break;

      case 2:
        handleAdicionarItemCardapio(sc);
        break;

      case 3:
        handleAtualizarItemCardapio(sc);
        break;

      case 4:
        handleDeletarItemCardapio(sc);
        break;

      case 0:
        break;

      default:
        System.out.println("Opção inválida.");
    }
  }

  // Receber coisas
  private void handleListarTodosClientes() {
    clienteService.findAll().forEach(System.out::println);
  }

  private String receberTelefoneString(Scanner sc) {
    System.out.print("Digite o telefone do cliente: ");
    return sc.nextLine();
  }

  private Integer receberQuantidadePessoas(Scanner sc) {
    System.out.print("Digite a quantidade de pessoas: ");
    return Integer.parseInt(sc.nextLine());
  }

  private Long receberIdDaMesa(Scanner sc) {
    System.out.print("Digite o id da mesa: ");
    return Long.parseLong(sc.nextLine());
  }

  private void imprimirMensagemDeSucesso() {
    System.out.println("Sucesso!");
  }

  private void imprimirMensagemDeErro() {
    System.out.println("Algo deu errado...");
  }

  private void imprimirCardapio() {
    itemCardapioService.findAll().forEach(System.out::println);
  }

  // MENU CLIENTES
  private String receberNomeString(Scanner sc) {
    System.out.print("Digite o nome do cliente: ");
    return sc.nextLine();
  }

  private Cliente handleBuscarClientePorTelefone(String telefone) {
    return clienteService.findByTelefone(telefone).orElse(null);
  }

  private void handleInserirNovoCliente(Scanner sc) {
    clienteService.insert(new Cliente(null, receberNomeString(sc), receberTelefoneString(sc)));
  }

  private void handleAtualizarCliente(Scanner sc) {
    String telefone = receberTelefoneString(sc);
    Cliente cliente = clienteService.findByTelefone(telefone)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    System.out.print("Digite o novo nome do cliente: ");
    String nome = sc.nextLine();
    System.out.print("Digite o novo telefone do cliente: ");
    String telefoneNovo = sc.nextLine();
    cliente.setNome(nome);
    cliente.setTelefone(telefoneNovo);
    clienteService.update(cliente.getId(), cliente);
  }

  private void handleDeletarCliente(Scanner sc) {
    Long id = clienteService.findByTelefone(receberTelefoneString(sc))
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"))
        .getId();
    clienteService.delete(id);
  }
  // FIM MENU CLIENTES

  // MENU REQUISIÇÕES
  private void handleCriarRequisicao(Scanner sc) {
    String telefone = receberTelefoneString(sc);
    Cliente cliente = handleBuscarClientePorTelefone(telefone);

    if (cliente == null) {
      cliente = clienteService.insert(new Cliente(null, receberNomeString(sc), telefone));
      System.out.println("Cliente cadastrado com sucesso." + cliente);
    } else {
      System.out.println("Cliente encontrado: " + cliente);
    }

    Integer quantidadePessoas = receberQuantidadePessoas(sc);
    requisicaoService.criarRequisicao(cliente, quantidadePessoas);
  }

  private void handleAtualizarFila(Scanner sc) {
    requisicaoService.atualizarFilaDeRequisicoes();
    System.out.println("Fila de requisições atualizada.");
  }

  private void handleExcluirRequisicao(Scanner sc) {
    String telefone = receberTelefoneString(sc);
    Requisicao req = requisicaoService.findRequisicaoByTelefoneCliente(telefone);
    if (req != null) {
      requisicaoService.delete(req.getId());
      imprimirMensagemDeSucesso();
    } else {
      imprimirMensagemDeErro();
    }
  }
  // FIM MENU REQUISICOES

  // MENU MESAS
  private List<Mesa> handleListarMesasOcupadas(Scanner sc) {
    return mesaService.findAll().stream().filter(Mesa::getIsOcupada).toList();
  }

  private Pedido handleCriarPedido(Scanner sc) {
    handleListarMesasOcupadas(sc).forEach(System.out::println);
    Long mesaId = receberIdDaMesa(sc);
    mesaService.findById(mesaId).orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

    Requisicao requisicao = requisicaoService.findRequisicaoByMesaId(mesaId)
        .orElseThrow(() -> new RuntimeException("Requisição não encontrada"));

    Pedido pedido = pedidoService.insert(new Pedido(), requisicao.getId());

    return pedido;
  }

  private ItemRequest handleCriarItemRequest(Scanner sc) {
    imprimirCardapio();
    System.out.print("Digite o id do item do cardápio: ");
    Long itemId = Long.parseLong(sc.nextLine());
    System.out.print("Digite a quantidade: ");
    Integer quantidade = Integer.parseInt(sc.nextLine());
    return new ItemRequest(itemId, quantidade);
  }

  private void handleAddItensNoPedido(Scanner sc) {
    List<ItemRequest> itemRequests = new ArrayList<>();
    Pedido pedido = handleCriarPedido(sc);

    boolean sair = false;
    while (!sair) {
      itemRequests.add(handleCriarItemRequest(sc));
      System.out.print("Deseja adicionar mais itens? (s/n) ");
      sair = sc.nextLine().charAt(0) == 'n';
    }

    pedidoItemCardapioService.insertMultiple(pedido.getId(), itemRequests);
  }

  private void handlePedidoMenuFechado(Scanner sc) {
    Pedido pedido = handleCriarPedido(sc);

    List<ItemCardapio> pratosPrincipais = handleListarPratosPrincipaisMenuFechado();
    List<ItemCardapio> bebidas = handleListarBebidasMenuFechado();

    for (int i = 1; i <= NUMERO_COMIDAS_MENU_FECHADO; i++) {
      System.out.println("Escolha seu " + i + "º" + " prato principal");
      pratosPrincipais.forEach(prato -> System.out.println(prato.getId() + " " + prato.getNome()));

      Long comidaId = Long.parseLong(sc.nextLine());
      ItemCardapio comida = itemCardapioService.findById(comidaId)
          .orElseThrow(() -> new RuntimeException("Comida não encontrada"));

      if (i == 1) {
        pedidoItemCardapioService.insert(pedido.getId(), comida.getId(), 1, PRECO_MENU_FECHADO);
      } else {
        pedidoItemCardapioService.insert(pedido.getId(), comida.getId(), 1, 0.0);
      }
    }

    for (int j = 1; j <= NUMERO_BEBIDAS_MENU_FECHADO; j++) {

      System.out.println("Escolha sua " + j + "ª" + " bebida");
      bebidas.forEach(bebida -> System.out.println(bebida.getId() + " " + bebida.getNome()));

      Long bebidaId = Long.parseLong(sc.nextLine());
      ItemCardapio bebida = itemCardapioService.findById(bebidaId)
          .orElseThrow(() -> new RuntimeException("Bebida não encontrada"));

      pedidoItemCardapioService.insert(pedido.getId(), bebida.getId(), 1, 0.0);
    }

    System.out.println("Pedido criado com sucesso.");

  }

  private List<ItemCardapio> handleListarPratosPrincipaisMenuFechado() {
    return itemCardapioService.findAll().stream().filter(ItemCardapio::isComidaNoMenuFechado).toList();
  }

  private List<ItemCardapio> handleListarBebidasMenuFechado() {
    return itemCardapioService.findAll().stream().filter(ItemCardapio::isBebidaNoMenuFechado).toList();
  }

  private void handleFecharConta(Scanner sc) {
    handleListarMesasOcupadas(sc).forEach(System.out::println);
    Long mesaId = receberIdDaMesa(sc);

    Requisicao req = requisicaoService.encerrarConta(mesaId);

    System.out.println("Conta fechada com sucesso.");
    System.out.println("Total da conta: " + req.getTotalConta());
    System.out.println("Total por pessoa: " + req.getTotalPorPessoa());
  }

  // FIM MENU MESAS

  // MENU CARDAPIO
  private void handleListarItensCardapio() {
    imprimirCardapio();
  }

  private void handleAdicionarItemCardapio(Scanner sc) {
    System.out.print("Digite o nome do item: ");
    String nome = sc.nextLine();
    System.out.print("Digite o preço do item: ");
    Double preco = Double.parseDouble(sc.nextLine());
    System.out.print("É comida no menu fechado? (s/n) ");
    boolean isComidaNoMenuFechado = sc.nextLine().charAt(0) == 's';
    System.out.print("É bebida no menu fechado? (s/n) ");
    boolean isBebidaNoMenuFechado = sc.nextLine().charAt(0) == 's';

    itemCardapioService.insert(new ItemCardapio(null, nome, preco, isComidaNoMenuFechado, isBebidaNoMenuFechado));

    imprimirMensagemDeSucesso();
  }

  private void handleAtualizarItemCardapio(Scanner sc) {
    imprimirCardapio();
    System.out.print("Digite o id do item que deseja atualizar: ");
    Long id = Long.parseLong(sc.nextLine());
    ItemCardapio item = itemCardapioService.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado"));

    System.out.print("Digite o novo nome do item: ");
    String nome = sc.nextLine();
    System.out.print("Digite o novo preço do item: ");
    Double preco = Double.parseDouble(sc.nextLine());
    System.out.print("É comida no menu fechado? (s/n) ");
    boolean isComidaNoMenuFechado = sc.nextLine().charAt(0) == 's';
    System.out.print("É bebida no menu fechado? (s/n) ");
    boolean isBebidaNoMenuFechado = sc.nextLine().charAt(0) == 's';

    item.setNome(nome);
    item.setPreco(preco);
    item.setComidaNoMenuFechado(isComidaNoMenuFechado);
    item.setBebidaNoMenuFechado(isBebidaNoMenuFechado);

    itemCardapioService.update(id, item);

    imprimirMensagemDeSucesso();
  }

  private void handleDeletarItemCardapio(Scanner sc) {
    imprimirCardapio();
    System.out.print("Digite o id do item que deseja deletar: ");
    Long id = Long.parseLong(sc.nextLine());
    itemCardapioService.delete(id);

    imprimirMensagemDeSucesso();
  }

}

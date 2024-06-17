// package com.advanced.comidinhasveganas.runners;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.core.annotation.Order;
// import org.springframework.stereotype.Component;

// import com.advanced.comidinhasveganas.entities.*;
// import com.advanced.comidinhasveganas.exceptions.ResourceNotFoundException;
// import com.advanced.comidinhasveganas.services.*;

// @Component
// @Order(12)
// public class InitializeConfigRunner implements CommandLineRunner {

// @Autowired
// private ClienteService clienteService;

// @Autowired
// private MesaService mesaService;

// @Autowired
// private RestauranteService restauranteService;

// @Autowired
// private RequisicaoService requisicaoService;

// @Autowired
// private CardapioService cardapioService;

// @Autowired
// private ItemCardapioService itemCardapioService;

// @Autowired
// private PedidoService pedidoService;

// @Autowired
// private ItemPedidoService itemPedidoService;

// @Override
// public void run(String... args) throws Exception {
// Long restauranteId = 1L;

// Restaurante restaurante = carregarRestaurante(restauranteId);

// exibirDadosRestaurante(restaurante);

// Cliente cliente = new Cliente("Maria", "10", restaurante);
// restaurante.addCliente(cliente);

// Requisicao r = new Requisicao(cliente, 4, restaurante);
// restaurante.addRequisicao(r);

// restaurante.atualizarRequisicoes();

// // ItemPedido itemPedido = new
// // ItemPedido(restaurante.getCardapios().get(0).getItens().get(0), 2,
// // r.getPedido());
// // r.getPedido().addItem(itemPedido);
// // r.getPedido().addItem(new
// // ItemPedido(restaurante.getCardapios().get(0).getItens().get(1), 1,
// // r.getPedido()));

// r.getPedido().addItem(new
// ItemPedido(restaurante.getCardapios().get(1).getItens().get(0), 1,
// r.getPedido()));
// r.getPedido().addItem(new
// ItemPedido(restaurante.getCardapios().get(1).getItens().get(2), 1,
// r.getPedido()));
// r.getPedido().addItem(new
// ItemPedido(restaurante.getCardapios().get(1).getItens().get(3), 1,
// r.getPedido()));
// r.getPedido().addItem(new
// ItemPedido(restaurante.getCardapios().get(1).getItens().get(4), 1,
// r.getPedido()));

// System.out.println(r.getPedido());
// System.out.println(r);

// r.finalizarRequisicao();

// atualizarEPersistirEntidades(restaurante);
// }

// private Restaurante carregarRestaurante(Long restauranteId) {
// Restaurante restaurante = restauranteService.findById(restauranteId)
// .orElseThrow(() -> new ResourceNotFoundException("Restaurante não
// encontrado"));

// carregarEntidadesAssociadas(restaurante);
// return restaurante;
// }

// private void carregarEntidadesAssociadas(Restaurante restaurante) {
// List<Mesa> mesas = mesaService.findByRestauranteId(restaurante.getId());
// restaurante.setMesas(mesas);

// List<Cliente> clientes =
// clienteService.findByRestauranteId(restaurante.getId());
// restaurante.setClientes(clientes);

// List<Requisicao> requisicoes =
// requisicaoService.findByRestauranteId(restaurante.getId());
// restaurante.setRequisicoes(requisicoes);

// List<ItemCardapio> itensCardapio =
// itemCardapioService.findByCardapioRestauranteId(restaurante.getId());
// List<Cardapio> cardapios =
// cardapioService.findByRestauranteId(restaurante.getId());
// restaurante.setCardapios(cardapios);

// List<Pedido> pedidos =
// pedidoService.findByRequisicaoRestauranteId(restaurante.getId());
// List<ItemPedido> itensPedido =
// itemPedidoService.findByPedidoRequisicaoRestauranteId(restaurante.getId());

// associarEntidades(requisicoes, pedidos, cardapios, itensCardapio,
// itensPedido);
// }

// private void associarEntidades(List<Requisicao> requisicoes, List<Pedido>
// pedidos,
// List<Cardapio> cardapios, List<ItemCardapio> itensCardapio,
// List<ItemPedido> itensPedido) {
// requisicoes.forEach(r -> {
// Pedido pedido = pedidos.stream()
// .filter(p -> p.getRequisicao().getId().equals(r.getId()))
// .findFirst()
// .orElse(null);
// r.setPedido(pedido);
// });

// cardapios.forEach(c -> {
// List<ItemCardapio> itens = itensCardapio.stream()
// .filter(i -> i.getCardapio().getId().equals(c.getId()))
// .collect(Collectors.toList());
// c.setItens(itens);
// });

// pedidos.forEach(p -> {
// List<ItemPedido> itens = itensPedido.stream()
// .filter(i -> i.getPedido().getId().equals(p.getId()))
// .collect(Collectors.toList());
// p.setItens(itens);
// });
// }

// private void exibirDadosRestaurante(Restaurante restaurante) {
// System.out.println("-------------------");
// System.out.println(restaurante);
// restaurante.getMesas().forEach(System.out::println);
// System.out.println("-------------------");
// restaurante.getClientes().forEach(System.out::println);
// System.out.println("-------------------");
// restaurante.getRequisicoes().forEach(System.out::println);
// System.out.println("-------------------");
// restaurante.getCardapios().forEach(cardapio -> {
// System.out.println(cardapio.getNome());
// cardapio.getItens().forEach(System.out::println);
// System.out.println("-------------------");
// });
// }

// private Requisicao criarEPersistirRequisicao(Restaurante restaurante, String
// nomeCliente, String telefoneCliente) {
// Cliente cliente = restaurante.getClienteByTelefone(telefoneCliente);
// if (cliente == null) {
// cliente = new Cliente(nomeCliente, telefoneCliente, restaurante);
// clienteService.insert(cliente);
// restaurante.addCliente(cliente);
// }

// Requisicao req = new Requisicao(cliente, 4, restaurante);
// requisicaoService.insert(req);
// restaurante.addRequisicao(req);

// return req;
// }

// private Pedido criarEPersistirPedido(Restaurante restaurante, Requisicao
// requisicao) {
// Pedido pedido = new Pedido(requisicao);
// pedido = pedidoService.insert(pedido);
// requisicao.setPedido(pedido);
// requisicaoService.update(requisicao.getId(), requisicao);

// return pedido;
// }

// private void atualizarEPersistirEntidades(Restaurante restaurante) {
// restaurante.getClientes().forEach(c ->
// Optional.ofNullable(c.getId()).ifPresentOrElse(
// id -> clienteService.update(id, c),
// () -> clienteService.insert(c)));

// restaurante.getMesas().forEach(m ->
// Optional.ofNullable(m.getId()).ifPresentOrElse(
// id -> mesaService.update(id, m),
// () -> mesaService.insert(m)));

// restaurante.getCardapios().forEach(c ->
// Optional.ofNullable(c.getId()).ifPresentOrElse(
// id -> cardapioService.update(id, c),
// () -> cardapioService.insert(c)));

// restaurante.getRequisicoes().forEach(r -> {
// Pedido p = r.getPedido();
// if (p != null) {
// Optional.ofNullable(p.getId()).ifPresentOrElse(
// id -> pedidoService.update(id, p),
// () -> pedidoService.insert(p));
// }
// });

// restaurante.getRequisicoes().forEach(r -> {
// Pedido p = r.getPedido();
// if (p != null) {
// p.getItens().forEach(i -> Optional.ofNullable(i.getId()).ifPresentOrElse(
// id -> itemPedidoService.update(id, i),
// () -> itemPedidoService.insert(i)));
// }
// });

// restaurante.getCardapios().forEach(c -> c.getItens().forEach(i ->
// Optional.ofNullable(i.getId()).ifPresentOrElse(
// id -> itemCardapioService.update(id, i),
// () -> itemCardapioService.insert(i))));

// restaurante.getRequisicoes().forEach(r ->
// Optional.ofNullable(r.getId()).ifPresentOrElse(
// id -> requisicaoService.update(id, r),
// () -> requisicaoService.insert(r)));

// restauranteService.update(restaurante.getId(), restaurante);
// }
// }

// package com.advanced.comidinhasveganas.runners;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.core.annotation.Order;
// import org.springframework.stereotype.Component;

// import com.advanced.comidinhasveganas.entities.Cliente;
// import com.advanced.comidinhasveganas.entities.ItemCardapio;
// import com.advanced.comidinhasveganas.entities.Mesa;
// import com.advanced.comidinhasveganas.entities.Restaurante;
// import com.advanced.comidinhasveganas.entities.enums.TipoItem;
// import com.advanced.comidinhasveganas.services.ClienteService;
// import com.advanced.comidinhasveganas.services.ItemCardapioService;
// import com.advanced.comidinhasveganas.services.MesaService;
// import com.advanced.comidinhasveganas.services.RestauranteService;

// @Component
// @Order(1) // Definindo a ordem de execução
// public class InitializeComidinhasVeganasRunner implements CommandLineRunner {

// private static final Logger logger =
// LoggerFactory.getLogger(InitializeComidinhasVeganasRunner.class);

// @Autowired
// private RestauranteService restauranteService;

// @Autowired
// private ClienteService clienteService;

// @Autowired
// private MesaService mesaService;

// @Autowired
// private ItemCardapioService itemCardapioService;

// @Override
// public void run(String... args) throws Exception {
// logger.info("Iniciando o restaurante Comidinhas Veganas");
// inicializarRestaurante();
// }

// private void inicializarRestaurante() {

// Restaurante restaurante = new Restaurante("Comidinhas Veganas", "Rua das
// Flores, 123");

// Cliente[] clientes = {
// new Cliente("Victor", "1"),
// new Cliente("Marcos Rocha", "2"),
// new Cliente("Leonardo Silva", "3"),
// new Cliente("Réver", "4"),
// new Cliente("Junior César", "6"),
// new Cliente("Pierre", "5"),
// new Cliente("Leandro Donizete", "8"),
// new Cliente("Ronaldinho", "10"),
// new Cliente("Diego Tardelli", "9"),
// new Cliente("Bernard", "11"),
// new Cliente("Jô", "7")
// };

// for (Cliente cliente : clientes) {
// restaurante.addCliente(cliente);
// clienteService.insert(cliente);
// }

// Mesa[] mesas = {
// new Mesa(4),
// new Mesa(4),
// new Mesa(4),
// new Mesa(4),
// new Mesa(6),
// new Mesa(6),
// new Mesa(6),
// new Mesa(6),
// new Mesa(8),
// new Mesa(8)
// };

// for (Mesa mesa : mesas) {
// restaurante.addMesa(mesa);
// mesaService.insert(mesa);
// }

// ItemCardapio[] itens = {
// new ItemCardapio("Moqueca de Palmito", 32.0, TipoItem.COMIDA),
// new ItemCardapio("Falafel Assado", 20.0, TipoItem.COMIDA),
// new ItemCardapio("Salada Primavera com Macarrão Konjac", 25.0,
// TipoItem.COMIDA),
// new ItemCardapio("Escondidinho de Inhame", 18.0, TipoItem.COMIDA),
// new ItemCardapio("Strogonoff de Cogumelos", 35.0, TipoItem.COMIDA),
// new ItemCardapio("Caçarola de legumes", 22.0, TipoItem.COMIDA),
// new ItemCardapio("Água", 3.0, TipoItem.BEBIDA),
// new ItemCardapio("Copo de Suco", 7.0, TipoItem.BEBIDA),
// new ItemCardapio("Refrigerante Orgânico", 7.0, TipoItem.BEBIDA),
// new ItemCardapio("Cerveja Vegana", 9.0, TipoItem.BEBIDA),
// new ItemCardapio("Taça de Vinho Vegano", 18.0, TipoItem.BEBIDA)
// };

// for (ItemCardapio item : itens) {
// restaurante.addItemCardapio(item);
// itemCardapioService.insert(item);
// }

// restauranteService.insert(restaurante);

// logger.info("Restaurante Comidinhas Veganas inicializado com sucesso");

// };
// }
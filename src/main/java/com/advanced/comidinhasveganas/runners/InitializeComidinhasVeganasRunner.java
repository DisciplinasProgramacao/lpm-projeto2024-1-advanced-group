package com.advanced.comidinhasveganas.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.advanced.comidinhasveganas.entities.Cardapio;
import com.advanced.comidinhasveganas.entities.Cliente;
import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.entities.MenuFechado;
import com.advanced.comidinhasveganas.entities.Mesa;
import com.advanced.comidinhasveganas.entities.Restaurante;
import com.advanced.comidinhasveganas.entities.enums.TipoItem;
import com.advanced.comidinhasveganas.services.CardapioService;
import com.advanced.comidinhasveganas.services.ClienteService;
import com.advanced.comidinhasveganas.services.ItemCardapioService;
import com.advanced.comidinhasveganas.services.MesaService;
import com.advanced.comidinhasveganas.services.RestauranteService;

@Component
@Order(1) // Definindo a ordem de execução
public class InitializeComidinhasVeganasRunner implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(InitializeComidinhasVeganasRunner.class);

  @Autowired
  private RestauranteService restauranteService;

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private MesaService mesaService;

  @Autowired
  private CardapioService cardapioService;

  @Autowired
  private ItemCardapioService itemCardapioService;

  @Override
  public void run(String... args) throws Exception {
    logger.info("Iniciando o restaurante Comidinhas Veganas");
    inicializarRestaurante();
  }

  private void inicializarRestaurante() {

    Restaurante restaurante = new Restaurante("Comidinhas Veganas", "Rua das Flores, 123");
    Restaurante restaurante2 = new Restaurante("Comidinhas Veganas 2", "Rua das Flores, 123");

    Cliente[] clientes = {
        new Cliente("Victor", "1", restaurante),
        new Cliente("Marcos Rocha", "2", restaurante),
        new Cliente("Leonardo Silva", "3", restaurante),
        new Cliente("Réver", "4", restaurante),
        new Cliente("Junior César", "6", restaurante),
        new Cliente("Pierre", "5", restaurante),
        new Cliente("Leandro Donizete", "8", restaurante),
        new Cliente("Ronaldinho", "10", restaurante),
        new Cliente("Diego Tardelli", "9", restaurante),
        new Cliente("Bernard", "11", restaurante),
        new Cliente("Jô", "7", restaurante)
    };

    Mesa[] mesas = {
        new Mesa(4, restaurante),
        new Mesa(6, restaurante),
        new Mesa(8, restaurante),
        new Mesa(8, restaurante2)
    };

    Cardapio cardapio = new Cardapio("Principal", restaurante);
    MenuFechado menuFechado = new MenuFechado("Menu Fechado", restaurante, 32.0);

    ItemCardapio[] itens = {
        new ItemCardapio("Moqueca de Palmito", 32.0, TipoItem.COMIDA, cardapio),
        new ItemCardapio("Falafel Assado", 20.0, TipoItem.COMIDA, cardapio),
        new ItemCardapio("Salada Primavera com Macarrão Konjac", 25.0, TipoItem.COMIDA, cardapio),
        new ItemCardapio("Escondidinho de Inhame", 18.0, TipoItem.COMIDA, cardapio),
        new ItemCardapio("Strogonoff de Cogumelos", 35.0, TipoItem.COMIDA, cardapio),
        new ItemCardapio("Caçarola de legumes", 22.0, TipoItem.COMIDA, cardapio),
        new ItemCardapio("Água", 3.0, TipoItem.BEBIDA, cardapio),
        new ItemCardapio("Copo de Suco", 7.0, TipoItem.BEBIDA, cardapio),
        new ItemCardapio("Refrigerante Orgânico", 7.0, TipoItem.BEBIDA, cardapio),
        new ItemCardapio("Cerveja Vegana", 9.0, TipoItem.BEBIDA, cardapio),
        new ItemCardapio("Taça de Vinho Vegano", 18.0, TipoItem.BEBIDA, cardapio),
        new ItemCardapio("Falafel Assado", 0.0, TipoItem.COMIDA, menuFechado),
        new ItemCardapio("Caçarola de legumes", 0.0, TipoItem.COMIDA, menuFechado),
        new ItemCardapio("Copo de Suco", 0.0, TipoItem.BEBIDA, menuFechado),
        new ItemCardapio("Refrigerante Orgânico", 0.0, TipoItem.BEBIDA, menuFechado),
        new ItemCardapio("Cerveja Vegana", 0.0, TipoItem.BEBIDA, menuFechado)
    };

    restaurante.addCardapio(cardapio);
    restaurante.addCardapio(menuFechado);

    restauranteService.insert(restaurante);
    restauranteService.insert(restaurante2);

    for (Cliente cliente : clientes) {
      clienteService.insert(cliente);
    }

    for (Mesa mesa : mesas) {
      mesaService.insert(mesa);
    }

    cardapioService.insert(cardapio);
    cardapioService.insert(menuFechado);

    for (ItemCardapio item : itens) {
      itemCardapioService.insert(item);
    }

    logger.info("Restaurante Comidinhas Veganas inicializado com sucesso");

  };
}
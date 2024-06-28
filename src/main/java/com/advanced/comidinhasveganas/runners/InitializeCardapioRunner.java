package com.advanced.comidinhasveganas.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.entities.enums.TipoItem;
import com.advanced.comidinhasveganas.services.ItemCardapioService;

/**
 * Classe responsável pela inicialização dos dados do cardápio.
 */
@Component
@Order(3)
public class InitializeCardapioRunner implements CommandLineRunner {

  @Autowired
  private ItemCardapioService itemCardapioService;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    inicializarCardapio();
  }

  /**
   * Inicializa o cardápio.
   */
  private void inicializarCardapio() {
    ItemCardapio[] itens = {
        new ItemCardapio("Moqueca de Palmito", 32.0, TipoItem.COMIDA),
        new ItemCardapio("Falafel Assado", 20.0, TipoItem.COMIDA),
        new ItemCardapio("Salada Primavera com Macarrão Konjac", 25.0,
            TipoItem.COMIDA),
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
      itemCardapioService.insert(item);
    }
  }
}

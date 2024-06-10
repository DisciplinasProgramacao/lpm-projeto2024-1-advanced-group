package com.advanced.comidinhasveganas.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.services.ItemCardapioService;

@Component
@Order(2)
public class InitializeCardapioRunner implements CommandLineRunner {

  @Autowired
  private ItemCardapioService itemCardapioService;

  @Override
  public void run(String... args) throws Exception {
    if (itemCardapioService.findAll().isEmpty()) {
      inicializarCardapio();
    }
  }

  private void inicializarCardapio() {
    ItemCardapio[] itens = {
        new ItemCardapio(null, "Moqueca de Palmito", 32.0),
        new ItemCardapio(null, "Falafel Assado", 20.0),
        new ItemCardapio(null, "Salada Primavera com Macarrão Konjac", 25.0),
        new ItemCardapio(null, "Escondidinho de Inhame", 18.0),
        new ItemCardapio(null, "Strogonoff de Cogumelos", 35.0),
        new ItemCardapio(null, "Caçarola de legumes", 22.0),
        new ItemCardapio(null, "Água", 3.0),
        new ItemCardapio(null, "Copo de Suco", 7.0),
        new ItemCardapio(null, "Refrigerante Orgânico", 7.0),
        new ItemCardapio(null, "Cerveja Vegana", 9.0),
        new ItemCardapio(null, "Taça de Vinho Vegano", 18.0)
    };
    for (ItemCardapio item : itens) {
      itemCardapioService.insert(item);
    }
  }
}

// package com.advanced.comidinhasveganas.runners;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.core.annotation.Order;
// import org.springframework.stereotype.Component;

// import com.advanced.comidinhasveganas.entities.ItemCardapio;
// import com.advanced.comidinhasveganas.services.ItemCardapioService;

// @Component
// @Order(2)
// public class InitializeCardapioRunner implements CommandLineRunner {

// @Autowired
// private ItemCardapioService itemCardapioService;

// @Override
// public void run(String... args) throws Exception {
// itemCardapioService.deleteAll();
// inicializarCardapio();
// }

// private void inicializarCardapio() {
// ItemCardapio[] itens = {
// new ItemCardapio(null, "Moqueca de Palmito", 32.0, false, false),
// new ItemCardapio(null, "Falafel Assado", 20.0, true, false),
// new ItemCardapio(null, "Salada Primavera com Macarrão Konjac", 25.0, false,
// false),
// new ItemCardapio(null, "Escondidinho de Inhame", 18.0, false, false),
// new ItemCardapio(null, "Strogonoff de Cogumelos", 35.0, false, false),
// new ItemCardapio(null, "Caçarola de legumes", 22.0, true, false),
// new ItemCardapio(null, "Água", 3.0, false, false),
// new ItemCardapio(null, "Copo de Suco", 7.0, false, true),
// new ItemCardapio(null, "Refrigerante Orgânico", 7.0, false, true),
// new ItemCardapio(null, "Cerveja Vegana", 9.0, false, true),
// new ItemCardapio(null, "Taça de Vinho Vegano", 18.0, false, false)
// };
// for (ItemCardapio item : itens) {
// itemCardapioService.insert(item);
// }
// }
// }

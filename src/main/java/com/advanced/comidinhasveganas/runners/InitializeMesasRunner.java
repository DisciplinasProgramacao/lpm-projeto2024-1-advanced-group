// package com.advanced.comidinhasveganas.runners;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.core.annotation.Order;
// import org.springframework.stereotype.Component;

// import com.advanced.comidinhasveganas.entities.Mesa;
// import com.advanced.comidinhasveganas.services.MesaService;

// @Component
// @Order(3)
// public class InitializeMesasRunner implements CommandLineRunner {

// @Autowired
// private MesaService mesaService;

// @Override
// public void run(String... args) throws Exception {
// mesaService.deleteAll();
// inicializarMesas();
// }

// private void inicializarMesas() {
// int[] lugaresPorMesa = { 4, 4, 4, 4, 6, 6, 6, 6, 8, 8 };
// for (int lugares : lugaresPorMesa) {
// Mesa mesa = new Mesa(lugares);
// mesaService.insert(mesa);
// }
// }
// }

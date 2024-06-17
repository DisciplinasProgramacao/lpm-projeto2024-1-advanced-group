// package com.advanced.comidinhasveganas.runners;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.core.annotation.Order;
// import org.springframework.stereotype.Component;

// import com.advanced.comidinhasveganas.entities.Cliente;
// import com.advanced.comidinhasveganas.services.ClienteService;

// @Component
// @Order(2) // Definindo a ordem de execução
// public class InitializeClientesRunner implements CommandLineRunner {

// private static final Logger logger =
// LoggerFactory.getLogger(InitializeClientesRunner.class);

// @Autowired
// private ClienteService clienteService;

// @Override
// public void run(String... args) throws Exception {
// logger.info("Iniciando a tabela de clientes");
// clienteService.deleteAll();
// inicializarClientes();
// }

// private void inicializarClientes() {
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
// clienteService.insert(cliente);
// }
// }
// }

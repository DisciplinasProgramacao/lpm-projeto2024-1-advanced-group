package com.advanced.comidinhasveganas.runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.advanced.comidinhasveganas.entities.Cliente;
import com.advanced.comidinhasveganas.services.ClienteService;

@Component
@Order(3) // Definindo a ordem de execução
public class InitializeClientesRunner implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(InitializeClientesRunner.class);

  @Autowired
  private ClienteService clienteService;

  @Override
  public void run(String... args) throws Exception {
    if (clienteService.findAll().isEmpty()) {
      logger.info("Inicializando clientes...");
      inicializarClientes();
    } else {
      logger.info("Clientes já estão inicializados.");
    }
  }

  private void inicializarClientes() {
    Cliente[] clientes = {
        new Cliente(null, "Victor", "1"),
        new Cliente(null, "Marcos Rocha", "2"),
        new Cliente(null, "Leonardo Silva", "3"),
        new Cliente(null, "Réver", "4"),
        new Cliente(null, "Junior César", "6"),
        new Cliente(null, "Pierre", "5"),
        new Cliente(null, "Leandro Donizete", "8"),
        new Cliente(null, "Ronaldinho", "10"),
        new Cliente(null, "Diego Tardelli", "9"),
        new Cliente(null, "Bernard", "11"),
        new Cliente(null, "Jô", "7")
    };
    for (Cliente cliente : clientes) {
      clienteService.insert(cliente);
    }
  }
}

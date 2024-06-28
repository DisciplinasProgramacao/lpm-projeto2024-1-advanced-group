package com.advanced.comidinhasveganas.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.Cliente;
import com.advanced.comidinhasveganas.services.ClienteService;

/**
 * Classe responsável pela inicialização dos dados dos clientes.
 */
@Component
@Order(2)
public class InitializeClientesRunner implements CommandLineRunner {

  @Autowired
  private ClienteService clienteService;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    inicializarClientes();
  }

  /**
   * Inicializa os clientes.
   */
  private void inicializarClientes() {
    Cliente[] clientes = {
        new Cliente("Victor", "1"),
        new Cliente("Marcos Rocha", "2"),
        new Cliente("Leonardo Silva", "3"),
        new Cliente("Réver", "4"),
        new Cliente("Junior César", "6"),
        new Cliente("Pierre", "5"),
        new Cliente("Leandro Donizete", "8"),
        new Cliente("Ronaldinho", "10"),
        new Cliente("Diego Tardelli", "9"),
        new Cliente("Bernard", "11"),
        new Cliente("Jô", "7")
    };
    for (Cliente cliente : clientes) {
      clienteService.insert(cliente);
    }
  }
}

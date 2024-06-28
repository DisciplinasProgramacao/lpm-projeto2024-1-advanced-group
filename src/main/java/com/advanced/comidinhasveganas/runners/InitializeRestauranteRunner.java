package com.advanced.comidinhasveganas.runners;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.Cliente;
import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.entities.Mesa;
import com.advanced.comidinhasveganas.entities.Restaurante;
import com.advanced.comidinhasveganas.services.ClienteService;
import com.advanced.comidinhasveganas.services.ItemCardapioService;
import com.advanced.comidinhasveganas.services.MesaService;
import com.advanced.comidinhasveganas.services.RestauranteService;

/**
 * Classe responsável pela inicialização dos dados do restaurante.
 */
@Component
@Order(99)
public class InitializeRestauranteRunner implements CommandLineRunner {

  @Autowired
  private RestauranteService restauranteService;

  @Autowired
  private MesaService mesaService;

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private ItemCardapioService itemCardapioService;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    Restaurante restaurante = inicializarRestaurante("Comidinhas Veganas", "Rua das Flores, 123");
    addMesas(restaurante);
    addClientes(restaurante);
    addItensCardapio(restaurante);
    restauranteService.insert(restaurante);
  }

  /**
   * Inicializa a entidade restaurante.
   *
   * @param nome o nome do restaurante
   * @param end  o endereço do restaurante
   * @return o restaurante inicializado
   */
  private Restaurante inicializarRestaurante(String nome, String end) {
    return new Restaurante(nome, end);
  }

  /**
   * Adiciona mesas ao restaurante.
   *
   * @param restaurante o restaurante ao qual as mesas serão adicionadas
   */
  private void addMesas(Restaurante restaurante) {
    List<Mesa> mesas = mesaService.findAll();
    mesas.stream().forEach(m -> restaurante.addMesa(m));
  }

  /**
   * Adiciona clientes ao restaurante.
   *
   * @param restaurante o restaurante ao qual os clientes serão adicionados
   */
  private void addClientes(Restaurante restaurante) {
    List<Cliente> clientes = clienteService.findAll();
    clientes.stream().forEach(c -> restaurante.addCliente(c));
  }

  /**
   * Adiciona itens do cardápio ao restaurante.
   *
   * @param restaurante o restaurante ao qual os itens do cardápio serão
   *                    adicionados
   */
  private void addItensCardapio(Restaurante restaurante) {
    List<ItemCardapio> itens = itemCardapioService.findAll();
    itens.stream().forEach(i -> restaurante.addItemCardapio(i));
  }

}

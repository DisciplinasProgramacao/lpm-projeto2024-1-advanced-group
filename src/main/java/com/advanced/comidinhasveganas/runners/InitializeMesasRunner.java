package com.advanced.comidinhasveganas.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.Mesa;
import com.advanced.comidinhasveganas.services.MesaService;

@Component
@Order(1)
public class InitializeMesasRunner implements CommandLineRunner {

  @Autowired
  private MesaService mesaService;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    inicializarMesas();
  }

  private void inicializarMesas() {
    int[] lugaresPorMesa = { 4, 4, 4, 4, 6, 6, 6, 6, 8, 8 };
    for (int lugares : lugaresPorMesa) {
      Mesa mesa = new Mesa(lugares);
      mesaService.insert(mesa);
    }
  }
}

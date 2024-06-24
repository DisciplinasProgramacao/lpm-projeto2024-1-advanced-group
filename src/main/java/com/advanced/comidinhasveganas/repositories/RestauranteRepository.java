package com.advanced.comidinhasveganas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.comidinhasveganas.entities.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

  @SuppressWarnings("null")
  @EntityGraph(attributePaths = { "mesas", "clientes", "requisicoes", "itensCardapio" })
  Optional<Restaurante> findById(Long id);

}

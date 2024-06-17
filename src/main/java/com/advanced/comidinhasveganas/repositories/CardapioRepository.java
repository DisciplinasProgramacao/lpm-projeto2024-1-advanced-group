package com.advanced.comidinhasveganas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.comidinhasveganas.entities.Cardapio;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {

  List<Cardapio> findByRestauranteId(Long id);
}

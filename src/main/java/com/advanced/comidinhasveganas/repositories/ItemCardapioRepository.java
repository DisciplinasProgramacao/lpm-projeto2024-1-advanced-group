package com.advanced.comidinhasveganas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.comidinhasveganas.entities.ItemCardapio;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Long> {

  List<ItemCardapio> findByCardapioId(Long id);

  List<ItemCardapio> findByCardapioRestauranteId(Long id);
}

package com.advanced.comidinhasveganas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.comidinhasveganas.entities.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Long> {

  List<Mesa> findByRestauranteId(Long id);

}

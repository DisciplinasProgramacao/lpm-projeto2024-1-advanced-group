package com.advanced.comidinhasveganas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.comidinhasveganas.entities.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {

  List<Requisicao> findByRestauranteId(Long id);

}

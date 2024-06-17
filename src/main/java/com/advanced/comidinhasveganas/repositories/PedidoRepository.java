package com.advanced.comidinhasveganas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.comidinhasveganas.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
  List<Pedido> findByRequisicaoRestauranteId(Long restauranteId);
}

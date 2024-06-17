package com.advanced.comidinhasveganas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.comidinhasveganas.entities.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

  List<ItemPedido> findByPedidoRequisicaoRestauranteId(Long restauranteId);

}

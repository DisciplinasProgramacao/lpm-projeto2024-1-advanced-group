package com.advanced.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}

package com.advanced.comidinhasveganas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.comidinhasveganas.entities.PedidoItemCardapio;
import com.advanced.comidinhasveganas.entities.pk.PedidoItemCardapioPK;

public interface PedidoItemCardapioRepository extends JpaRepository<PedidoItemCardapio, PedidoItemCardapioPK> {

}
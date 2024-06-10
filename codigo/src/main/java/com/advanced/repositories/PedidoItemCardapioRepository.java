package com.advanced.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.entities.PedidoItemCardapio;
import com.advanced.entities.pk.PedidoItemCardapioPK;

public interface PedidoItemCardapioRepository extends JpaRepository<PedidoItemCardapio, PedidoItemCardapioPK> {

}
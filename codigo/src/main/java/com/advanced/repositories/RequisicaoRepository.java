package com.advanced.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.entities.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {

}
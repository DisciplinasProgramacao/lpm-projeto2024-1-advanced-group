package com.advanced.comidinhasveganas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.comidinhasveganas.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  Optional<Cliente> findByTelefone(String telefone);

  List<Cliente> findByRestauranteId(Long id);
}

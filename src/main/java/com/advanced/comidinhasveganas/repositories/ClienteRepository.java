package com.advanced.comidinhasveganas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.comidinhasveganas.entities.Cliente;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  List<Cliente> findByTelefone(String telefone);
}

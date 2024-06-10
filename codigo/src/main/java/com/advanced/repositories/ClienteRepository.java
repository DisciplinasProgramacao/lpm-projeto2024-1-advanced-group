package com.advanced.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.advanced.entities.Cliente;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  List<Cliente> findByTelefone(String telefone);
}

package com.advanced.comidinhasveganas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.Cliente;
import com.advanced.comidinhasveganas.repositories.ClienteRepository;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  public List<Cliente> findAll() {
    return clienteRepository.findAll();
  }

  public Optional<Cliente> findById(Long id) {
    return clienteRepository.findById(id);
  }

  public Optional<Cliente> findByTelefone(String telefone) {
    return clienteRepository.findByTelefone(telefone);
  }

  @Transactional
  public Cliente insert(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  @Transactional
  public List<Cliente> insertAll(List<Cliente> clientes) {
    return clienteRepository.saveAll(clientes);
  }

  @Transactional
  public void deleteAll() {
    clienteRepository.deleteAll();
  }

  @Transactional
  public void deleteById(Long id) {
    clienteRepository.deleteById(id);
  }

}

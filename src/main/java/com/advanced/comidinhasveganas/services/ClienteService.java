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
  private ClienteRepository repository;

  public List<Cliente> findAll() {
    return repository.findAll();
  }

  public Optional<Cliente> findById(Long id) {
    return repository.findById(id);
  }

  public Optional<Cliente> findByTelefone(String telefone) {
    return repository.findByTelefone(telefone).stream().findFirst();
  }

  @Transactional
  public Cliente insert(Cliente cliente) {
    if (findByTelefone(cliente.getTelefone()).isPresent()) {
      throw new RuntimeException("Cliente com este telefone já existe.");
    }
    return repository.save(cliente);
  }

  @Transactional
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Transactional
  public Cliente update(Long id, Cliente cliente) {
    Cliente entity = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    updateData(entity, cliente);
    return repository.save(entity);
  }

  private void updateData(Cliente entity, Cliente cliente) {
    entity.setNome(cliente.getNome());
    entity.setTelefone(cliente.getTelefone());
  }

  @Transactional
  public void deleteAll() {
    repository.deleteAll();
  }
}

package com.advanced.comidinhasveganas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.advanced.comidinhasveganas.entities.Cliente;
import com.advanced.comidinhasveganas.exceptions.ResourceNotFoundException;
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

  public List<Cliente> findByRestauranteId(Long id) {
    return clienteRepository.findByRestauranteId(id);
  }

  public Optional<Cliente> findByTelefone(String telefone) {
    return clienteRepository.findByTelefone(telefone);
  }

  @Transactional
  public Cliente insert(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  @Transactional
  public void deleteAll() {
    clienteRepository.deleteAll();
  }

  @Transactional
  public void deleteById(Long id) {
    clienteRepository.deleteById(id);
  }

  @Transactional
  public Cliente update(Long id, Cliente cliente) {
    Cliente entity = clienteRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    updateData(entity, cliente);
    return clienteRepository.save(entity);
  }

  private void updateData(Cliente entity, Cliente cliente) {
    if (cliente.getNome() != null) {
      entity.setNome(cliente.getNome());
    }
    if (cliente.getTelefone() != null) {
      entity.setTelefone(cliente.getTelefone());
    }
    if (cliente.getRestaurante() != null) {
      entity.setRestaurante(cliente.getRestaurante());
    }
  }

}

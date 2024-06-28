package com.advanced.comidinhasveganas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.advanced.comidinhasveganas.entities.Cliente;

public class ClienteTest {

  private Cliente cliente;

  @BeforeEach
  public void setUp() {
    cliente = new Cliente("Joao Caram", "1234567890");
  }

  @Test
  public void testGetId() {
    Assertions.assertNull(cliente.getId());
  }

  @Test
  public void testGetNome() {
    Assertions.assertEquals("Joao Caram", cliente.getNome());
  }

  @Test
  public void testGetTelefone() {
    Assertions.assertEquals("1234567890", cliente.getTelefone());
  }

  @Test
  public void testSetNome() {
    cliente.setNome("Ronaldinho Gaúcho");
    Assertions.assertEquals("Ronaldinho Gaúcho", cliente.getNome());
  }

  @Test
  public void testSetTelefone() {
    cliente.setTelefone("0987654321");
    Assertions.assertEquals("0987654321", cliente.getTelefone());
  }

  @Test
  public void testToString() {
    String expected = "Cliente [id=null, nome=Joao Caram, telefone=1234567890]";
    Assertions.assertEquals(expected, cliente.toString());
  }
}
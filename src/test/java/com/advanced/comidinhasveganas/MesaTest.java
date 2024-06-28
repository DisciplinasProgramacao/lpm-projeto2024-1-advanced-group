package com.advanced.comidinhasveganas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.advanced.comidinhasveganas.entities.Mesa;

public class MesaTest {

  private Mesa mesa;

  @BeforeEach
  public void setUp() {
    mesa = new Mesa(4);
  }

  @Test
  public void testGetId() {
    Assertions.assertNull(mesa.getId());
  }

  @Test
  public void testGetLugares() {
    Assertions.assertEquals(4, mesa.getLugares());
  }

  @Test
  public void testGetIsOcupada() {
    Assertions.assertFalse(mesa.getIsOcupada());
  }

  @Test
  public void testSetLugares() {
    mesa.setLugares(6);
    Assertions.assertEquals(6, mesa.getLugares());
  }

  @Test
  public void testSetIsOcupada() {
    mesa.setIsOcupada(true);
    Assertions.assertTrue(mesa.getIsOcupada());
  }

  @Test
  public void testOcupar() {
    mesa.ocupar();
    Assertions.assertTrue(mesa.getIsOcupada());
  }

  @Test
  public void testDesocupar() {
    mesa.desocupar();
    Assertions.assertFalse(mesa.getIsOcupada());
  }

  @Test
  public void testCabe() {
    Assertions.assertTrue(mesa.cabe(3));
    Assertions.assertFalse(mesa.cabe(5));
  }

  @Test
  public void testToString() {
    String expected = "Mesa [id=null, lugares=4, isOcupada=false]";
    Assertions.assertEquals(expected, mesa.toString());
  }
}
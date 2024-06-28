package com.advanced.comidinhasveganas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.entities.enums.TipoItem;

public class ItemCardapioTest {

  private ItemCardapio itemCardapio;

  @BeforeEach
  public void setUp() {
    itemCardapio = new ItemCardapio("Vegan Burger", 10.99, TipoItem.COMIDA);
  }

  @Test
  public void testGetId() {
    Assertions.assertNull(itemCardapio.getId());
  }

  @Test
  public void testGetNome() {
    Assertions.assertEquals("Vegan Burger", itemCardapio.getNome());
  }

  @Test
  public void testGetPreco() {
    Assertions.assertEquals(10.99, itemCardapio.getPreco());
  }

  @Test
  public void testGetTipo() {
    Assertions.assertEquals(TipoItem.COMIDA, itemCardapio.getTipo());
  }

  @Test
  public void testSetNome() {
    itemCardapio.setNome("Vegan Pizza");
    Assertions.assertEquals("Vegan Pizza", itemCardapio.getNome());
  }

  @Test
  public void testSetPreco() {
    itemCardapio.setPreco(15.99);
    Assertions.assertEquals(15.99, itemCardapio.getPreco());
  }

  @Test
  public void testSetTipo() {
    itemCardapio.setTipo(TipoItem.BEBIDA);
    Assertions.assertEquals(TipoItem.BEBIDA, itemCardapio.getTipo());
  }

  @Test
  public void testToString() {
    String expected = "ItemCardapio [id=null, nome=Vegan Burger, preco=10.99, tipo=COMIDA]";
    Assertions.assertEquals(expected, itemCardapio.toString());
  }
}
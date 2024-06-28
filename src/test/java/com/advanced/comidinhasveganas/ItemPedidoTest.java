package com.advanced.comidinhasveganas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.entities.ItemPedido;
import com.advanced.comidinhasveganas.entities.enums.TipoItem;

public class ItemPedidoTest {

  private ItemCardapio itemCardapio;
  private ItemPedido itemPedido;

  @BeforeEach
  public void setUp() {
    itemCardapio = new ItemCardapio("Vegan Burger", 10.99, TipoItem.COMIDA);
    itemPedido = new ItemPedido(itemCardapio, 2);
  }

  @Test
  public void testGetId() {
    Assertions.assertNull(itemPedido.getId());
  }

  @Test
  public void testGetItemCardapio() {
    Assertions.assertEquals(itemCardapio, itemPedido.getItemCardapio());
  }

  @Test
  public void testSetItemCardapio() {
    ItemCardapio newItemCardapio = new ItemCardapio("Vegan Pizza", 15.99, TipoItem.COMIDA);
    itemPedido.setItemCardapio(newItemCardapio);
    Assertions.assertEquals(newItemCardapio, itemPedido.getItemCardapio());
  }

  @Test
  public void testGetQuantidade() {
    Assertions.assertEquals(2, itemPedido.getQuantidade());
  }

  @Test
  public void testSetQuantidade() {
    itemPedido.setQuantidade(3);
    Assertions.assertEquals(3, itemPedido.getQuantidade());
  }

  @Test
  public void testGetSubTotal() {
    Assertions.assertEquals(21.98, itemPedido.getSubTotal());
  }

  @Test
  public void testToString() {
    String expected = "ItemPedido [id=null, itemCardapio=ItemCardapio [id=null, nome=Vegan Burger, preco=10.99, tipo=COMIDA], quantidade=2]";
    Assertions.assertEquals(expected, itemPedido.toString());
  }
}
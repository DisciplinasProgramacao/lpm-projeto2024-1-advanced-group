package com.advanced.comidinhasveganas;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.advanced.comidinhasveganas.entities.ItemCardapio;
import com.advanced.comidinhasveganas.entities.ItemPedido;
import com.advanced.comidinhasveganas.entities.Pedido;
import com.advanced.comidinhasveganas.entities.enums.TipoItem;

public class PedidoTest {

  private Pedido pedido;

  @BeforeEach
  public void setUp() {
    pedido = new Pedido("normal");
  }

  @Test
  public void testGetId() {
    Assertions.assertNull(pedido.getId());
  }

  @Test
  public void testGetItens() {
    List<ItemPedido> itens = new ArrayList<>();
    ItemPedido item1 = new ItemPedido(new ItemCardapio("Hamburguer Vegano", 15.0, TipoItem.COMIDA), 2);
    ItemPedido item2 = new ItemPedido(new ItemCardapio("Batata Frita", 10.0, TipoItem.COMIDA), 1);
    itens.add(item1);
    itens.add(item2);
    pedido.addItens(itens);
    Assertions.assertEquals(itens, pedido.getItens());
  }

  @Test
  public void testGetTipoPedido() {
    Assertions.assertEquals("normal", pedido.getTipoPedido());
  }

  @Test
  public void testSetTipoPedido() {
    pedido.setTipoPedido("fechado");
    Assertions.assertEquals("fechado", pedido.getTipoPedido());
  }

  @Test
  public void testGetPrecoTotal() {
    List<ItemPedido> itens = new ArrayList<>();
    ItemPedido item1 = new ItemPedido(new ItemCardapio("Hamburguer Vegano", 15.0, TipoItem.COMIDA), 2);
    ItemPedido item2 = new ItemPedido(new ItemCardapio("Batata Frita", 10.0, TipoItem.COMIDA), 1);
    itens.add(item1);
    itens.add(item2);
    pedido.addItens(itens);
    pedido.setPrecoTotal();
    Assertions.assertEquals(40.0, pedido.getPrecoTotal());
  }

  @Test
  public void testAddItem() {
    ItemPedido item = new ItemPedido(new ItemCardapio("Hamburguer Vegano", 15.0, TipoItem.COMIDA), 2);
    pedido.addItem(item);
    Assertions.assertTrue(pedido.getItens().contains(item));
  }

  @Test
  public void testAddItens() {
    List<ItemPedido> itens = new ArrayList<>();
    ItemPedido item1 = new ItemPedido(new ItemCardapio("Hamburguer Vegano", 15.0, TipoItem.COMIDA), 2);
    ItemPedido item2 = new ItemPedido(new ItemCardapio("Batata Frita", 10.0, TipoItem.COMIDA), 1);
    itens.add(item1);
    itens.add(item2);
    pedido.addItens(itens);
    Assertions.assertEquals(itens.size(), pedido.getItens().size());
    Assertions.assertTrue(pedido.getItens().containsAll(itens));
  }

  @Test
  public void testSetPrecoTotalNormal() {
    List<ItemPedido> itens = new ArrayList<>();
    ItemPedido item1 = new ItemPedido(new ItemCardapio("Hamburguer Vegano", 15.0, TipoItem.COMIDA), 2);
    ItemPedido item2 = new ItemPedido(new ItemCardapio("Batata Frita", 10.0, TipoItem.COMIDA), 1);
    itens.add(item1);
    itens.add(item2);
    pedido.addItens(itens);
    pedido.setPrecoTotal();
    Assertions.assertEquals(40.0, pedido.getPrecoTotal());
  }

  @Test
  public void testSetPrecoTotalFechado() {
    pedido.setTipoPedido("fechado");
    pedido.setPrecoTotal();
    Assertions.assertEquals(32.0, pedido.getPrecoTotal());
  }

  @Test
  public void testSetPrecoTotalUnknownType() {
    pedido.setTipoPedido("unknown");
    Assertions.assertThrows(IllegalArgumentException.class, () -> pedido.setPrecoTotal());
  }

  @Test
  public void testToString() {
    List<ItemPedido> itens = new ArrayList<>();
    ItemPedido item1 = new ItemPedido(new ItemCardapio("Hamburguer Vegano", 15.0, TipoItem.COMIDA), 2);
    ItemPedido item2 = new ItemPedido(new ItemCardapio("Batata Frita", 10.0, TipoItem.COMIDA), 1);
    itens.add(item1);
    itens.add(item2);
    pedido.addItens(itens);
    pedido.setPrecoTotal();
    String expected = "ItemPedido [id=null, itemCardapio=ItemCardapio [id=null, nome=Hamburguer Vegano, preco=15.0, tipo=COMIDA], quantidade=2]\n"
        +
        "ItemPedido [id=null, itemCardapio=ItemCardapio [id=null, nome=Batata Frita, preco=10.0, tipo=COMIDA], quantidade=1]\n"
        +
        "Preço total: R$ 40.0";
    Assertions.assertEquals(expected, pedido.toString());
  }
}
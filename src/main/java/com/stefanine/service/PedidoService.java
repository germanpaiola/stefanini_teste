package com.stefanine.service;

import com.stefanine.model.Item;
import com.stefanine.model.Pedido;

public interface PedidoService {
    void adicionaItemAoPedido(Pedido pedido, Item item);
    void retiraItemDoPedido(Pedido pedido, Item item);
    void fechaPedido(Pedido pedido);
}

package com.stefanine.model.repository;

import com.stefanine.model.Item;
import com.stefanine.model.Pedido;

import java.util.List;

public interface PedidoRepository {
    Pedido findbyId(int id);
    List<Pedido> findByNomeCliente(String nomeCliente);
    Pedido save(Pedido pedido);
    void delete(Pedido pedido);
}

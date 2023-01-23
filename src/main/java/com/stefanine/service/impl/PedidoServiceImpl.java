package com.stefanine.service.impl;

import com.stefanine.DatabaseConnector;
import com.stefanine.model.Item;
import com.stefanine.model.Pedido;
import com.stefanine.model.repository.ItemRepository;
import com.stefanine.model.repository.PedidoRepository;
import com.stefanine.model.repository.impl.ItemRepositoryImpl;
import com.stefanine.model.repository.impl.PedidoRepositoryImpl;
import com.stefanine.service.Calculadora;
import com.stefanine.service.Impressora;
import com.stefanine.service.PedidoService;


import javax.persistence.EntityManager;

public class PedidoServiceImpl implements PedidoService {

    ItemRepository itemRepository;
    PedidoRepository pedidoRepository;
    EntityManager entityManager;

    public PedidoServiceImpl(){
        this.entityManager = DatabaseConnector.getEntityManager();
        itemRepository = new ItemRepositoryImpl(entityManager);
        pedidoRepository = new PedidoRepositoryImpl(entityManager);
    }

    public void adicionaItemAoPedido(Pedido pedido, Item item){
        pedido.getItensDopedido().add(item);
        itemRepository.save(item);
        pedidoRepository.save(pedido);
    }

    public void retiraItemDoPedido(Pedido pedido, Item item){
        pedido.getItensDopedido().remove(item);
        itemRepository.save(item);
        pedidoRepository.save(pedido);
    }

    public void fechaPedido(Pedido pedido){
        Impressora impressora = new Impressora();
        Calculadora calculadora = new Calculadora();
        calculadora.calculaImpostos(pedido);
        pedidoRepository.save(pedido);
        impressora.imprime(pedido);
    }
}

package com.stefanine.service;

import com.stefanine.DatabaseConnector;
import com.stefanine.model.Item;
import com.stefanine.model.ItemBuilder;
import com.stefanine.model.Pedido;
import com.stefanine.model.PedidoBuilder;
import com.stefanine.service.impl.ItemServiceImpl;
import com.stefanine.service.impl.PedidoServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Date;

public class PedidoServiceTest {

    PedidoService pedidoService;
    ItemService itemService;
    EntityManager entityManager;

    @Before
    public void init(){
        this.entityManager = DatabaseConnector.getEntityManager();
        pedidoService = new PedidoServiceImpl();
        itemService = new ItemServiceImpl();
    }

    @After
    public void clearDatabase(){

    }

    @Test
    public void compraItensPessoaFisica(){
        Date dataCompra = new Date();
        Pedido pedido = new PedidoBuilder()
                .nomeCliente("cliente_um")
                .cnpjOuCpf("123456")
                .tipo("física")
                .dataCompra(dataCompra)
                .build();

        Item item1 = new ItemBuilder()
                .descricao("Descricao_um")
                .quantidade(1)
                .valor(2.00)
                .build();

        Item item2 = new ItemBuilder()
                .descricao("Descricao_dois")
                .quantidade(3)
                .valor(10.00)
                .build();

        pedidoService.adicionaItemAoPedido(pedido, item1);
        pedidoService.adicionaItemAoPedido(pedido, item2);
        pedidoService.fechaPedido(pedido);
    }

    @Test
    public void compraItensPessoaJuridica(){
        Date dataCompra = new Date();
        Pedido pedido = new PedidoBuilder()
                .nomeCliente("cliente_um")
                .cnpjOuCpf("123456")
                .tipo("jurídica")
                .dataCompra(dataCompra)
                .build();

        Item item1 = new ItemBuilder()
                .descricao("Descricao_um")
                .quantidade(2)
                .valor(50.12)
                .build();

        Item item2 = new ItemBuilder()
                .descricao("Descricao_dois")
                .quantidade(3)
                .valor(10.00)
                .build();

        pedidoService.adicionaItemAoPedido(pedido, item1);
        pedidoService.adicionaItemAoPedido(pedido, item2);
        pedidoService.fechaPedido(pedido);
    }
}

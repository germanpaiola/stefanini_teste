package com.stefanine.model.repository;

import com.stefanine.DatabaseConnector;
import com.stefanine.model.Item;
import com.stefanine.model.Pedido;
import com.stefanine.model.PedidoBuilder;
import com.stefanine.model.repository.impl.ItemRepositoryImpl;
import com.stefanine.model.repository.impl.PedidoRepositoryImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Date;

public class PedidoRepositoryTest {

    ItemRepository itemRepository;
    PedidoRepository pedidoRepository;
    EntityManager entityManager;

    @Before
    public void init(){
        this.entityManager = DatabaseConnector.getEntityManager();
        itemRepository = new ItemRepositoryImpl(entityManager);
        pedidoRepository = new PedidoRepositoryImpl(entityManager);
    }

    @After
    public void clearDatabase(){

    }

    @Test
    public void deveSalvarUmPedidoSemItem(){
        Date dataCompra = new Date();
        Pedido pedido = new PedidoBuilder()
                .nomeCliente("cliente_um")
                .cnpjOuCpf("123456")
                .tipo("fisico")
                .dataCompra(dataCompra)
                .build();

        pedido = pedidoRepository.save(pedido);
        Pedido recuperado = pedidoRepository.findbyId(pedido.getId());
        Assert.assertEquals("cliente_um", recuperado.getNomeCliente());
        Assert.assertEquals("123456", recuperado.getCnpjOuCpf());
        Assert.assertEquals("fisico", recuperado.getTipo());
        Assert.assertEquals(dataCompra, recuperado.getDataCompra());
    }

    @Test
    public void deveSalvarUmPedidoComItem(){
        Date dataCompra = new Date();
        Pedido pedido = new PedidoBuilder()
                .nomeCliente("cliente_um")
                .cnpjOuCpf("123456")
                .tipo("fisico")
                .dataCompra(dataCompra)
                .build();
        Item item = itemRepository.findById(1);
        pedido.getItensDopedido().add(item);
        pedido = pedidoRepository.save(pedido);
        Pedido recuperado = pedidoRepository.findbyId(pedido.getId());
        Assert.assertEquals("cliente_um", recuperado.getNomeCliente());
        Assert.assertEquals("123456", recuperado.getCnpjOuCpf());
        Assert.assertEquals("fisico", recuperado.getTipo());
        Assert.assertEquals(dataCompra, recuperado.getDataCompra());
    }
}

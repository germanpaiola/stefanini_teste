package com.stefanine.model.repository;


import com.stefanine.DatabaseConnector;
import com.stefanine.model.Item;
import com.stefanine.model.ItemBuilder;
import com.stefanine.model.repository.impl.ItemRepositoryImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

public class ItemRepositoryTest {

    ItemRepository itemRepository;
    EntityManager entityManager;

    @Before
    public void init(){
        this.entityManager = DatabaseConnector.getEntityManager();
        itemRepository = new ItemRepositoryImpl(entityManager);
    }

    @After
    public void clearDatabase(){

    }

    @Test
    public void deveSalvarUmItem_happyDay() {
        Item item = new ItemBuilder()
                .descricao("Descricao_um")
                .quantidade(1)
                .valor(2.00)
                .build();
        item = itemRepository.save(item);
        Item recuperado = itemRepository.findById(item.getId());
        Assert.assertEquals("Descricao_um", recuperado.getDescricao());
        Assert.assertEquals(1, recuperado.getQuantidade());
        Assert.assertTrue(2.00 == recuperado.getValor());
        entityManager.close();
    }
}

package com.stefanine.service.impl;

import com.stefanine.DatabaseConnector;
import com.stefanine.model.repository.ItemRepository;
import com.stefanine.model.repository.impl.ItemRepositoryImpl;
import com.stefanine.service.ItemService;

import javax.persistence.EntityManager;

public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository;
    EntityManager entityManager;

    public ItemServiceImpl(){
        this.entityManager = DatabaseConnector.getEntityManager();
        itemRepository = new ItemRepositoryImpl(entityManager);
    }

    public void adicionaItem(){

    }

    public void removeItem(){

    }
}

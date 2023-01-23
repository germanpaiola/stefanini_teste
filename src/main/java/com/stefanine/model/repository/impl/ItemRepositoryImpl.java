package com.stefanine.model.repository.impl;

import com.stefanine.model.Item;
import com.stefanine.model.repository.ItemRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {

    private EntityManager em;

    public ItemRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Item findById(Integer id) {
        return em.find(Item.class, id);
    }

    @Override
    public List<Item> findByDescricao(String desc) {
        TypedQuery<Item> query = em.createQuery("SELECT i FROM items i WHERE i.DESCRICAO = :desc", Item.class);
        query.setParameter("desc", desc);
        return query.getResultList();
    }

    @Override
    public Item save(Item item) {
        em.getTransaction().begin();
        if(item.getId() == null){
            em.persist(item);
        } else {
            item = em.merge(item);
        }
        em.getTransaction().commit();
        return item;
    }

    @Override
    public void delete(Item item) {
        if (em.contains(item)) {
            em.remove(item);
        } else {
            em.merge(item);
        }
    }
}

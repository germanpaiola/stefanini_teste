package com.stefanine.model.repository.impl;

import com.stefanine.model.Pedido;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PedidoRepositoryImpl implements com.stefanine.model.repository.PedidoRepository {

    private EntityManager em;

    public PedidoRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Pedido findbyId(int id) {
        return em.find(Pedido.class, id);
    }

    @Override
    public List<Pedido> findByNomeCliente(String nomeCliente) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM pedidos p WHERE p.nome_cliente = :nomeCliente", Pedido.class);
        query.setParameter("nomeCliente", nomeCliente);
        return query.getResultList();
    }

    @Override
    public Pedido save(Pedido pedido) {
        em.getTransaction().begin();
        if(pedido.getId() == null){
            em.persist(pedido);
        } else {
            pedido = em.merge(pedido);
        }
        em.getTransaction().commit();
    return pedido;
    }

    @Override
    public void delete(Pedido pedido) {
        if (em.contains(pedido)) {
            em.remove(pedido);
        } else {
            em.merge(pedido);
        }
    }
}

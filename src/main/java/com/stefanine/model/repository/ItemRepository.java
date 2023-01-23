package com.stefanine.model.repository;

import com.stefanine.model.Item;

import java.util.List;

public interface ItemRepository {
    Item findById(Integer id);
    List<Item> findByDescricao(String desc);
    Item save(Item item);
    void delete(Item item);
}

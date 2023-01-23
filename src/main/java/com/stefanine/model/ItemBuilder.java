package com.stefanine.model;

public class ItemBuilder {

    public ItemBuilder(){
        this.item = new Item();
    }

    private final Item item;

    public ItemBuilder descricao(String desc){
        item.setDescricao(desc);
        return this;
    }

    public ItemBuilder quantidade(int q){
        item.setQuantidade(q);
        return this;
    }

    public ItemBuilder valor(double valor){
        item.setValor(valor);
        return this;
    }

    public Item build(){
        return item;
    }
}

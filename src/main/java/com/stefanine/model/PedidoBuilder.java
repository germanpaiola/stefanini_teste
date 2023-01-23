package com.stefanine.model;

import java.util.ArrayList;
import java.util.Date;

public class PedidoBuilder {

    private final Pedido pedido;

    public PedidoBuilder(){
        this.pedido = new Pedido();
        this.pedido.setItensDopedido(new ArrayList<>());
    }

    public Pedido build(){
        return pedido;
    }

    public PedidoBuilder nomeCliente(String nome){
        pedido.setNomeCliente(nome);
        return this;
    }

    public PedidoBuilder tipo(String tipo){
        pedido.setTipo(tipo);
        return this;
    }

    public PedidoBuilder cnpjOuCpf(String cnpjOuCpf){
        pedido.setCnpjOuCpf(cnpjOuCpf);
        return this;
    }

    public PedidoBuilder dataCompra(Date dataCompra){
        pedido.setDataCompra(dataCompra);
        return this;
    }
}

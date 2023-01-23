package com.stefanine.service;

import com.stefanine.exceptions.RegraNegocioException;
import com.stefanine.model.Item;
import com.stefanine.model.Pedido;

import java.util.List;

public class Calculadora {

    public void calculaImpostos(Pedido pedido){
        if(pedido.getTipo().equals("física")){
            calculaImpostoPessoaFisica(pedido);
        } else if(pedido.getTipo().equals("jurídica")){
            calculaImpostoPessoaJuridica(pedido);
        } else {
            throw new RegraNegocioException("Tipo da pessoa inválido. Deve ser 'física' ou 'jurídica';");
        }
    }

    private void calculaImpostoPessoaFisica(Pedido pedido){
        double valorTotal = somaValorDosItens(pedido.getItensDopedido());
        valorTotal += valorTotal * 5.2/100;
        pedido.setValorTotal(valorTotal);
    }

    private void calculaImpostoPessoaJuridica(Pedido pedido){
        double valorTotal = somaValorDosItens(pedido.getItensDopedido());
        if(valorTotal == 0)
            return;
        valorTotal += (1 + (valorTotal * 3.2/100));
        pedido.setValorTotal(valorTotal);
    }

    private double somaValorDosItens(List<Item> itens){
        double valorTotal = 0;
        for(Item item : itens)
            valorTotal+=item.getValor() * item.getQuantidade();
        return valorTotal;
    }

    private double somaValorDosItens(Pedido pedido){
        double valorTotal = 0;
        for(Item item : pedido.getItensDopedido())
            valorTotal+=item.getValor() * item.getQuantidade();
        return valorTotal;
    }
}

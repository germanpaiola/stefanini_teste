package com.stefanine.service;

import com.stefanine.model.Item;
import com.stefanine.model.Pedido;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Impressora {

    public void imprime(Pedido pedido){
        StringBuilder builder = new StringBuilder();
        builder.append("===================== RESUMO DO PEDIDO =====================").append("\n")
                .append(pedido.getNomeCliente()).append("\n")
                .append(pedido.getCnpjOuCpf()).append("\n");
        builder.append("============================================================").append("\n");
        for(Item item : pedido.getItensDopedido()){
            builder.append(item.getDescricao()).append("\t")
                    .append(item.getQuantidade()).append("\t")
                    .append("x").append("\t")
                    .append(item.getValor()).append("\n");
        }
        builder.append("============================================================").append("\n")
                .append("TOTAL").append("\t\t\t\t").append(formata(pedido.getValorTotal())).append("\n")
                .append("============================================================");
        System.out.println(builder);
    }

    private String formata(double valor){
        DecimalFormat df = new DecimalFormat("##########.##");
        df.setRoundingMode(RoundingMode.UP);
        return df.format(valor).toString();
    }
}

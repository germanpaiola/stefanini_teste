package com.stefanine.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="pedidos", schema="stefanine_teste")
public class Pedido {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_cliente", unique=true)
    private String nomeCliente;

    @Column(name = "tipo", unique=true)
    private String tipo;

    @Column(name = "cnpj_ou_cpf", unique=true)
    private String cnpjOuCpf;

    @Column(name = "data_compra", unique=true)
    private Date dataCompra;

    @OneToMany
    @JoinTable(name = "pedido_item",
            joinColumns = {@JoinColumn(name = "pedido_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private List<Item> itensDopedido;

    @Column(name = "valor_total", unique=true)
    private double valorTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCnpjOuCpf() {
        return cnpjOuCpf;
    }

    public void setCnpjOuCpf(String cnpjOuCpf) {
        this.cnpjOuCpf = cnpjOuCpf;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Item> getItensDopedido() {
        return itensDopedido;
    }

    public void setItensDopedido(List<Item> itensDopedido) {
        this.itensDopedido = itensDopedido;
    }
}

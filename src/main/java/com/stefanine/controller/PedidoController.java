package com.stefanine.controller;

import com.stefanine.service.PedidoService;
import com.stefanine.service.impl.PedidoServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "PedidoController", urlPatterns = {"/pedido"})
public class PedidoController extends HttpServlet {

    PedidoService pedidoService;

    public PedidoController(){
        super();
        this.pedidoService = new PedidoServiceImpl();
    }

}

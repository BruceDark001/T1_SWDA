package com.ecommerce.controller;

import com.ecommerce.dto.PedidoDTO;
import com.ecommerce.entity.Pedido;
import com.ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoDTO dto) {
        Pedido nuevoPedido = pedidoService.procesarPedido(dto);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }
}
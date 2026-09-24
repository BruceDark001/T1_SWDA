package com.ecommerce.service;

import com.ecommerce.dto.DetallePedidoDTO;
import com.ecommerce.dto.PedidoDTO;
import com.ecommerce.entity.DetallePedido;
import com.ecommerce.entity.Pedido;
import com.ecommerce.entity.Producto;
import com.ecommerce.repository.DetallePedidoRepository;
import com.ecommerce.repository.PedidoRepository;
import com.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Pedido procesarPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setCliente(pedidoDTO.getCliente());
        pedido.setMetodoPago(pedidoDTO.getMetodoPago());
        pedido.setEstadoPedido("PROCESADO");
        pedido.setFechaCompra(LocalDateTime.now());
        
        double montoTotal = 0.0;
        pedido = pedidoRepository.save(pedido); // Guarda el pedido para generar el ID

        for (DetallePedidoDTO detDTO : pedidoDTO.getDetalles()) {
            // En un caso real se validaría si el producto existe
            Producto producto = productoRepository.findById(detDTO.getProductoId()).orElseThrow();
            
            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(producto.getPrecio() * detDTO.getCantidad());
            
            montoTotal += detalle.getSubtotal();
            detallePedidoRepository.save(detalle); // Guarda cada detalle
        }
        
        pedido.setMontoTotal(montoTotal);
        return pedidoRepository.save(pedido); // Actualiza el monto total
    }
}
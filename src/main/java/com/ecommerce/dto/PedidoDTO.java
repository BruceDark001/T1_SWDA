package com.ecommerce.dto;

import java.util.List;

public class PedidoDTO {
    private String cliente;
    private String metodoPago;
    private List<DetallePedidoDTO> detalles;
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	public List<DetallePedidoDTO> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetallePedidoDTO> detalles) {
		this.detalles = detalles;
	} 

}
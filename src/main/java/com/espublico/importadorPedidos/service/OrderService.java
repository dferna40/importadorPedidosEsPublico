package com.espublico.importadorPedidos.service;

import java.util.List;

import com.espublico.importadorPedidos.dto.OrderDTO;

public interface OrderService {

	 List<OrderDTO> findAllOrders();
}

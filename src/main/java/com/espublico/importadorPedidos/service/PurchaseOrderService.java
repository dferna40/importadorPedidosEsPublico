package com.espublico.importadorPedidos.service;

import java.util.List;

import com.espublico.importadorPedidos.dto.PurchaseOrderDTO;

public interface PurchaseOrderService {

	 List<PurchaseOrderDTO> findAllOrders();
}

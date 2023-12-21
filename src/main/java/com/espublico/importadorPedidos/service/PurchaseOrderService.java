package com.espublico.importadorPedidos.service;

import java.util.List;

import com.espublico.importadorPedidos.model.PurchaseOrder;

public interface PurchaseOrderService {

	 List<PurchaseOrder> findAllOrders();
}

package com.espublico.importadorPedidos.service;

import java.util.Map;

public interface PurchaseOrderService {

	Map<String, Long> countPurchaseOrdersByCountry();
	
	Map<String, Long> countPurchaseOrdersByRegion();
	
	Map<String, Long> countPurchaseOrdersByItemType();
	
	Map<String, Long> countPurchaseOrdersBySalesChannel();
	
	Map<String, Long> countPurchaseOrdersByOrderPriority();
}

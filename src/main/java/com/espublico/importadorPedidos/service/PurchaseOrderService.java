package com.espublico.importadorPedidos.service;

import java.util.Map;

public interface PurchaseOrderService {

	Map<String, Long> countPurchaseOrdersByCountryAndHistoryId(Long historyId);
	
	Map<String, Long> countPurchaseOrdersByRegionAndHistoryId(Long historyId);
	
	Map<String, Long> countPurchaseOrdersByItemTypeAndHistoryId(Long historyId);
	
	Map<String, Long> countPurchaseOrdersBySalesChannelAndHistoryId(Long historyId);
	
	Map<String, Long> countPurchaseOrdersByOrderPriorityAndHistoryId(Long historyId);
}

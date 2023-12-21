package com.espublico.importadorPedidos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espublico.importadorPedidos.repository.PurchaseOrderRepository;
import com.espublico.importadorPedidos.service.PurchaseOrderService;

@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService{
	
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;
	
	@Override
	public Map<String, Long> countPurchaseOrdersByCountryAndHistoryId(Long historyId) {
		 List<Object[]> results = purchaseOrderRepository.countPurchaseOrdersByCountryAndHistoryId(historyId);
		    Map<String, Long> countryOrderCounts = new HashMap<>();
		    for (Object[] result : results) {
		        String country = (String) result[0];
		        Long count = (Long) result[1];
		        countryOrderCounts.put(country, count);
		    }
		    return countryOrderCounts;
	}

	@Override
	public Map<String, Long> countPurchaseOrdersByRegionAndHistoryId(Long historyId) {
		List<Object[]> results = purchaseOrderRepository.countPurchaseOrdersByRegionAndHistoryId(historyId);
	    Map<String, Long> regionOrderCounts = new HashMap<>();
	    for (Object[] result : results) {
	        String region = (String) result[0];
	        Long count = (Long) result[1];
	        regionOrderCounts.put(region, count);
	    }
	    return regionOrderCounts;
	}

	@Override
	public Map<String, Long> countPurchaseOrdersByItemTypeAndHistoryId(Long historyId) {
		List<Object[]> results = purchaseOrderRepository.countPurchaseOrdersByItemTypeAndHistoryId(historyId);
	    Map<String, Long> itemTypeOrderCounts = new HashMap<>();
	    for (Object[] result : results) {
	        String itemType = (String) result[0];
	        Long count = (Long) result[1];
	        itemTypeOrderCounts.put(itemType, count);
	    }
	    return itemTypeOrderCounts;
	}

	@Override
	public Map<String, Long> countPurchaseOrdersBySalesChannelAndHistoryId(Long historyId) {
		List<Object[]> results = purchaseOrderRepository.countPurchaseOrdersBySalesChannelAndHistoryId(historyId);
	    Map<String, Long> salesChannelOrderCounts = new HashMap<>();
	    for (Object[] result : results) {
	        String salesChannel = (String) result[0];
	        Long count = (Long) result[1];
	        salesChannelOrderCounts.put(salesChannel, count);
	    }
	    return salesChannelOrderCounts;
	}

	@Override
	public Map<String, Long> countPurchaseOrdersByOrderPriorityAndHistoryId(Long historyId) {
		List<Object[]> results = purchaseOrderRepository.countPurchaseOrdersByOrderPriorityAndHistoryId(historyId);
	    Map<String, Long> orderPriorityOrderCounts = new HashMap<>();
	    for (Object[] result : results) {
	        String orderPriority = (String) result[0];
	        Long count = (Long) result[1];
	        orderPriorityOrderCounts.put(orderPriority, count);
	    }
	    return orderPriorityOrderCounts;
	}

}

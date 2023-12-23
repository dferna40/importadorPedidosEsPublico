package com.espublico.importadorPedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.PurchaseOrder;

@Repository("purchaseOrderRepository")
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
	
	@Query("SELECT p.country, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idHistory GROUP BY p.country")
	List<Object[]> countPurchaseOrdersByCountryAndHistoryId(@Param("idHistory") Long historyId);
	
	@Query("SELECT p.region, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idHistory GROUP BY p.region")
	List<Object[]> countPurchaseOrdersByRegionAndHistoryId(@Param("idHistory") Long historyId);
	
	@Query("SELECT p.itemType, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idHistory GROUP BY p.itemType")
	List<Object[]> countPurchaseOrdersByItemTypeAndHistoryId(@Param("idHistory") Long historyId);
	
	@Query("SELECT p.salesChannel, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idHistory GROUP BY p.salesChannel")
	List<Object[]> countPurchaseOrdersBySalesChannelAndHistoryId(@Param("idHistory") Long historyId);
	
	@Query("SELECT p.orderPriority, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idHistory GROUP BY p.orderPriority")
	List<Object[]> countPurchaseOrdersByOrderPriorityAndHistoryId(@Param("idHistory") Long historyId);
	
	@Query("SELECT p FROM PurchaseOrder p WHERE p.historyOrder.id = :idHistory ORDER BY p.orderId")
	List<PurchaseOrder> findByHistoryOrderIdByOrderId(@Param("idHistory") Long historyId);


}

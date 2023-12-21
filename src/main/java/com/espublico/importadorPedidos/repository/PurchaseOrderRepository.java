package com.espublico.importadorPedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.PurchaseOrder;

@Repository("purchaseOrderRepository")
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
	
	@Query("SELECT p.country, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idMaxHistory GROUP BY p.country")
	List<Object[]> countPurchaseOrdersByCountryAndHistoryId(@Param("idMaxHistory") Long historyId);
	
	@Query("SELECT p.region, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idMaxHistory GROUP BY p.region")
	List<Object[]> countPurchaseOrdersByRegionAndHistoryId(@Param("idMaxHistory") Long historyId);
	
	@Query("SELECT p.itemType, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idMaxHistory GROUP BY p.itemType")
	List<Object[]> countPurchaseOrdersByItemTypeAndHistoryId(@Param("idMaxHistory") Long historyId);
	
	@Query("SELECT p.salesChannel, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idMaxHistory GROUP BY p.salesChannel")
	List<Object[]> countPurchaseOrdersBySalesChannelAndHistoryId(@Param("idMaxHistory") Long historyId);
	
	@Query("SELECT p.orderPriority, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idMaxHistory GROUP BY p.orderPriority")
	List<Object[]> countPurchaseOrdersByOrderPriorityAndHistoryId(@Param("idMaxHistory") Long historyId);

}

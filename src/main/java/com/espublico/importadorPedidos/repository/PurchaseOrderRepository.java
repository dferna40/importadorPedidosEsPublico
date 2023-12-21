package com.espublico.importadorPedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.PurchaseOrder;

@Repository("purchaseOrderRepository")
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
	
	@Query("SELECT p.country, COUNT(p) FROM PurchaseOrder p GROUP BY p.country")
	List<Object[]> countPurchaseOrdersByCountry();
	
	@Query("SELECT p.region, COUNT(p) FROM PurchaseOrder p GROUP BY p.region")
	List<Object[]> countPurchaseOrdersByRegion();
	
	@Query("SELECT p.itemType, COUNT(p) FROM PurchaseOrder p GROUP BY p.itemType")
	List<Object[]> countPurchaseOrdersByItemType();
	
	@Query("SELECT p.salesChannel, COUNT(p) FROM PurchaseOrder p GROUP BY p.salesChannel")
	List<Object[]> countPurchaseOrdersBySalesChannel();
	
	@Query("SELECT p.orderPriority, COUNT(p) FROM PurchaseOrder p GROUP BY p.orderPriority")
	List<Object[]> countPurchaseOrdersByOrderPriority();

}

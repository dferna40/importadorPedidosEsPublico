package com.espublico.importadorPedidos.repository;

import com.espublico.importadorPedidos.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad PurchaseOrder. Esta interfaz extiende
 * JpaRepository, proporcionando operaciones CRUD estándar para la entidad
 * PurchaseOrder, así como operaciones personalizadas adicionales. Está marcada
 * como un repositorio en el contexto de Spring, lo que facilita su inyección de
 * dependencias y gestión de operaciones de base de datos relacionadas con
 * PurchaseOrder.
 */
@Repository("purchaseOrderRepository")
public interface IPurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

	/**
	 * Realiza un conteo de órdenes de compra agrupadas por país basado en un
	 * identificador de historial. Esta consulta personalizada retorna una lista de
	 * pares (país, conteo) para todas las órdenes de compra asociadas a un
	 * identificador de historial específico.
	 *
	 * @param historyId El identificador del historial de órdenes para filtrar.
	 * @return Una lista de pares (país, conteo).
	 */
	@Query("SELECT p.country, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idHistory GROUP BY p.country")
	List<Object[]> countPurchaseOrdersByCountryAndHistoryId(@Param("idHistory") Long historyId);

	/**
	 * Realiza un conteo de órdenes de compra agrupadas por región basado en un
	 * identificador de historial. Esta consulta personalizada retorna una lista de
	 * pares (región, conteo) para todas las órdenes de compra asociadas a un
	 * identificador de historial específico.
	 *
	 * @param historyId El identificador del historial de órdenes para filtrar.
	 * @return Una lista de pares (país, conteo).
	 */
	@Query("SELECT p.region, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idHistory GROUP BY p.region")
	List<Object[]> countPurchaseOrdersByRegionAndHistoryId(@Param("idHistory") Long historyId);

	/**
	 * Realiza un conteo de órdenes de compra agrupadas por tipo de item basado en
	 * un identificador de historial. Esta consulta personalizada retorna una lista
	 * de pares (tipo de item, conteo) para todas las órdenes de compra asociadas a
	 * un identificador de historial específico.
	 *
	 * @param historyId El identificador del historial de órdenes para filtrar.
	 * @return Una lista de pares (país, conteo).
	 */
	@Query("SELECT p.itemType, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idHistory GROUP BY p.itemType")
	List<Object[]> countPurchaseOrdersByItemTypeAndHistoryId(@Param("idHistory") Long historyId);

	/**
	 * Realiza un conteo de órdenes de compra agrupadas por canal de ventas basado
	 * en un identificador de historial. Esta consulta personalizada retorna una
	 * lista de pares (canal de ventas, conteo) para todas las órdenes de compra
	 * asociadas a un identificador de historial específico.
	 *
	 * @param historyId El identificador del historial de órdenes para filtrar.
	 * @return Una lista de pares (país, conteo).
	 */
	@Query("SELECT p.salesChannel, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idHistory GROUP BY p.salesChannel")
	List<Object[]> countPurchaseOrdersBySalesChannelAndHistoryId(@Param("idHistory") Long historyId);

	/**
	 * Realiza un conteo de órdenes de compra agrupadas por prioridad de orden
	 * basado en un identificador de historial. Esta consulta personalizada retorna
	 * una lista de pares (prioridad de orden, conteo) para todas las órdenes de
	 * compra asociadas a un identificador de historial específico.
	 *
	 * @param historyId El identificador del historial de órdenes para filtrar.
	 * @return Una lista de pares (país, conteo).
	 */
	@Query("SELECT p.orderPriority, COUNT(p) FROM PurchaseOrder p WHERE p.historyOrder.id = :idHistory GROUP BY p.orderPriority")
	List<Object[]> countPurchaseOrdersByOrderPriorityAndHistoryId(@Param("idHistory") Long historyId);

	/**
	 * Encuentra todas las órdenes de compra asociadas a un identificador de
	 * historial específico y las ordena por orderId. Esta consulta personalizada
	 * retorna una lista de órdenes de compra ordenadas por su orderId para un
	 * historial específico.
	 *
	 * @param historyId El identificador del historial de órdenes para filtrar.
	 * @return Una lista de órdenes de compra ordenadas.
	 */
	@Query("SELECT p FROM PurchaseOrder p WHERE p.historyOrder.id = :idHistory ORDER BY p.orderId")
	Optional<List<PurchaseOrder>> findByHistoryOrderIdOrderByOrderId(@Param("idHistory") Long historyId);

}

package com.espublico.importadorPedidos.service;

import java.util.Map;

/**
 * Interfaz de servicio para operaciones relacionadas con órdenes de compra.
 * Esta interfaz define métodos para analizar y agrupar órdenes de compra
 * basándose en diferentes criterios, como país, región, tipo de ítem, canal de
 * ventas y prioridad de la orden. Las agrupaciones se realizan considerando un
 * identificador de historial específico, proporcionando insights útiles sobre
 * los datos de las órdenes.
 */
public interface IPurchaseOrderService {

	/**
	 * Cuenta las órdenes de compra agrupadas por país para un identificador de
	 * historial específico.
	 *
	 * @param historyId El identificador del historial de órdenes.
	 * @return Un mapa con el país como clave y el conteo de órdenes como valor.
	 */
	Map<String, Long> countPurchaseOrdersByCountryAndHistoryId(Long historyId);

	/**
	 * Cuenta las órdenes de compra agrupadas por región para un identificador de
	 * historial específico.
	 *
	 * @param historyId El identificador del historial de órdenes.
	 * @return Un mapa con la región como clave y el conteo de órdenes como valor.
	 */
	Map<String, Long> countPurchaseOrdersByRegionAndHistoryId(Long historyId);

	/**
	 * Cuenta las órdenes de compra agrupadas por tipo de ítem para un identificador
	 * de historial específico.
	 *
	 * @param historyId El identificador del historial de órdenes.
	 * @return Un mapa con el tipo de ítem como clave y el conteo de órdenes como
	 *         valor.
	 */
	Map<String, Long> countPurchaseOrdersByItemTypeAndHistoryId(Long historyId);

	/**
	 * Cuenta las órdenes de compra agrupadas por canal de ventas para un
	 * identificador de historial específico.
	 *
	 * @param historyId El identificador del historial de órdenes.
	 * @return Un mapa con el canal de ventas como clave y el conteo de órdenes como
	 *         valor.
	 */
	Map<String, Long> countPurchaseOrdersBySalesChannelAndHistoryId(Long historyId);

	/**
	 * Cuenta las órdenes de compra agrupadas por prioridad de la orden para un
	 * identificador de historial específico.
	 *
	 * @param historyId El identificador del historial de órdenes.
	 * @return Un mapa con la prioridad de la orden como clave y el conteo de
	 *         órdenes como valor.
	 */
	Map<String, Long> countPurchaseOrdersByOrderPriorityAndHistoryId(Long historyId);
}

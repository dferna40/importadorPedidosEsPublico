package com.espublico.importadorPedidos.service.impl;

import com.espublico.importadorPedidos.repository.IPurchaseOrderRepository;
import com.espublico.importadorPedidos.service.IPurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementación del servicio PurchaseOrderService. Esta clase se encarga de
 * agrupar y contar órdenes de compra basadas en varios criterios, como país,
 * región, tipo de ítem, canal de ventas y prioridad de la orden. Utiliza el
 * repositorio PurchaseOrderRepository para realizar consultas de datos
 * específicas y compilar los resultados en mapas.
 */
@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private IPurchaseOrderRepository purchaseOrderRepository;

	/**
	 * Cuenta las órdenes de compra agrupadas por país para un identificador de
	 * historial específico. Realiza una consulta al repositorio para obtener los
	 * conteos y los compila en un mapa.
	 *
	 * @param historyId El identificador del historial de órdenes.
	 * @return Un mapa con el país como clave y el conteo de órdenes como valor.
	 */
	@Override
	public Map<String, Long> countPurchaseOrdersByCountryAndHistoryId(Long historyId, Long userId) {
		List<Object[]> results = purchaseOrderRepository.countPurchaseOrdersByCountryAndHistoryId(historyId, userId);
		Map<String, Long> countryOrderCounts = new HashMap<>();
		for (Object[] result : results) {
			String country = (String) result[0];
			Long count = (Long) result[1];
			countryOrderCounts.put(country, count);
		}
		return countryOrderCounts;
	}

	/**
	 * Cuenta las órdenes de compra agrupadas por región para un identificador de
	 * historial específico. Realiza una consulta al repositorio para obtener los
	 * conteos y los compila en un mapa.
	 *
	 * @param historyId El identificador del historial de órdenes.
	 * @return Un mapa con el país como clave y el conteo de órdenes como valor.
	 */
	@Override
	public Map<String, Long> countPurchaseOrdersByRegionAndHistoryId(Long historyId, Long userId) {
		List<Object[]> results = purchaseOrderRepository.countPurchaseOrdersByRegionAndHistoryId(historyId, userId);
		Map<String, Long> regionOrderCounts = new HashMap<>();
		for (Object[] result : results) {
			String region = (String) result[0];
			Long count = (Long) result[1];
			regionOrderCounts.put(region, count);
		}
		return regionOrderCounts;
	}

	/**
	 * Cuenta las órdenes de compra agrupadas por tipo de item para un identificador
	 * de historial específico. Realiza una consulta al repositorio para obtener los
	 * conteos y los compila en un mapa.
	 *
	 * @param historyId El identificador del historial de órdenes.
	 * @return Un mapa con el país como clave y el conteo de órdenes como valor.
	 */
	@Override
	public Map<String, Long> countPurchaseOrdersByItemTypeAndHistoryId(Long historyId, Long userId) {
		List<Object[]> results = purchaseOrderRepository.countPurchaseOrdersByItemTypeAndHistoryId(historyId, userId);
		Map<String, Long> itemTypeOrderCounts = new HashMap<>();
		for (Object[] result : results) {
			String itemType = (String) result[0];
			Long count = (Long) result[1];
			itemTypeOrderCounts.put(itemType, count);
		}
		return itemTypeOrderCounts;
	}

	/**
	 * Cuenta las órdenes de compra agrupadas por canal de envio para un
	 * identificador de historial específico. Realiza una consulta al repositorio
	 * para obtener los conteos y los compila en un mapa.
	 *
	 * @param historyId El identificador del historial de órdenes.
	 * @return Un mapa con el país como clave y el conteo de órdenes como valor.
	 */
	@Override
	public Map<String, Long> countPurchaseOrdersBySalesChannelAndHistoryId(Long historyId, Long userId) {
		List<Object[]> results = purchaseOrderRepository.countPurchaseOrdersBySalesChannelAndHistoryId(historyId, userId);
		Map<String, Long> salesChannelOrderCounts = new HashMap<>();
		for (Object[] result : results) {
			String salesChannel = (String) result[0];
			Long count = (Long) result[1];
			salesChannelOrderCounts.put(salesChannel, count);
		}
		return salesChannelOrderCounts;
	}

	/**
	 * Cuenta las órdenes de compra agrupadas por prioridad de orden para un
	 * identificador de historial específico. Realiza una consulta al repositorio
	 * para obtener los conteos y los compila en un mapa.
	 *
	 * @param historyId El identificador del historial de órdenes.
	 * @return Un mapa con el país como clave y el conteo de órdenes como valor.
	 */
	@Override
	public Map<String, Long> countPurchaseOrdersByOrderPriorityAndHistoryId(Long historyId, Long userId) {
		List<Object[]> results = purchaseOrderRepository.countPurchaseOrdersByOrderPriorityAndHistoryId(historyId, userId);
		Map<String, Long> orderPriorityOrderCounts = new HashMap<>();
		for (Object[] result : results) {
			String orderPriority = (String) result[0];
			Long count = (Long) result[1];
			orderPriorityOrderCounts.put(orderPriority, count);
		}
		return orderPriorityOrderCounts;
	}

}

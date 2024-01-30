package com.espublico.importadorPedidos.service.impl;

import com.espublico.importadorPedidos.dto.*;
import com.espublico.importadorPedidos.service.IFinalSummaryService;
import com.espublico.importadorPedidos.service.IPurchaseOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementación del servicio FinalSummaryService. Esta clase se encarga de
 * compilar resúmenes finales basados en varios criterios, utilizando datos de
 * órdenes de compra. Se apoya en el servicio PurchaseOrderService para realizar
 * agrupaciones y conteos de órdenes basados en diferentes dimensiones.
 */
@Service("finalSummaryService")
public class FinalSummaryServiceImpl implements IFinalSummaryService {

	private static final Logger logger = LoggerFactory.getLogger(FinalSummaryServiceImpl.class);
	
	@Autowired
	@Qualifier("purchaseOrderService")
	private IPurchaseOrderService purchaseOrderService;

	/**
	 * Genera un resumen final compuesto basado en un identificador de historial
	 * específico. Este método agrupa y cuenta las órdenes de compra por país,
	 * región, tipo de ítem, prioridad de la orden y canal de ventas. Luego, compila
	 * estos conteos en DTOs específicos y los agrupa en un DTO final de resumen.
	 *
	 * @param idHistory El identificador del historial de órdenes a partir del cual
	 *                  se generará el resumen.
	 * @return Un objeto FinalSummaryDTO que contiene los resúmenes agrupados.
	 */
	@Override
	public FinalSummaryDTO resultFinalSummary(Long idHistory) {

		logger.info("Empieza el procesamiento de los datos para el conteo del resumen final");
		
		Map<String, Long> countryOrderCounts = purchaseOrderService.countPurchaseOrdersByCountryAndHistoryId(idHistory);
		Map<String, Long> regionOrderCounts = purchaseOrderService.countPurchaseOrdersByRegionAndHistoryId(idHistory);
		Map<String, Long> itemTpyeOrderCounts = purchaseOrderService
				.countPurchaseOrdersByItemTypeAndHistoryId(idHistory);
		Map<String, Long> orderPriorityOrderCounts = purchaseOrderService
				.countPurchaseOrdersByOrderPriorityAndHistoryId(idHistory);
		Map<String, Long> salesChannelOrderCounts = purchaseOrderService
				.countPurchaseOrdersBySalesChannelAndHistoryId(idHistory);

		List<CountryFinalSummaryDTO> countryOrderCountList = new ArrayList<>();
		List<RegionFinalSummaryDTO> regionOrderCountList = new ArrayList<>();
		List<ItemTypeFinalSummaryDTO> itemTpyeOrderCountList = new ArrayList<>();
		List<OrderPriorityFinalSummaryDTO> orderPriorityOrderCountList = new ArrayList<>();
		List<SalesChannelFinalSummaryDTO> salesChannelOrderCountList = new ArrayList<>();

		for (Map.Entry<String, Long> entry : countryOrderCounts.entrySet()) {
			CountryFinalSummaryDTO countryOrderCount = new CountryFinalSummaryDTO(entry.getKey(), entry.getValue());
			countryOrderCountList.add(countryOrderCount);
		}

		for (Map.Entry<String, Long> entry : regionOrderCounts.entrySet()) {
			RegionFinalSummaryDTO regionOrderCount = new RegionFinalSummaryDTO(entry.getKey(), entry.getValue());
			regionOrderCountList.add(regionOrderCount);
		}

		for (Map.Entry<String, Long> entry : itemTpyeOrderCounts.entrySet()) {
			ItemTypeFinalSummaryDTO itemTpyeOrderCount = new ItemTypeFinalSummaryDTO(entry.getKey(), entry.getValue());
			itemTpyeOrderCountList.add(itemTpyeOrderCount);
		}

		for (Map.Entry<String, Long> entry : orderPriorityOrderCounts.entrySet()) {
			OrderPriorityFinalSummaryDTO orderPriorityOrderCount = new OrderPriorityFinalSummaryDTO(entry.getKey(),
					entry.getValue());
			orderPriorityOrderCountList.add(orderPriorityOrderCount);
		}

		for (Map.Entry<String, Long> entry : salesChannelOrderCounts.entrySet()) {
			SalesChannelFinalSummaryDTO salesChannelOrderCount = new SalesChannelFinalSummaryDTO(entry.getKey(),
					entry.getValue());
			salesChannelOrderCountList.add(salesChannelOrderCount);
		}
		
		logger.info("Finaliza el conteo");

		return new FinalSummaryDTO(countryOrderCountList, regionOrderCountList, itemTpyeOrderCountList,
				orderPriorityOrderCountList, salesChannelOrderCountList);
	}

}

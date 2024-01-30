package com.espublico.importadorPedidos.mapper;

import com.espublico.importadorPedidos.dto.PurchaseOrderDTO;
import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.model.PurchaseOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de mapeo para convertir entre objetos de dominio PurchaseOrder y
 * objetos de transferencia de datos PurchaseOrderDTO. Provee funcionalidades
 * para convertir objetos individuales y listas de objetos entre estas dos
 * representaciones, facilitando la separación entre la lógica de negocio y la
 * presentación de datos en diferentes capas de la aplicación.
 */

@Component("purchaseOrderMapper")
public class PurchaseOrderMapper {

	/**
	 * Convierte un objeto de dominio PurchaseOrder a su correspondiente DTO.
	 *
	 * @param order El objeto de dominio a ser convertido.
	 * @return Un objeto PurchaseOrderDTO correspondiente, o null si el objeto de
	 *         dominio es null.
	 */
	public PurchaseOrderDTO toDto(PurchaseOrder order) {
		if (order == null) {
			return null;
		}

		PurchaseOrderDTO dto = new PurchaseOrderDTO();
		dto.setPurchaseOrderId(order.getPurchaseOrderId());
		dto.setOrderId(order.getOrderId());
		dto.setOrderDate(order.getOrderDate());
		dto.setShipDate(order.getShipDate());
		dto.setOrderPriority(order.getOrderPriority());
		dto.setUnitsSold(order.getUnitsSold());
		dto.setCountry(order.getCountry());
		dto.setItemType(order.getItemType());
		dto.setRegion(order.getRegion());
		dto.setSalesChannel(order.getSalesChannel());
		dto.setTotalCost(order.getTotalCost());
		dto.setTotalRevenue(order.getTotalRevenue());
		dto.setTotalProfit(order.getTotalProfit());
		dto.setUnitCost(order.getUnitCost());
		dto.setUnitPrice(order.getUnitPrice());
		dto.setHistoryId(order.getHistoryOrder() != null ? order.getHistoryOrder().getHistoryId() : null);

		return dto;
	}

	/**
	 * Convierte un objeto PurchaseOrderDTO a su correspondiente objeto de dominio.
	 *
	 * @param orderDTO El DTO a ser convertido.
	 * @return Un objeto de dominio PurchaseOrder correspondiente, o null si el DTO
	 *         es null.
	 */
	public PurchaseOrder toEntity(PurchaseOrderDTO orderDTO) {
		if (orderDTO == null) {
			return null;
		}

		PurchaseOrder order = new PurchaseOrder();
		order.setPurchaseOrderId(orderDTO.getPurchaseOrderId());
		order.setOrderId(orderDTO.getOrderId());
		order.setOrderDate(orderDTO.getOrderDate());
		order.setShipDate(orderDTO.getShipDate());
		order.setOrderPriority(orderDTO.getOrderPriority());
		order.setUnitsSold(orderDTO.getUnitsSold());
		order.setCountry(orderDTO.getCountry());
		order.setItemType(orderDTO.getItemType());
		order.setRegion(orderDTO.getRegion());
		order.setSalesChannel(orderDTO.getSalesChannel());
		order.setTotalCost(orderDTO.getTotalCost());
		order.setTotalRevenue(orderDTO.getTotalRevenue());
		order.setTotalProfit(orderDTO.getTotalProfit());
		order.setUnitCost(orderDTO.getUnitCost());
		order.setUnitPrice(orderDTO.getUnitPrice());
		order.setHistoryOrder(orderDTO.getHistoryId() != null ? new HistoryOrder(orderDTO.getHistoryId()) : null);

		return order;
	}

	/**
	 * Convierte una lista de objetos de dominio PurchaseOrder a una lista de sus
	 * correspondientes DTOs.
	 *
	 * @param orders La lista de objetos de dominio a ser convertida.
	 * @return Una lista de objetos PurchaseOrderDTO, o null si la lista de dominio
	 *         es null.
	 */
	public List<PurchaseOrderDTO> toDtoList(List<PurchaseOrder> orders) {
		if (orders == null) {
			return null;
		}

		List<PurchaseOrderDTO> orderDTOs = new ArrayList<>();
		for (PurchaseOrder order : orders) {
			orderDTOs.add(toDto(order));
		}
		return orderDTOs;
	}

	/**
	 * Convierte una lista de objetos PurchaseOrderDTO a una lista de sus
	 * correspondientes objetos de dominio.
	 *
	 * @param orderDTOs La lista de DTOs a ser convertida.
	 * @return Una lista de objetos de dominio PurchaseOrder, o null si la lista de
	 *         DTOs es null.
	 */
	public List<PurchaseOrder> toEntityList(List<PurchaseOrderDTO> orderDTOs) {
		if (orderDTOs == null) {
			return null;
		}

		List<PurchaseOrder> orders = new ArrayList<>();
		for (PurchaseOrderDTO orderDTO : orderDTOs) {
			orders.add(toEntity(orderDTO));
		}
		return orders;
	}
}

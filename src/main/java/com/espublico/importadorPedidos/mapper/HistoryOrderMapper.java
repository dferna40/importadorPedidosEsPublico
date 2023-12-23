package com.espublico.importadorPedidos.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.espublico.importadorPedidos.dto.HistoryOrderDTO;
import com.espublico.importadorPedidos.model.HistoryOrder;

/**
 * Clase de mapeo para convertir entre objetos de dominio HistoryOrder y objetos
 * de transferencia de datos HistoryOrderDTO. Esta clase provee funcionalidades
 * para convertir objetos individuales y listas de objetos entre estas dos
 * representaciones, permitiendo una separación clara entre la lógica de negocio
 * y la presentación de datos.
 */
@Component("historyOrderMapper")
public class HistoryOrderMapper {

	/**
	 * Convierte un objeto de dominio HistoryOrder a su correspondiente DTO.
	 *
	 * @param historyOrder El objeto de dominio a ser convertido.
	 * @return Un objeto HistoryOrderDTO correspondiente, o null si el objeto de
	 *         dominio es null.
	 */
	public HistoryOrderDTO toDto(HistoryOrder historyOrder) {
		if (historyOrder == null) {
			return null;
		}

		HistoryOrderDTO dto = new HistoryOrderDTO();
		dto.setChangeDate(historyOrder.getChangeDate());
		dto.setHistoryId(historyOrder.getHistoryId());

		return dto;
	}

	/**
	 * Convierte un objeto HistoryOrderDTO a su correspondiente objeto de dominio.
	 *
	 * @param historyOrderDTO El DTO a ser convertido.
	 * @return Un objeto de dominio HistoryOrder correspondiente, o null si el DTO
	 *         es null.
	 */
	public HistoryOrder toEntity(HistoryOrderDTO historyOrderDTO) {
		if (historyOrderDTO == null) {
			return null;
		}

		HistoryOrder historyOrder = new HistoryOrder();
		historyOrder.setChangeDate(historyOrderDTO.getChangeDate());
		historyOrder.setHistoryId(historyOrderDTO.getHistoryId());

		return historyOrder;
	}

	/**
	 * Convierte una lista de objetos de dominio HistoryOrder a una lista de sus
	 * correspondientes DTOs.
	 *
	 * @param historyOrders La lista de objetos de dominio a ser convertida.
	 * @return Una lista de objetos HistoryOrderDTO, o null si la lista de dominio
	 *         es null.
	 */
	public List<HistoryOrderDTO> toDtoList(List<HistoryOrder> historyOrders) {
		if (historyOrders == null) {
			return null;
		}

		List<HistoryOrderDTO> historyOrdersDTOs = new ArrayList<>();
		for (HistoryOrder historyOrder : historyOrders) {
			historyOrdersDTOs.add(toDto(historyOrder));
		}
		return historyOrdersDTOs;
	}

	/**
	 * Convierte una lista de objetos HistoryOrderDTO a una lista de sus
	 * correspondientes objetos de dominio.
	 *
	 * @param historyOrdersDTOs La lista de DTOs a ser convertida.
	 * @return Una lista de objetos de dominio HistoryOrder, o null si la lista de
	 *         DTOs es null.
	 */
	public List<HistoryOrder> toEntityList(List<HistoryOrderDTO> historyOrdersDTOs) {
		if (historyOrdersDTOs == null) {
			return null;
		}

		List<HistoryOrder> historyOrders = new ArrayList<>();
		for (HistoryOrderDTO historyOrder : historyOrdersDTOs) {
			historyOrders.add(toEntity(historyOrder));
		}
		return historyOrders;
	}
}

package com.espublico.importadorPedidos.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.espublico.importadorPedidos.dto.HistoryOrderDTO;
import com.espublico.importadorPedidos.model.HistoryOrder;

@Component("historyOrderMapper")
public class HistoryOrderMapper {

	public HistoryOrderDTO toDto(HistoryOrder historyOrder) {
        if (historyOrder == null) {
            return null;
        }

        HistoryOrderDTO dto = new HistoryOrderDTO();
        dto.setChangeDate(historyOrder.getChangeDate());
        dto.setHistoryId(historyOrder.getHistoryId());

        return dto;
    }
	
	public HistoryOrder toEntity(HistoryOrderDTO historyOrderDTO) {
        if (historyOrderDTO == null) {
            return null;
        }

        HistoryOrder historyOrder = new HistoryOrder();
        historyOrder.setChangeDate(historyOrderDTO.getChangeDate());
        historyOrder.setHistoryId(historyOrderDTO.getHistoryId());

        return historyOrder;
    }
	
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

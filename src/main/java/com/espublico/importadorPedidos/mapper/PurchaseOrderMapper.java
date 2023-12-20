package com.espublico.importadorPedidos.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.espublico.importadorPedidos.dto.PurchaseOrderDTO;
import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.model.PurchaseOrder;

@Component("purchaseOrderMapper")
public class PurchaseOrderMapper {

	public PurchaseOrderDTO toDto(PurchaseOrder order) {
        if (order == null) {
            return null;
        }

        PurchaseOrderDTO dto = new PurchaseOrderDTO();
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

    public PurchaseOrder toEntity(PurchaseOrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }

        PurchaseOrder order = new PurchaseOrder();
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

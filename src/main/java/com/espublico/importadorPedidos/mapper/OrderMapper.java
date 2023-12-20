package com.espublico.importadorPedidos.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.espublico.importadorPedidos.dto.OrderDTO;
import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.model.Order;

@Component("orderMapper")
public class OrderMapper {

	public OrderDTO toDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderDTO dto = new OrderDTO();
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

    public Order toEntity(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }

        Order order = new Order();
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

    public List<OrderDTO> toDtoList(List<Order> orders) {
        if (orders == null) {
            return null;
        }

        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(toDto(order));
        }
        return orderDTOs;
    }

    public List<Order> toEntityList(List<OrderDTO> orderDTOs) {
        if (orderDTOs == null) {
            return null;
        }

        List<Order> orders = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOs) {
            orders.add(toEntity(orderDTO));
        }
        return orders;
    }
}

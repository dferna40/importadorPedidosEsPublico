package com.espublico.importadorPedidos.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.espublico.importadorPedidos.dto.OrderDTO;
import com.espublico.importadorPedidos.model.Order;

@Component
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

        if (order.getProduct() != null) {
            dto.setProductId(order.getProduct().getProductId());
            // Agregar más campos del producto si es necesario
        }
        if (order.getCustomer() != null) {
            dto.setCustomerId(order.getCustomer().getCustomerId());
            // Agregar más campos del cliente si es necesario
        }
        // Agrega los campos restantes necesarios
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

        // Aquí debes manejar la asignación de Product y Customer
        // Esto podría requerir buscar las entidades correspondientes en la base de datos

        // Agrega los campos restantes necesarios
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

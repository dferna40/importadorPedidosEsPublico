package com.espublico.importadorPedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}

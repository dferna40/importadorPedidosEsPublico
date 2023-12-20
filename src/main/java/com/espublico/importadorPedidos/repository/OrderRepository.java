package com.espublico.importadorPedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.PurchaseOrder;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<PurchaseOrder, Long> {

}

package com.espublico.importadorPedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espublico.importadorPedidos.model.HistoryOrder;

public interface HistoryOrderRepository extends JpaRepository<HistoryOrder, Long>{

}

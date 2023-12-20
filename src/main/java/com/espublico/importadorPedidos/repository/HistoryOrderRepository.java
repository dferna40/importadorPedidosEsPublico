package com.espublico.importadorPedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.HistoryOrder;

@Repository("historyOrderRepository")
public interface HistoryOrderRepository extends JpaRepository<HistoryOrder, Long>{

}

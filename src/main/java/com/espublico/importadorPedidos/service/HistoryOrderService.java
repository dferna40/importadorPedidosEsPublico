package com.espublico.importadorPedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.repository.HistoryOrderRepository;

@Service("historyOrderService")
public class HistoryOrderService {

	@Autowired
    private HistoryOrderRepository historyOrderRepository;

    public List<HistoryOrder> getAllHistoryOrders() {
        return historyOrderRepository.findAll();
    }
}

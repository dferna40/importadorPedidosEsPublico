package com.espublico.importadorPedidos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.repository.HistoryOrderRepository;
import com.espublico.importadorPedidos.service.HistoryOrderService;

@Service("historyOrderService")
public class HistoryOrderServiceImpl implements HistoryOrderService{

	@Autowired
    private HistoryOrderRepository historyOrderRepository;

    public List<HistoryOrder> getAllHistoryOrders() {
        return historyOrderRepository.findAll();
    }
}

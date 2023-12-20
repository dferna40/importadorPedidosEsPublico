package com.espublico.importadorPedidos.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.espublico.importadorPedidos.dto.PurchaseOrderDTO;
import com.espublico.importadorPedidos.service.PurchaseOrderService;

@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

	@Override
	public List<PurchaseOrderDTO> findAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}

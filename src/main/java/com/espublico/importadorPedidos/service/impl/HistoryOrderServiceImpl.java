package com.espublico.importadorPedidos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.repository.HistoryOrderRepository;
import com.espublico.importadorPedidos.service.HistoryOrderService;

/**
 * Implementación del servicio HistoryOrderService. Esta clase se encarga de la
 * gestión y recuperación de órdenes históricas, proporcionando funcionalidades
 * para interactuar con el repositorio de órdenes históricas
 * (HistoryOrderRepository). Facilita el acceso y manipulación de datos
 * históricos de órdenes en la aplicación.
 */
@Service("historyOrderService")
public class HistoryOrderServiceImpl implements HistoryOrderService {

	@Autowired
	private HistoryOrderRepository historyOrderRepository;

	/**
	 * Recupera todas las órdenes históricas almacenadas. Este método consulta el
	 * repositorio de órdenes históricas para obtener una lista completa de todas
	 * las órdenes históricas registradas en la base de datos.
	 *
	 * @return Una lista de objetos HistoryOrder que representan todas las órdenes
	 *         históricas almacenadas.
	 */
	public List<HistoryOrder> getAllHistoryOrders() {
		return historyOrderRepository.findAll();
	}
}

package com.espublico.importadorPedidos.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.espublico.importadorPedidos.dto.PurchaseOrderDTO;
import com.espublico.importadorPedidos.mapper.PurchaseOrderMapper;
import com.espublico.importadorPedidos.model.PurchaseOrder;
import com.espublico.importadorPedidos.repository.OrderRepository;
import com.espublico.importadorPedidos.service.ImportCsvService;
import com.espublico.importadorPedidos.util.CsvDataValidator;

@Service("importCsvService")
public class ImportCsvServiceImpl implements ImportCsvService {

	@Autowired
	@Qualifier("orderMapper")
	private PurchaseOrderMapper orderMapper;

	@Autowired
	@Qualifier("orderRepository")
	private OrderRepository orderRepository;

	List<String> errorMessages = new ArrayList<>();
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

	public List<String> processCsvFile(BufferedReader reader) throws IOException {
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		List<PurchaseOrderDTO> orders = new ArrayList<>();
		List<PurchaseOrder> modelOrders = new ArrayList<>();
		String line;
		while ((line = reader.readLine()) != null) {
			// Procesar cada línea del archivo CSV
			String[] values = line.split(",");

			if (validValuesCsv(values, line)) {
				PurchaseOrderDTO orderDTO = new PurchaseOrderDTO();

				orderDTO.setRegion(values[0]);
				orderDTO.setCountry(values[1]);
				orderDTO.setItemType(values[2]);
				orderDTO.setSalesChannel(values[3]);
				orderDTO.setOrderPriority(values[4]);
				LocalDate orderDate = LocalDate.parse(values[5], formatter);
				orderDTO.setOrderDate(orderDate);
				LocalDate shipDate = LocalDate.parse(values[7], formatter);
				orderDTO.setShipDate(shipDate);
				orderDTO.setOrderId(Long.parseLong(values[6]));
				orderDTO.setUnitsSold(Integer.parseInt(values[8]));
				orderDTO.setUnitPrice(Double.parseDouble(values[9]));
				orderDTO.setUnitCost(Double.parseDouble(values[10]));
				orderDTO.setTotalRevenue(Double.parseDouble(values[11]));
				orderDTO.setTotalCost(Double.parseDouble(values[12]));
				orderDTO.setTotalProfit(Double.parseDouble(values[13]));
				// En cada iteracion se añade la orden a la lista de ordenes
				orders.add(orderDTO);
			}
		}
		// Se convierte de dto a model
		modelOrders = orderMapper.toEntityList(orders);
		// Guardar en base de datos
		orderRepository.saveAll(modelOrders);

		System.out.println("");
		return errorMessages;

	}

	private boolean validValuesCsv(String[] values, String line) {

		boolean isValid = true;

		if (!CsvDataValidator.isValidString(values[0], "Region", errorMessages)) {
			isValid = false;
		}
		if (!CsvDataValidator.isValidString(values[1], "Country", errorMessages)) {
			isValid = false;
		}
		if (!CsvDataValidator.isValidString(values[2], "ItemType", errorMessages)) {
			isValid = false;
		}
		if (!CsvDataValidator.isValidString(values[3], "SalesChanne", errorMessages)) {
			isValid = false;
		}
		if (!CsvDataValidator.isValidString(values[4], "OrderPriority", errorMessages)) {
			isValid = false;
		}

		if (!CsvDataValidator.isValidDate(values[5], formatter, "OrderDate", errorMessages)) {
			isValid = false;
		}
		if (!CsvDataValidator.isValidDate(values[7], formatter, "ShipDate", errorMessages)) {
			isValid = false;
		}

		if (!CsvDataValidator.isValidNumber(values[6], "OrderId", errorMessages)) {
			isValid = false;
		}
		if (!CsvDataValidator.isValidNumber(values[8], "UnitsSold", errorMessages)) {
			isValid = false;
		}
		if (!CsvDataValidator.isValidNumber(values[9], "UnitPrice", errorMessages)) {
			isValid = false;
		}
		if (!CsvDataValidator.isValidNumber(values[10], "UnitCost", errorMessages)) {
			isValid = false;
		}
		if (!CsvDataValidator.isValidNumber(values[11], "TotalRevenue", errorMessages)) {
			isValid = false;
		}
		if (!CsvDataValidator.isValidNumber(values[12], "TotalCost", errorMessages)) {
			isValid = false;
		}
		if (!CsvDataValidator.isValidNumber(values[13], "TotalProfit", errorMessages)) {
			isValid = false;
		}
		return isValid;
	}
}

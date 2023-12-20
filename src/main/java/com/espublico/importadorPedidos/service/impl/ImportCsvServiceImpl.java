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
import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.model.PurchaseOrder;
import com.espublico.importadorPedidos.repository.HistoryOrderRepository;
import com.espublico.importadorPedidos.repository.PurchaseOrderRepository;
import com.espublico.importadorPedidos.service.ImportCsvService;
import com.espublico.importadorPedidos.util.CsvDataValidator;

import jakarta.transaction.Transactional;

@Service("importCsvService")
public class ImportCsvServiceImpl implements ImportCsvService {

	@Autowired
	@Qualifier("purchaseOrderMapper")
	private PurchaseOrderMapper purchaseOrderMapper;

	@Autowired
	@Qualifier("purchaseOrderRepository")
	private PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	@Qualifier("historyOrderRepository")
	private HistoryOrderRepository historyOrderRepository;

	List<String> errorMessages = new ArrayList<>();

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

	public List<String> processCsvFile(BufferedReader reader) throws IOException {
	    List<PurchaseOrderDTO> purchaseOrdersDTO = new ArrayList<>();
	    String line;

	    // Crear un nuevo HistoryOrder para esta importación
	    HistoryOrder newHistoryOrder = new HistoryOrder();
	    newHistoryOrder.setChangeDate(LocalDate.now()); // Configura la fecha actual
	    newHistoryOrder = historyOrderRepository.save(newHistoryOrder);

	    while ((line = reader.readLine()) != null) {
	        // Procesar cada línea del archivo CSV
	        String[] values = line.split(",");

					if (validValuesCsv(values, line)) {
						PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
						purchaseOrderDTO.setRegion(values[0]);
						purchaseOrderDTO.setCountry(values[1]);
						purchaseOrderDTO.setItemType(values[2]);
						purchaseOrderDTO.setSalesChannel(values[3]);
						purchaseOrderDTO.setOrderPriority(values[4]);
						LocalDate orderDate = LocalDate.parse(values[5], formatter);
						purchaseOrderDTO.setOrderDate(orderDate);
						LocalDate shipDate = LocalDate.parse(values[7], formatter);
						purchaseOrderDTO.setShipDate(shipDate);
						purchaseOrderDTO.setOrderId(Long.parseLong(values[6]));
						purchaseOrderDTO.setUnitsSold(Integer.parseInt(values[8]));
						purchaseOrderDTO.setUnitPrice(Double.parseDouble(values[9]));
						purchaseOrderDTO.setUnitCost(Double.parseDouble(values[10]));
						purchaseOrderDTO.setTotalRevenue(Double.parseDouble(values[11]));
						purchaseOrderDTO.setTotalCost(Double.parseDouble(values[12]));
						purchaseOrderDTO.setTotalProfit(Double.parseDouble(values[13]));
						// En cada iteracion se añade la orden a la lista de ordenes de pedidos
						purchaseOrdersDTO.add(purchaseOrderDTO);
					}
	    }

	    for (PurchaseOrderDTO dto : purchaseOrdersDTO) {
	        PurchaseOrder order = purchaseOrderMapper.toEntity(dto);
	        order.setHistoryOrder(newHistoryOrder); // Asignar el HistoryOrder común a cada PurchaseOrder
	        savePurchaseOrder(order, newHistoryOrder); // Guardar cada PurchaseOrder con el HistoryOrder común
	    }

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

	@Transactional
	public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder, HistoryOrder historyOrder) {
	    // Asociar HistoryOrder con PurchaseOrder
	    purchaseOrder.setHistoryOrder(historyOrder);
	    // Guardar PurchaseOrder en la base de datos
	    return purchaseOrderRepository.save(purchaseOrder);
	}
}

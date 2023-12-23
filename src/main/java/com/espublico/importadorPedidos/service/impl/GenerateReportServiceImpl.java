package com.espublico.importadorPedidos.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.espublico.importadorPedidos.model.PurchaseOrder;
import com.espublico.importadorPedidos.repository.PurchaseOrderRepository;
import com.espublico.importadorPedidos.service.GenerateReportService;

import jakarta.servlet.http.HttpServletResponse;

@Service("generateReportService")
public class GenerateReportServiceImpl implements GenerateReportService{
	
	@Autowired
	@Qualifier("purchaseOrderRepository")
	private PurchaseOrderRepository purchaseOrderRepository;

	@Override
	public void generateReportCsv(HttpServletResponse response,Long idHistory) throws IOException {
		// Configura la respuesta para descargar un archivo CSV
	    String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
	    String fileName = "informePedidos_" + currentDate + ".csv";
	    response.setContentType("text/csv");
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

	    List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findByHistoryOrderIdByOrderId(idHistory);
	    
	    // Crear un escritor para escribir la respuesta
	    try (CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT)) {
	        // Datos de cabecera
	        csvPrinter.printRecord("Order ID", "Order Priority", "Order Date", "Region", "Country", "Item Type", "Sales Channel", "Ship Date", "Units Sold", "Unit Price", "Unit Cost", "Total Revenue", "Total Cost", "Total Profit");
	        for (PurchaseOrder order : purchaseOrders) {
	        	csvPrinter.printRecord(order.getOrderId(),order.getOrderPriority(),order.getOrderDate(),order.getRegion(),order.getCountry(),order.getItemType(),order.getSalesChannel(),order.getShipDate(),order.getUnitsSold(),order.getUnitPrice(),order.getUnitCost(),order.getTotalRevenue(),order.getTotalCost(),order.getTotalProfit());
	        }
	    }
		
	}

}

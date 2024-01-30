package com.espublico.importadorPedidos.service.impl;

import com.espublico.importadorPedidos.model.PurchaseOrder;
import com.espublico.importadorPedidos.repository.IPurchaseOrderRepository;
import com.espublico.importadorPedidos.service.IGenerateReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio GenerateReportService. Esta clase se encarga de
 * generar informes en formato CSV basados en datos de órdenes de compra y
 * enviarlos a los clientes a través de una respuesta HTTP. Utiliza el
 * repositorio PurchaseOrderRepository para obtener los datos necesarios para el
 * informe.
 */
@Service("generateReportService")
public class GenerateReportServiceImpl implements IGenerateReportService {

	private static final Logger logger = LoggerFactory.getLogger(GenerateReportServiceImpl.class);
	
	@Autowired
	@Qualifier("purchaseOrderRepository")
	private IPurchaseOrderRepository purchaseOrderRepository;

	/**
	 * Genera un informe en formato CSV para un identificador de historial
	 * específico y lo envía a través de una respuesta HTTP. Configura la respuesta
	 * para permitir la descarga del informe como un archivo CSV. El informe incluye
	 * detalles de las órdenes de compra asociadas al historial especificado.
	 *
	 * @param response  La respuesta HTTP en la cual se escribirá el informe CSV.
	 * @param idHistory El identificador del historial de órdenes a partir del cual
	 *                  se generará el informe.
	 * @throws IOException Si ocurre un error al escribir en la respuesta HTTP.
	 */
	@Override
	public void generateReportCsv(HttpServletResponse response, Long idHistory) throws IOException {
		
		logger.info("Comienza la generación del informe");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		// Configura la respuesta para descargar un archivo CSV
		String currentDate = LocalDate.now().format(formatter);
		String fileName = "informePedidos_" + currentDate + ".csv";
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		// Crear un escritor para escribir la respuesta
		try (CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(), CSVFormat.DEFAULT)) {
			// Datos de cabecera
			csvPrinter.printRecord("Order ID", "Order Priority", "Order Date", "Region", "Country", "Item Type",
					"Sales Channel", "Ship Date", "Units Sold", "Unit Price", "Unit Cost", "Total Revenue",
					"Total Cost", "Total Profit");
			
			//Contenido del fichero
			Optional<List<PurchaseOrder>> purchaseOrdersOptional = purchaseOrderRepository.findByHistoryOrderIdOrderByOrderId(idHistory);
			if (purchaseOrdersOptional.isPresent()) {
				List<PurchaseOrder> purchaseOrders = purchaseOrdersOptional.get();
				
				for (PurchaseOrder order : purchaseOrders) {
					String formattedOrderDate = order.getOrderDate().format(formatter);
				    String formattedShipDate = order.getShipDate().format(formatter);
					
					csvPrinter.printRecord(order.getOrderId(), order.getOrderPriority(), formattedOrderDate,
							order.getRegion(), order.getCountry(), order.getItemType(), order.getSalesChannel(),
							formattedShipDate, order.getUnitsSold(), order.getUnitPrice(), order.getUnitCost(),
							order.getTotalRevenue(), order.getTotalCost(), order.getTotalProfit());
				}
			} else {
				System.out.println("Manejar cuando no lleguen ordenes de envio");
			}
			
		}
		logger.info("Finaliza la generación del informe");
	}

}

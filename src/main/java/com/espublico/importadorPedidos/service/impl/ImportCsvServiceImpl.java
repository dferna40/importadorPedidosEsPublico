package com.espublico.importadorPedidos.service.impl;

import com.espublico.importadorPedidos.dto.PurchaseOrderDTO;
import com.espublico.importadorPedidos.mapper.PurchaseOrderMapper;
import com.espublico.importadorPedidos.model.HistoryOrder;
import com.espublico.importadorPedidos.model.PurchaseOrder;
import com.espublico.importadorPedidos.model.User;
import com.espublico.importadorPedidos.repository.IHistoryOrderRepository;
import com.espublico.importadorPedidos.repository.IPurchaseOrderRepository;
import com.espublico.importadorPedidos.service.IImportCsvService;
import com.espublico.importadorPedidos.util.CsvDataValidator;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Servicio para importar datos de pedidos desde un archivo CSV y guardarlos en
 * la base de datos.
 *
 * Esta clase proporciona la funcionalidad para procesar archivos CSV,
 * transformar los datos en objetos DTO (Data Transfer Object) y luego convertir
 * estos DTO en entidades para guardarlos en la base de datos. Además, gestiona
 * la creación de registros de historial asociados a cada importación para un
 * seguimiento efectivo.
 */
@Service("importCsvService")
public class ImportCsvServiceImpl implements IImportCsvService {

	private static final Logger logger = LoggerFactory.getLogger(ImportCsvServiceImpl.class);

	@Autowired
	@Qualifier("purchaseOrderMapper")
	private PurchaseOrderMapper purchaseOrderMapper;

	@Autowired
	@Qualifier("purchaseOrderRepository")
	private IPurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	@Qualifier("historyOrderRepository")
	private IHistoryOrderRepository historyOrderRepository;

	List<String> errorMessages = new ArrayList<>();

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

	/**
	 * Procesa un archivo CSV línea por línea y guarda los pedidos en la base de
	 * datos.
	 *
	 * Este método lee un archivo CSV a través de un BufferedReader, analiza cada
	 * línea y transforma la información en objetos PurchaseOrderDTO. Cada objeto
	 * DTO se valida y luego se convierte en una entidad PurchaseOrder para su
	 * persistencia en la base de datos. Además, se asocia cada pedido con un
	 * registro único de historial (HistoryOrder) que representa la importación de
	 * este conjunto de pedidos.
	 *
	 * El método crea inicialmente un nuevo HistoryOrder con la fecha y hora
	 * actuales para marcar el momento de la importación. Luego, procesa cada línea
	 * del CSV, validando los campos y convirtiéndolos en PurchaseOrderDTO. Los DTO
	 * válidos se convierten en entidades PurchaseOrder, se les asigna el
	 * HistoryOrder común y se guardan en la base de datos. Los errores encontrados
	 * durante la validación se agregan a la lista 'errorMessages', que se retorna
	 * al final del proceso.
	 * 
	 * @param reader El BufferedReader que proporciona el contenido del archivo CSV.
	 * @return List<String> Una lista de mensajes de error que se generaron durante
	 *         el proceso de validación de los datos del CSV.
	 * @throws IOException Si ocurre un error de entrada/salida al leer el archivo
	 *                     CSV.
	 */
	public List<String> processCsvFile(BufferedReader  reader, User user) throws IOException {
		List<PurchaseOrder> purchaseOrders = new ArrayList<>();
		String line;

		logger.info("Comienza el procesamiento del CSV");
		// Crear un nuevo HistoryOrder para esta importación
		HistoryOrder newHistoryOrder = new HistoryOrder();
		// Asigna la fecha actual para cada historico
		newHistoryOrder.setChangeDate(LocalDateTime.now());

		newHistoryOrder.setUser(user);
		//Guarda primero un id en el histórico
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
				PurchaseOrder order = purchaseOrderMapper.toEntity(purchaseOrderDTO);
				order.setHistoryOrder(newHistoryOrder);
				order.setUser(user);
				// Guardar la entidad PurchaseOrder inmediatamente
				purchaseOrderRepository.save(order);
			}
		}

		logger.info("Guarda todos los registros en Base de datos");

		return errorMessages;
	}

	/**
	 * Guarda un pedido de compra (PurchaseOrder) en la base de datos con una
	 * asociación a un historial específico (HistoryOrder). Este método asegura que
	 * la asociación entre el pedido de compra y su historial correspondiente sea
	 * mantenida correctamente en la base de datos.
	 *
	 * @param purchaseOrder El pedido de compra que se va a guardar. Este objeto
	 *                      debe contener todos los datos relevantes del pedido que
	 *                      se desea almacenar.
	 * @param historyOrder  El historial asociado con el pedido de compra. Este
	 *                      historial representa un registro específico en la tabla
	 *                      de historial (order_history) al que se asocia el pedido
	 *                      de compra.
	 * @return PurchaseOrder El objeto PurchaseOrder después de ser guardado en la
	 *         base de datos. Esto incluye cualquier actualización que haya ocurrido
	 *         durante el proceso de guardado, como la generación del ID del pedido
	 *         de compra.
	 *
	 * @Transactional Asegura que la operación de guardado se maneje como una
	 *                transacción completa. Esto significa que si ocurre algún error
	 *                durante el proceso de guardado, la transacción se revertirá
	 *                para mantener la integridad de los datos en la base de datos.
	 */
	@Transactional
	public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder, HistoryOrder historyOrder) {
		// Asociar HistoryOrder con PurchaseOrder
		purchaseOrder.setHistoryOrder(historyOrder);
		// Guardar PurchaseOrder en la base de datos
		return purchaseOrderRepository.save(purchaseOrder);
	}

	/**
	 * Valida los valores de una línea específica del archivo CSV.
	 * <p>
	 * Este método verifica que cada campo de una línea del CSV cumpla con las
	 * validaciones específicas dependiendo de su tipo y contenido esperado. Se
	 * utilizan distintas validaciones para cadenas, fechas y números. Cada
	 * validación se realiza mediante el uso de métodos específicos de la clase
	 * CsvDataValidator.
	 *
	 * @param values Un array de String que contiene todos los campos de una línea
	 *              del CSV. Cada elemento del array representa un campo específico
	 *              del CSV.
	 * @return boolean Retorna 'true' si todos los campos de la línea son válidos,
	 * de lo contrario retorna 'false'.
	 * <p>
	 * El método actualiza la lista 'errorMessages' con mensajes
	 * descriptivos en caso de encontrar valores inválidos en alguno de los
	 * campos. Esta lista se utilizadara posteriormente para informar al
	 * usuario sobre los errores específicos encontrados durante el
	 * procesamiento del archivo CSV.
	 */
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

	@Override
	public Long getIdMaxHistoryOrder() {
		return historyOrderRepository.findMaxId();
	}
}

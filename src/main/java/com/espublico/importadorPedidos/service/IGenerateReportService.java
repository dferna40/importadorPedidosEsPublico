package com.espublico.importadorPedidos.service;

import com.espublico.importadorPedidos.model.User;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Interfaz de servicio para la generación de informes. Esta interfaz define una
 * operación para generar informes en formato CSV y enviarlos como respuesta
 * HTTP. Se puede implementar para crear informes basados en datos históricos
 * específicos, formatearlos adecuadamente, y manejar la escritura y transmisión
 * del informe generado a un cliente a través de una respuesta HTTP.
 */
public interface IGenerateReportService {

	/**
	 * Genera un informe en formato CSV basado en un identificador de historial y lo
	 * envía a través de una respuesta HTTP. Este método es responsable de crear un
	 * informe CSV utilizando los datos asociados con el identificador de historial
	 * proporcionado, y luego escribir este informe directamente en la respuesta
	 * HTTP para su descarga o visualización por parte del cliente.
	 *
	 * @param response  La respuesta HTTP en la cual se escribirá el informe CSV.
	 * @param idHistory El identificador del historial de órdenes a partir del cual
	 *                  se generará el informe.
	 * @throws IOException Si ocurre un error al escribir en la respuesta HTTP.
	 */
	void generateReportCsv(HttpServletResponse response, Long idHistory, Long idUser) throws IOException;
}

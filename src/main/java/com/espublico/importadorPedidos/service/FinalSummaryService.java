package com.espublico.importadorPedidos.service;

import com.espublico.importadorPedidos.dto.FinalSummaryDTO;

/**
 * Interfaz de servicio que define operaciones para generar resúmenes finales.
 * Esta interfaz encapsula la lógica de negocio necesaria para compilar y
 * estructurar un resumen final, basado en un identificador de historial
 * específico. Los métodos definidos aquí pueden ser implementados para
 * interactuar con diversas fuentes de datos y realizar cálculos o agregaciones
 * según sea necesario.
 */
public interface FinalSummaryService {

	/**
	 * Genera un resumen final basado en un identificador de historial. Este método
	 * es responsable de compilar información detallada y estadísticas relacionadas
	 * con un historial de órdenes, devolviendo un DTO que encapsula los resultados
	 * finales de dicho resumen.
	 *
	 * @param idHistory El identificador del historial de órdenes a partir del cual
	 *                  se generará el resumen.
	 * @return Un DTO que representa el resumen final compilado a partir del
	 *         historial de órdenes.
	 */
	FinalSummaryDTO resultFinalSummary(Long idHistory);
}

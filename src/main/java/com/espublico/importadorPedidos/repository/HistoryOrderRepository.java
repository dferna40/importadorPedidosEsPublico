package com.espublico.importadorPedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.HistoryOrder;

/**
 * Repositorio JPA para la entidad HistoryOrder. Esta interfaz extiende
 * JpaRepository, proporcionando operaciones CRUD estándar para la entidad
 * HistoryOrder, así como cualquier operación personalizada adicional. Está
 * marcada como un repositorio Spring, lo que la hace apta para la inyección de
 * dependencias y la gestión de operaciones de base de datos relacionadas con
 * HistoryOrder.
 */
@Repository("historyOrderRepository")
public interface HistoryOrderRepository extends JpaRepository<HistoryOrder, Long> {

	/**
	 * Encuentra el identificador máximo actual en la entidad HistoryOrder. Esta
	 * consulta personalizada retorna el valor máximo del identificador entre todas
	 * las instancias de HistoryOrder, lo que puede ser útil para operaciones que
	 * requieren conocer el último id registrado.
	 *
	 * @return El valor máximo del identificador (id) en la entidad HistoryOrder.
	 */
	@Query("SELECT MAX(h.id) FROM HistoryOrder h")
	Long findMaxId();
}

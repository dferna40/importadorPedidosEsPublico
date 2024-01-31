package com.espublico.importadorPedidos.service;

import com.espublico.importadorPedidos.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Interfaz de servicio para el procesamiento de archivos CSV y la gestión de
 * órdenes históricas. Esta interfaz define operaciones para el manejo y
 * análisis de archivos CSV, incluyendo la extracción y procesamiento de datos,
 * así como la recuperación de información histórica relacionada con órdenes.
 */
public interface IImportCsvService {

	/**
	 * Procesa un archivo CSV proporcionado a través de un BufferedReader. Este
	 * método se encarga de leer y analizar los datos del archivo CSV, realizando
	 * las operaciones necesarias para extraer la información relevante. Puede
	 * utilizarse para importar datos a la aplicación desde un archivo CSV.
	 *
	 * @param reader BufferedReader que representa el archivo CSV a procesar.
	 * @return Una lista de cadenas de texto que representan mensajes de error si se
	 *         encuentran durante el procesamiento.
	 * @throws IOException Si ocurre un error de entrada/salida durante el
	 *                     procesamiento del archivo.
	 */
	List<String> processCsvFile(BufferedReader reader, User user) throws IOException;

	/**
	 * Obtiene el identificador máximo de una orden histórica. Este método puede ser
	 * utilizado para recuperar el identificador más alto registrado en la base de
	 * datos para las órdenes históricas, lo cual es útil en diversas operaciones
	 * como asignar un nuevo identificador único.
	 *
	 * @return El valor máximo del identificador de una orden histórica.
	 */
	Long getIdMaxHistoryOrder();
}

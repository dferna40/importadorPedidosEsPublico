package com.espublico.importadorPedidos.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Clase CsvDataValidator
 * Esta clase proporciona métodos estáticos para validar los datos de un archivo CSV.
 * Se utiliza para verificar si los valores de las diferentes columnas son válidos
 * según el tipo de dato esperado (cadena, número, fecha).
 */
public class CsvDataValidator {

	/**
     * Verifica si una cadena de texto es válida.
     * 
     * @param value El valor de la cadena de texto a validar.
     * @param fieldName El nombre del campo para el mensaje de error.
     * @param errorMessages Lista donde se agregan los mensajes de error.
     * @return true si la cadena es válida (no nula y no vacía); false en caso contrario.
     */
	public static boolean isValidString(String value, String fieldName, List<String> errorMessages) {
        if (value == null || value.trim().isEmpty()) {
            errorMessages.add("El campo '" + fieldName + "' está vacío o es nulo.");
            return false;
        }
        return true;
    }

	/**
     * Verifica si un valor puede interpretarse como un número válido.
     * 
     * @param value El valor numérico en formato de cadena a validar.
     * @param fieldName El nombre del campo para el mensaje de error.
     * @param errorMessages Lista donde se agregan los mensajes de error.
     * @return true si el valor es un número válido; false en caso contrario.
     */
    public static boolean isValidNumber(String value, String fieldName, List<String> errorMessages) {
        if (value == null || value.trim().isEmpty()) {
            errorMessages.add("El valor numérico en el campo '" + fieldName + "' está vacío o es nulo.");
            return false;
        }
        try {
            Double.parseDouble(value.trim());
            return true;
        } catch (NumberFormatException e) {
            errorMessages.add("El valor numérico en el campo '" + fieldName + "' no es válido: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifica si un valor puede interpretarse como una fecha válida.
     * 
     * @param value El valor de la fecha en formato de cadena a validar.
     * @param formatter El formateador de fecha para interpretar el valor.
     * @param fieldName El nombre del campo para el mensaje de error.
     * @param errorMessages Lista donde se agregan los mensajes de error.
     * @return true si el valor es una fecha válida; false en caso contrario.
     */
    public static boolean isValidDate(String value, DateTimeFormatter formatter, String fieldName, List<String> errorMessages) {
        if (value == null || value.trim().isEmpty()) {
            errorMessages.add("La fecha en el campo '" + fieldName + "' está vacía o es nula.");
            return false;
        }
        try {
            LocalDate.parse(value.trim(), formatter);
            return true;
        } catch (DateTimeParseException e) {
            errorMessages.add("La fecha en el campo '" + fieldName + "' no es válida: " + e.getMessage());
            return false;
        }
    }
}

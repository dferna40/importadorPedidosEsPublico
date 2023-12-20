package com.espublico.importadorPedidos.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CsvDataValidator {

	public static boolean isValidString(String value, String fieldName, List<String> errorMessages) {
        if (value == null || value.trim().isEmpty()) {
            errorMessages.add("El campo '" + fieldName + "' está vacío o es nulo.");
            return false;
        }
        return true;
    }

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

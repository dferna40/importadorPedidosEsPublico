package com.espublico.importadorPedidos.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public interface ImportCsvService {

	List<String> processCsvFile(BufferedReader reader) throws IOException;
}

package com.espublico.importadorPedidos.service;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

public interface GenerateReportService {

	void generateReportCsv(HttpServletResponse response,Long idHistory) throws IOException;
}

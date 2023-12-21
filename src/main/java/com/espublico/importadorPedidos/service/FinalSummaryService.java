package com.espublico.importadorPedidos.service;

import com.espublico.importadorPedidos.dto.FinalSummaryDTO;

public interface FinalSummaryService {

	FinalSummaryDTO resultFinalSummary(Long idHistory);
}

package com.espublico.importadorPedidos.dto;

import java.time.LocalDate;

public class HistoryOrderDTO {

	private Long historyId;
	private LocalDate changeDate;

	// Constructor sin parametros Getters y setters

	public HistoryOrderDTO() {
		super();
	}

	public HistoryOrderDTO(Long historyId, Long orderId, LocalDate changeDate) {
		super();
		this.historyId = historyId;
		this.changeDate = changeDate;
	}

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public LocalDate getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(LocalDate changeDate) {
		this.changeDate = changeDate;
	}

}

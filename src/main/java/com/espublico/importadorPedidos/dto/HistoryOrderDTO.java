package com.espublico.importadorPedidos.dto;

import java.time.LocalDateTime;

public class HistoryOrderDTO {

	private Long historyId;
	private LocalDateTime changeDate;

	// Constructor sin parametros Getters y setters

	public HistoryOrderDTO() {
		super();
	}

	public HistoryOrderDTO(Long historyId, Long orderId, LocalDateTime changeDate) {
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

	public LocalDateTime getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(LocalDateTime changeDate) {
		this.changeDate = changeDate;
	}

}

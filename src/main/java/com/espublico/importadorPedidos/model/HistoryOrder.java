package com.espublico.importadorPedidos.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_history")
public class HistoryOrder {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

	@OneToMany(mappedBy = "historyOrder")
    private Set<PurchaseOrder> purchaseOrders;

    @Column(name = "change_date")
    private LocalDate changeDate;

    public HistoryOrder() {
        // Constructor vacío necesario para JPA
    }
    
    public HistoryOrder(Long historyId) {
    	this.historyId = historyId;
    }


    public HistoryOrder(Long historyId, Set<PurchaseOrder> purchaseOrders, LocalDate changeDate) {
		super();
		this.historyId = historyId;
		this.purchaseOrders = purchaseOrders;
		this.changeDate = changeDate;
	}

	// Getters y setters
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

	public Set<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

}

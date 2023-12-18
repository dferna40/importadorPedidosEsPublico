package com.espublico.importadorPedidos.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SalesChannel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesChannelId;

    private String channelName;

    @OneToMany(mappedBy = "salesChannel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Product> products;

    // Constructores, getters y setters

    public SalesChannel() {
        // Constructor vacío necesario para JPA
    }

    // Getters y setters
    public Long getSalesChannelId() {
        return salesChannelId;
    }

    public void setSalesChannelId(Long salesChannelId) {
        this.salesChannelId = salesChannelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}

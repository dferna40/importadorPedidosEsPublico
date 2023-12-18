package com.espublico.importadorPedidos.dto;

public class SalesChannelDTO {

	private Long salesChannelId;
    private String channelName;

    // Constructores, getters y setters

    public SalesChannelDTO() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public SalesChannelDTO(Long salesChannelId, String channelName) {
        this.salesChannelId = salesChannelId;
        this.channelName = channelName;
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
}

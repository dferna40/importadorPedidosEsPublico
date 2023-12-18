package com.espublico.importadorPedidos.dto;

public class ProductDTO {

	private Long productId;
    private String itemType;
    private Long salesChannelId;
    private String salesChannelName; // Ejemplo, si quieres incluir detalles del canal de ventas

    // Constructores, getters y setters

    public ProductDTO() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public ProductDTO(Long productId, String itemType, Long salesChannelId, String salesChannelName) {
        this.productId = productId;
        this.itemType = itemType;
        this.salesChannelId = salesChannelId;
        this.salesChannelName = salesChannelName;
    }

    // Getters y setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getSalesChannelId() {
        return salesChannelId;
    }

    public void setSalesChannelId(Long salesChannelId) {
        this.salesChannelId = salesChannelId;
    }

    public String getSalesChannelName() {
        return salesChannelName;
    }

    public void setSalesChannelName(String salesChannelName) {
        this.salesChannelName = salesChannelName;
    }
}

package com.espublico.importadorPedidos.dto;

public class CustomerDTO {

	private Long customerId;
    private String name;
    private Long countryId;
    private String countryName;
    private Long regionId;
    private String regionName;

    // Constructores, getters y setters

    public CustomerDTO() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public CustomerDTO(Long customerId, String name, Long countryId, String countryName, Long regionId, String regionName) {
        this.customerId = customerId;
        this.name = name;
        this.countryId = countryId;
        this.countryName = countryName;
        this.regionId = regionId;
        this.regionName = regionName;
    }

    // Getters y setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}

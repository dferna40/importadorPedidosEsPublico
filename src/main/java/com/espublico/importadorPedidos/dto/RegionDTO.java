package com.espublico.importadorPedidos.dto;

public class RegionDTO {

	private Long regionId;
    private String regionName;

    // Constructores, getters y setters

    public RegionDTO() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public RegionDTO(Long regionId, String regionName) {
        this.regionId = regionId;
        this.regionName = regionName;
    }

    // Getters y setters
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

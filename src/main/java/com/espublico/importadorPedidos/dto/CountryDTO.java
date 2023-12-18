package com.espublico.importadorPedidos.dto;

public class CountryDTO {

	private Long id;
    private String name;

    // Constructores, getters y setters

    public CountryDTO() {
        // Constructor vacío
    }

    public CountryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

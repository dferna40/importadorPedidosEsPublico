package com.espublico.importadorPedidos.mapper;

import org.springframework.stereotype.Component;

import com.espublico.importadorPedidos.dto.CountryDTO;
import com.espublico.importadorPedidos.model.Country;

@Component
public class CountryMapper {

	public CountryDTO toDto(Country country) {
        if (country == null) {
            return null;
        }

        CountryDTO dto = new CountryDTO();
        dto.setId(country.getCountryId());
        dto.setName(country.getCountryName());
        return dto;
    }

    public Country toEntity(CountryDTO countryDTO) {
        if (countryDTO == null) {
            return null;
        }

        Country country = new Country();
        country.setCountryId(countryDTO.getId());
        country.setCountryName(countryDTO.getName());
        return country;
    }
}

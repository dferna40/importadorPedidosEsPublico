package com.espublico.importadorPedidos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espublico.importadorPedidos.dto.CountryDTO;
import com.espublico.importadorPedidos.mapper.CountryMapper;
import com.espublico.importadorPedidos.model.Country;
import com.espublico.importadorPedidos.repository.CountryRepository;

@Service
public class CountryServiceImpl {

	@Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    public List<CountryDTO> findAllCountries() {
        List<CountryDTO> countryDTOs = new ArrayList<>();
        for (Country country : countryRepository.findAll()) {
            countryDTOs.add(countryMapper.toDto(country));
        }
        return countryDTOs;
    }

}

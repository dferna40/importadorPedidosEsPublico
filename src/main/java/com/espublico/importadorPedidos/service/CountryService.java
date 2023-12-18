package com.espublico.importadorPedidos.service;

import java.util.List;

import com.espublico.importadorPedidos.dto.CountryDTO;

public interface CountryService {

	 List<CountryDTO> findAllCountries();
}

package com.espublico.importadorPedidos.service;

import java.util.List;

import com.espublico.importadorPedidos.dto.RegionDTO;

public interface RegionService {

	 List<RegionDTO> findAllRegions();
}

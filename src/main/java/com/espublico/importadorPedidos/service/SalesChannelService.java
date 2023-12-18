package com.espublico.importadorPedidos.service;

import java.util.List;

import com.espublico.importadorPedidos.dto.SalesChannelDTO;

public interface SalesChannelService {

	 List<SalesChannelDTO> findAllSalesChannels();
}

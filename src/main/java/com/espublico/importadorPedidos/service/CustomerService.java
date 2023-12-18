package com.espublico.importadorPedidos.service;

import java.util.List;

import com.espublico.importadorPedidos.dto.CustomerDTO;

public interface CustomerService {

	 List<CustomerDTO> findAllCustomers();
}

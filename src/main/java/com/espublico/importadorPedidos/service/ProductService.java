package com.espublico.importadorPedidos.service;

import java.util.List;

import com.espublico.importadorPedidos.dto.ProductDTO;

public interface ProductService {

	 List<ProductDTO> findAllProducts();
}

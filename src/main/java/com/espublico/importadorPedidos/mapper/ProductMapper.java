package com.espublico.importadorPedidos.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.espublico.importadorPedidos.dto.ProductDTO;
import com.espublico.importadorPedidos.model.Product;

@Component
public class ProductMapper {

	public ProductDTO toDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setItemType(product.getItemType());

        if (product.getSalesChannel() != null) {
            dto.setSalesChannelId(product.getSalesChannel().getSalesChannelId());
            dto.setSalesChannelName(product.getSalesChannel().getChannelName());
        }
        // Agrega los campos restantes necesarios
        return dto;
    }

    public Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setItemType(productDTO.getItemType());

        // Aquí debes manejar la asignación de SalesChannel
        // Esto podría requerir buscar la entidad SalesChannel en la base de datos

        // Agrega los campos restantes necesarios
        return product;
    }

    public List<ProductDTO> toDtoList(List<Product> products) {
        if (products == null) {
            return null;
        }

        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(toDto(product));
        }
        return productDTOs;
    }

    public List<Product> toEntityList(List<ProductDTO> productDTOs) {
        if (productDTOs == null) {
            return null;
        }

        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : productDTOs) {
            products.add(toEntity(productDTO));
        }
        return products;
    }
}

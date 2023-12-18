package com.espublico.importadorPedidos.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.espublico.importadorPedidos.dto.CustomerDTO;
import com.espublico.importadorPedidos.model.Customer;

@Component
public class CustomerMapper {

	public CustomerDTO toDto(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(customer.getCustomerId());
        // Suponiendo que Customer tiene relaciones con Country y Region
        if (customer.getCountry() != null) {
            dto.setCountryId(customer.getCountry().getCountryId());
            dto.setCountryName(customer.getCountry().getCountryName());
        }
        if (customer.getRegion() != null) {
            dto.setRegionId(customer.getRegion().getRegionId());
            dto.setRegionName(customer.getRegion().getRegionName());
        }
        // Agrega los campos restantes necesarios
        return dto;
    }

    public Customer toEntity(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setCustomerId(customerDTO.getCustomerId());
        // Aquí debes manejar la asignación de Country y Region
        // Esto podría requerir buscar las entidades correspondientes en la base de datos

        // Agrega los campos restantes necesarios
        return customer;
    }

    public List<CustomerDTO> toDtoList(List<Customer> customers) {
        if (customers == null) {
            return null;
        }

        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOs.add(toDto(customer));
        }
        return customerDTOs;
    }

    public List<Customer> toEntityList(List<CustomerDTO> customerDTOs) {
        if (customerDTOs == null) {
            return null;
        }

        List<Customer> customers = new ArrayList<>();
        for (CustomerDTO customerDTO : customerDTOs) {
            customers.add(toEntity(customerDTO));
        }
        return customers;
    }
}

package com.espublico.importadorPedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

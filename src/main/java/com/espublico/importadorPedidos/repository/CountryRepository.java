package com.espublico.importadorPedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}

package com.espublico.importadorPedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}

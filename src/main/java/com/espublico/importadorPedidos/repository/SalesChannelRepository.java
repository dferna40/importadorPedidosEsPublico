package com.espublico.importadorPedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.espublico.importadorPedidos.model.SalesChannel;

@Repository
public interface SalesChannelRepository extends JpaRepository<SalesChannel, Long> {

}

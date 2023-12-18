package com.espublico.importadorPedidos.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.espublico.importadorPedidos.dto.RegionDTO;
import com.espublico.importadorPedidos.model.Region;

@Component
public class RegionMapper {

	public RegionDTO toDto(Region region) {
        if (region == null) {
            return null;
        }

        RegionDTO dto = new RegionDTO();
        dto.setRegionId(region.getRegionId());
        dto.setRegionName(region.getRegionName());
        // Agrega los campos restantes necesarios
        return dto;
    }

    public Region toEntity(RegionDTO regionDTO) {
        if (regionDTO == null) {
            return null;
        }

        Region region = new Region();
        region.setRegionId(regionDTO.getRegionId());
        region.setRegionName(regionDTO.getRegionName());
        // Agrega los campos restantes necesarios
        return region;
    }

    public List<RegionDTO> toDtoList(List<Region> regions) {
        if (regions == null) {
            return null;
        }

        List<RegionDTO> regionDTOs = new ArrayList<>();
        for (Region region : regions) {
            regionDTOs.add(toDto(region));
        }
        return regionDTOs;
    }

    public List<Region> toEntityList(List<RegionDTO> regionDTOs) {
        if (regionDTOs == null) {
            return null;
        }

        List<Region> regions = new ArrayList<>();
        for (RegionDTO regionDTO : regionDTOs) {
            regions.add(toEntity(regionDTO));
        }
        return regions;
    }
}

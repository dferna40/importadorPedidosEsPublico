package com.espublico.importadorPedidos.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.espublico.importadorPedidos.dto.SalesChannelDTO;
import com.espublico.importadorPedidos.model.SalesChannel;

@Component
public class SalesChannelMapper {

	public SalesChannelDTO toDto(SalesChannel salesChannel) {
        if (salesChannel == null) {
            return null;
        }

        SalesChannelDTO dto = new SalesChannelDTO();
        dto.setSalesChannelId(salesChannel.getSalesChannelId());
        dto.setChannelName(salesChannel.getChannelName());
        // Agrega los campos restantes necesarios
        return dto;
    }

    public SalesChannel toEntity(SalesChannelDTO salesChannelDTO) {
        if (salesChannelDTO == null) {
            return null;
        }

        SalesChannel salesChannel = new SalesChannel();
        salesChannel.setSalesChannelId(salesChannelDTO.getSalesChannelId());
        salesChannel.setChannelName(salesChannelDTO.getChannelName());
        // Agrega los campos restantes necesarios
        return salesChannel;
    }

    public List<SalesChannelDTO> toDtoList(List<SalesChannel> salesChannels) {
        if (salesChannels == null) {
            return null;
        }

        List<SalesChannelDTO> salesChannelDTOs = new ArrayList<>();
        for (SalesChannel salesChannel : salesChannels) {
            salesChannelDTOs.add(toDto(salesChannel));
        }
        return salesChannelDTOs;
    }

    public List<SalesChannel> toEntityList(List<SalesChannelDTO> salesChannelDTOs) {
        if (salesChannelDTOs == null) {
            return null;
        }

        List<SalesChannel> salesChannels = new ArrayList<>();
        for (SalesChannelDTO salesChannelDTO : salesChannelDTOs) {
            salesChannels.add(toEntity(salesChannelDTO));
        }
        return salesChannels;
    }
}

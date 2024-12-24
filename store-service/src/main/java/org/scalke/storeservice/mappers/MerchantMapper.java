package org.scalke.storeservice.mappers;

import org.mapstruct.Mapper;
import org.scalke.storeservice.dtos.MerchantDTO;
import org.scalke.storeservice.entities.Merchant;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MerchantMapper {
    MerchantDTO entityToDto(Merchant merchant);
    Merchant dtoToEntity(MerchantDTO merchantDTO);
    List<MerchantDTO> map(List<Merchant> merchants);
}

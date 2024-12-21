package org.example.addressservice.mappers;

import org.example.addressservice.dtos.AddressDTO;
import org.example.addressservice.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address dtoToEntity(AddressDTO addressDTO);
    @Mapping(source = "id", target = "id")
    AddressDTO entityToDto(Address address);
    List<AddressDTO> map(List<Address> addresses);
}

package org.example.addressservice.services;

import org.example.addressservice.dtos.AddressDTO;
import org.example.addressservice.entities.Address;
import org.example.addressservice.web.request.AddAddressRequest;
import org.example.helpers.PageElement;

import java.util.List;

public interface AddressService {
    AddressDTO findAddressByID(long id);
    Address findAddress(long id);
    PageElement<AddressDTO> findAddresses(int page, int size);
    List<AddressDTO> findAll();
    AddressDTO addNewAddress(AddressDTO request);
    void deleteAddress(long id);
}

package org.example.addressservice.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.addressservice.dtos.AddressDTO;
import org.example.addressservice.entities.Address;
import org.example.addressservice.mappers.AddressMapper;
import org.example.addressservice.repositories.AddressRepository;
import org.example.addressservice.web.request.AddAddressRequest;
import org.example.helpers.PageElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private AddressMapper addressMapper;

    @Override
    public AddressDTO findAddressByID(long id) {
        return addressMapper.entityToDto(findAddress(id));
    }

    @Override
    public Address findAddress(long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
    }

    @Override
    public PageElement<AddressDTO> findAddresses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Address> addresses = addressRepository.findAll(pageable);
        PageElement<AddressDTO> p = new PageElement<AddressDTO>();
        p.setElements(addressMapper.map(addresses.getContent()));
        p.setTotalElements(addresses.getTotalElements());
        p.setTotalPages(addresses.getTotalPages());
        p.setSize(size);
        p.setCurrentPage(page);

        return p;
    }

    @Override
    public List<AddressDTO> findAll() {
        return addressMapper.map(addressRepository.findAll());
    }

    @Override
    public AddressDTO addNewAddress(AddressDTO request) {
        Address address = addressRepository.save(
                Address.builder()
                        .name(request.getName())
                        .build()
        );
        return addressMapper.entityToDto(address);
    }

    @Override
    public void deleteAddress(long id) {
        addressRepository.deleteById(id);
    }
}

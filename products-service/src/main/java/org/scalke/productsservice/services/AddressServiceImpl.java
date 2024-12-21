package org.scalke.productsservice.services;

//package org.example.addressservice.services;
//
//import jakarta.transaction.Transactional;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.example.addressservice.entities.Address;
//import org.example.helpers.PageElement;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Transactional
//@AllArgsConstructor
//@Slf4j
public class AddressServiceImpl {
//
//    private org.example.addressservice.repositories.ProductRepository addressRepository;
//    private org.example.addressservice.mappers.ProductMapperMapper addressMapper;
//
//    @Override
//    public org.example.addressservice.dtos.ProductDTO findAddressByID(long id) {
//        return addressMapper.entityToDto(findAddress(id));
//    }
//
//    @Override
//    public Address findAddress(long id) {
//        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
//    }
//
//    @Override
//    public PageElement<org.example.addressservice.dtos.ProductDTO> findAddresses(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Address> addresses = addressRepository.findAll(pageable);
//        PageElement<org.example.addressservice.dtos.ProductDTO> p = new PageElement<org.example.addressservice.dtos.ProductDTO>();
//        p.setElements(addressMapper.map(addresses.getContent()));
//        p.setTotalElements(addresses.getTotalElements());
//        p.setTotalPages(addresses.getTotalPages());
//        p.setSize(size);
//        p.setCurrentPage(page);
//
//        return p;
//    }
//
//    @Override
//    public List<org.example.addressservice.dtos.ProductDTO> findAll() {
//        return addressMapper.map(addressRepository.findAll());
//    }
//
//    @Override
//    public org.example.addressservice.dtos.ProductDTO addNewAddress(org.example.addressservice.dtos.ProductDTO request) {
//        Address address = addressRepository.save(
//                Address.builder()
//                        .name(request.getName())
//                        .build()
//        );
//        return addressMapper.entityToDto(address);
//    }
//
//    @Override
//    public void deleteAddress(long id) {
//        addressRepository.deleteById(id);
//    }
}

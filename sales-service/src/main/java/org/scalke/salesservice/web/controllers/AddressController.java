package org.example.addressservice.web.controllers;

import lombok.AllArgsConstructor;
import org.example.addressservice.dtos.AddressDTO;
import org.example.addressservice.services.AddressService;
import org.example.addressservice.web.request.AddAddressRequest;
import org.example.helpers.PageElement;
import org.example.models.Address;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@AllArgsConstructor
public class AddressController {

    private AddressService addressService;

    @GetMapping("")
    PageElement<AddressDTO> getAddress(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return addressService.findAddresses(page, size);
    }

    @GetMapping("/{id}")
    AddressDTO getAddressById(@PathVariable long id) {
        return addressService.findAddressByID(id);
    }

    @PostMapping("")
    AddressDTO createAddress(@RequestBody AddressDTO request) {
        return addressService.addNewAddress(request);
    }

    @DeleteMapping("/{id}")
    void deleteAddressById(@PathVariable long id) {
         addressService.deleteAddress(id);
    }
}

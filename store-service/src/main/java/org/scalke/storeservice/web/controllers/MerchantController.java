package org.scalke.storeservice.web.controllers;

import jakarta.validation.Valid;
import org.scalke.storeservice.dtos.MerchantDTO;
import org.scalke.storeservice.entities.Merchant;
import org.scalke.storeservice.exceptions.MerchantNotFoundException;
import org.scalke.storeservice.exceptions.MerchnatServiceLogicException;
import org.scalke.storeservice.web.requests.AddNewMerchantRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/store/merchants")
public interface MerchantController {

    @GetMapping("")
    ResponseEntity<Page<Merchant>> getMerchants(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
                                                @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "true") boolean ascending) throws MerchnatServiceLogicException;

    @GetMapping("/find/{id}")
    ResponseEntity<Merchant> getMerchantById(@PathVariable Long id) throws MerchantNotFoundException, MerchnatServiceLogicException;

    @GetMapping("/exist/{id}")
    Boolean checkIfMerchantExistWithId(@PathVariable Long id) throws MerchantNotFoundException, MerchnatServiceLogicException;

    @PostMapping("")
    ResponseEntity<MerchantDTO> addNewMerchant(@Valid @RequestBody AddNewMerchantRequest request) throws MerchnatServiceLogicException;
}

package org.scalke.storeservice.web.controllers;

import org.scalke.storeservice.entities.Merchant;
import org.scalke.storeservice.exceptions.MerchantNotFoundException;
import org.scalke.storeservice.exceptions.MerchnatServiceLogicException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/store/merchants")
public interface MerchantController {

    @GetMapping("")
    ResponseEntity<Page<Merchant>> getMerchants(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
                                                @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "true") boolean ascending) throws MerchnatServiceLogicException;

    @GetMapping("/{id}")
    ResponseEntity<Merchant> getMerchantById(@PathVariable Long id) throws MerchantNotFoundException, MerchnatServiceLogicException;

    @GetMapping("/exist/{id}")
    Boolean checkIfMerchantExistWithId(@PathVariable Long id) throws MerchantNotFoundException, MerchnatServiceLogicException;
}

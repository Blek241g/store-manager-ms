package org.scalke.storeservice.web.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scalke.storeservice.entities.Merchant;
import org.scalke.storeservice.exceptions.MerchantNotFoundException;
import org.scalke.storeservice.exceptions.MerchnatServiceLogicException;
import org.scalke.storeservice.services.MerchantService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class MerchantControllerImpl implements MerchantController {
    private MerchantService merchantService;

    @Override
    public ResponseEntity<Page<Merchant>> getMerchants(int page, int size, String sortBy, boolean ascending) throws MerchnatServiceLogicException {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

       return ResponseEntity.ok(merchantService.getMerchants(pageable));
    }

    @Override
    public ResponseEntity<Merchant> getMerchantById(Long id) throws MerchantNotFoundException, MerchnatServiceLogicException {
        return ResponseEntity.status(HttpStatus.FOUND).body(merchantService.getMerchantById(id));
    }

    @Override
    public Boolean checkIfMerchantExistWithId(Long id) throws MerchantNotFoundException, MerchnatServiceLogicException {
        return merchantService.checkIfMerchantExistWithId(id);
    }
}

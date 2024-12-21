package org.scalke.storeservice.services;

import org.scalke.storeservice.entities.Merchant;
import org.scalke.storeservice.exceptions.MerchantNotFoundException;
import org.scalke.storeservice.exceptions.MerchnatServiceLogicException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MerchantService {
    Page<Merchant> getMerchants(Pageable pageable) throws MerchnatServiceLogicException;

    Merchant getMerchantById(Long id) throws MerchantNotFoundException, MerchnatServiceLogicException;

    Boolean checkIfMerchantExistWithId(Long id) throws MerchnatServiceLogicException;
}

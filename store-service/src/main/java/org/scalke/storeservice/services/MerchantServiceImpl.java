package org.scalke.storeservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scalke.storeservice.constants.AppMessage;
import org.scalke.storeservice.dtos.MerchantDTO;
import org.scalke.storeservice.entities.Merchant;
import org.scalke.storeservice.exceptions.MerchantNotFoundException;
import org.scalke.storeservice.exceptions.MerchnatServiceLogicException;
import org.scalke.storeservice.mappers.MerchantMapper;
import org.scalke.storeservice.repositories.MerchantRepository;
import org.scalke.storeservice.web.requests.AddNewMerchantRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private MerchantRepository merchantRepository;
    private MerchantMapper merchantMapper;

    @Override
    public Page<Merchant> getMerchants(Pageable pageable) throws MerchnatServiceLogicException {
        try {
            /*
             Lorsqu'on va recuperer les commerçant, on va completer les information spersonnelles
             de ce dernier, vu qu'elle ne change pas tout le temps, on va les decoupler dans la base de donnees, faudra
             aussi verifier la methode update, il faut aussi le userID
            * */
                return merchantRepository.findAll(pageable);
        }catch (Exception e) {
            log.error("Failed to fetch merchants: " + e.getMessage());
            throw new MerchnatServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Merchant getMerchantById(Long id) throws MerchantNotFoundException, MerchnatServiceLogicException {
        try {
            return merchantRepository.findById(id).orElseThrow(() -> new MerchantNotFoundException("Merchant not found with id " + id));
        }catch (MerchantNotFoundException e) {
            log.error("Merchant not found with id " + id);
            throw e;
        }
        catch (Exception e) {
            log.error("Failed to fetch merchant by ID: " + e.getMessage());
            throw new MerchnatServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean checkIfMerchantExistWithId(Long id) throws MerchnatServiceLogicException {
        try {
            return merchantRepository.existsMerchantById(id);
        }
        catch (Exception e) {
            log.error("Failed to check if exists merchant by ID: " + e.getMessage());
            throw new MerchnatServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MerchantDTO addNewMerchant(AddNewMerchantRequest request) throws MerchnatServiceLogicException {
        try {
            return merchantMapper.entityToDto(
                    merchantRepository.save(Merchant.builder()
                                    .userId(request.getUserId())
                                    .email(request.getEmail())
                                    .lastname(request.getLastname())
                                    .firstname(request.getFirstname())
                                    .phoneNumber(request.getPhoneNumber())
                                    .username(request.getUsername())
                            .build())
            );
        }catch (Exception e) {
            log.error("Failed to add new merchant: " + e.getMessage());
            throw new MerchnatServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

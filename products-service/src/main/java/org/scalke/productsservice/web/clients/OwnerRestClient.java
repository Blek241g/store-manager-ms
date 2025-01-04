package org.scalke.productsservice.web.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.scalke.productsservice.exceptions.ProductServiceLogicException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "STORE-SERVICE")
public interface OwnerRestClient {

    @CircuitBreaker(name = "storeMerchantService", fallbackMethod = "handleCheckIfMerchantExistWithId")
    @GetMapping("/api/store/merchants/exist/{id}")
    Boolean checkIfMerchantExistWithId(@PathVariable Long id);

    default Boolean handleCheckIfMerchantExistWithId(Long id, Exception e) throws ProductServiceLogicException {
        System.out.println("Error while find owner in feign client");
//        throw new ProductServiceLogicException("Error while find owner id: "+id+" to Store Service", HttpStatus.valueOf(503));
        return false;
    };
}

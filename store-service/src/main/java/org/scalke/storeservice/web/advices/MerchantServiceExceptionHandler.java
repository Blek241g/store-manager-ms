package org.scalke.storeservice.web.advices;

import org.scalke.storeservice.exceptions.MerchantNotFoundException;
import org.scalke.storeservice.exceptions.MerchnatServiceLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MerchantServiceExceptionHandler {
    @ExceptionHandler(MerchnatServiceLogicException.class)
    public ResponseEntity<?> handleProductServiceLogicException(MerchnatServiceLogicException e) {
        return ResponseEntity.status(e.status).body(e.getMessage());
    }

    @ExceptionHandler(MerchantNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(MerchantNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

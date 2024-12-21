package org.scalke.productsservice.web.advices;

import org.scalke.productsservice.exceptions.ProductNotFoundException;
import org.scalke.productsservice.exceptions.ProductServiceLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductServiceExceptionHandler {
    @ExceptionHandler(ProductServiceLogicException.class)
    public ResponseEntity<?> handleProductServiceLogicException(ProductServiceLogicException e) {
        return ResponseEntity.status(e.status).body(e.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

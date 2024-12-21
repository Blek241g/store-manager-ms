package org.scalke.productsservice.exceptions;

import org.springframework.http.HttpStatus;

public class ProductServiceLogicException extends Exception {
    public HttpStatus status;
    public ProductServiceLogicException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

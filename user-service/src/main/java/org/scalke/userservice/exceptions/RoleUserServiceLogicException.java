package org.scalke.userservice.exceptions;

import org.springframework.http.HttpStatus;

public class RoleUserServiceLogicException extends GeneraleLogicException{
    public RoleUserServiceLogicException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

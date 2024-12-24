package org.scalke.userservice.exceptions;

import org.springframework.http.HttpStatus;

public class PermissionRoleServiceLogicException extends GeneraleLogicException {

    public PermissionRoleServiceLogicException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}

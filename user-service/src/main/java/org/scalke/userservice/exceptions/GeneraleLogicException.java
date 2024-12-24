package org.scalke.userservice.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class GeneraleLogicException extends Exception{
    public HttpStatus status;

    public GeneraleLogicException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

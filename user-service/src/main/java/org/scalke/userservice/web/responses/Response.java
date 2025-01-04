package org.scalke.userservice.web.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public abstract class Response {
    protected String message;
    protected HttpStatus status;
}


package org.scalke.userservice.web.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Builder
public class LoginResponse {
    private String token;
    private List<String> privileges;
    private String message;
}

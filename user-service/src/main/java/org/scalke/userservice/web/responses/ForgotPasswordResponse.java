package org.scalke.userservice.web.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ForgotPasswordResponse extends Response{
    private String tempPassword;
}

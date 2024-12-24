package org.scalke.storeservice.web.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AddNewMerchantRequest {
    private String username;
    private String lastname;
    private String firstname;
    @Email
    private String email;
    private String phoneNumber;
    @NotNull
    private Long userId;
}

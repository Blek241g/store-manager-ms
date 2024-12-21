package org.scalke.storeservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MerchantDTO {
    private Long id;
    private String username;
    private String lastname;
    private String firstname;
    private String email;
    private String phoneNumber;
    private Long userId;

}

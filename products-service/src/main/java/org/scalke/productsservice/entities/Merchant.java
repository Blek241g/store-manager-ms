package org.scalke.productsservice.entities;

import lombok.*;

import java.io.Serializable;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor @Builder
public class Merchant implements Serializable {
    private Long id;
    private Long userId;
    private String username;
    private String lastname;
    private String firstname;
    private String email;
    private String phoneNumber;
}

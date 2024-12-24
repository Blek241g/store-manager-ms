package org.scalke.storeservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.scalke.models.User;

import java.io.Serializable;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor @Builder
@Entity
@Table(name = "merchants")
public class Merchant implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull
    private Long userId;
    private String username;
    private String lastname;
    private String firstname;
    @Column(unique = true)
    @NotNull
    @Email
    private String email;
    @Column(unique = true)
    @NotNull
    private String phoneNumber;
}

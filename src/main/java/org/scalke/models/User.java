package org.scalke.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class User {
    private long id;
    private String username;
    private String lastname;
    private String firstname;
    private String email;
    private String phoneNumber;
}

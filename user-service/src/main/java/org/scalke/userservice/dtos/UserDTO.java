package org.scalke.userservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.scalke.userservice.constants.UserType;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class UserDTO {
    private Long id;
    private String username;
    private String lastname;
    private String firstname;
    private String phoneNumber;
    private String email;
    private UserType userType;
    private Boolean isActive;
}

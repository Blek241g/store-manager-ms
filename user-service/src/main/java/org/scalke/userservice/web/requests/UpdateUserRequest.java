package org.scalke.userservice.web.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.scalke.userservice.constants.UserType;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @NotNull
    private Long userId;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String lastname;
    @NotNull
    private String firstname;
    @NotNull
    private String phoneNumber;
    @NotNull
    private UserType userType;
    private String avatar;
}

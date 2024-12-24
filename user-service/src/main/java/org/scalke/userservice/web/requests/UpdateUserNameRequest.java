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
public class UpdateUserNameRequest extends UpdateUser{ ;
    @NotNull
    private String lastname;
    @NotNull
    private String firstname;
}

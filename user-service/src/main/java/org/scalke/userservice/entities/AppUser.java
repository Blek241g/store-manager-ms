package org.scalke.userservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.scalke.userservice.constants.UserType;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
@Entity
@Table(name = "users")
@DynamicUpdate @DynamicInsert
public class AppUser extends RepresentationModel<AppUser> {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    @Column(unique = true)
    @Email

    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private String password;
    @Column(unique = true)
    private String username;
    private String lastname;
    private String firstname;
    private String phoneNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_users",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"
            )
    )
    private Collection<AppRole> roles = new ArrayList<AppRole>();
    //Never delete user
    private Boolean isActive;
    private String avatar;
    @Getter(AccessLevel.NONE)
    private Long createdBy;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String rememberToken;
    private LocalDateTime emailVerifiedAt;
    private LocalDateTime phoneNumberVerifiedAt;
    private Boolean deleted = false;


    public Long getCreatedBy(){
        return userType == UserType.ADMIN ? getId() : createdBy;
    }
}

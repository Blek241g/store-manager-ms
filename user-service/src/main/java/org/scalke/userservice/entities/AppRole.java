package org.scalke.userservice.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
@Entity
@Table(name = "roles")
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            unique = true
    )
    @NotNull
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "permissions_roles",
        joinColumns = @JoinColumn(
                name = "role_id", referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "permission_id", referencedColumnName = "id"
        )
    )
    private Collection<AppPermission> permissions = new ArrayList<AppPermission>();

}

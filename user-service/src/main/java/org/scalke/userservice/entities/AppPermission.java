package org.scalke.userservice.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
@Entity
@Table(name = "permissions")
public class AppPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(unique = true)
    private String name;
}

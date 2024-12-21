package org.scalke.productsservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.scalke.productsservice.constants.ProductState;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor @Builder
@Entity
@Table(name = "products", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ref", "ownerId", "designation"})
})
public class Product implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // private SubCategory subCategory;
    @NotNull
    private LocalDateTime createdAt;
    private Boolean deleted;
    private String description;
    @NotNull
    private String designation;
    private String photo;
    @NotNull
    @Size(min = 4, max = 15)
    @Column(unique = true)
    private String ref;
    private ProductState state;
    @NotNull
    private Double uPrice;

    //Sera place dans les achats
    private Double vat;
    @NotNull
    private Long ownerId;
}

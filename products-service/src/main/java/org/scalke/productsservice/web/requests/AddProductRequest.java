package org.scalke.productsservice.web.requests;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.scalke.productsservice.constants.ProductState;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductRequest {
    private String description;
    @NotNull
    private String designation;
    private String photo;
    @NotNull
    @Size(min = 4, max = 15)
    private String ref;
    @Enumerated(EnumType.ORDINAL)
    private ProductState state;
    @NotNull
    private Double uprice;
    private Double vat;
    @NotNull
    private Long ownerId;
}

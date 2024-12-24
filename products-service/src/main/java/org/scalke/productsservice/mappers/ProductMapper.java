package org.scalke.productsservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.scalke.productsservice.dtos.ProductDTO;
import org.scalke.productsservice.entities.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product dtoToEntity(ProductDTO productDTO);
    ProductDTO entityToDto(Product product);
    List<ProductDTO> map(List<Product> Product);
}

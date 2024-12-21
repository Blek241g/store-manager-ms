package org.scalke.productsservice.services;

import org.scalke.productsservice.dtos.ProductDTO;
import org.scalke.productsservice.entities.Product;
import org.scalke.productsservice.exceptions.ProductNotFoundException;
import org.scalke.productsservice.exceptions.ProductServiceLogicException;
import org.scalke.productsservice.web.requests.AddProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProductService {
    Page<ProductDTO> getProductByOwnerId(long ownerId, Pageable pageable) throws ProductServiceLogicException;
    int getTotalProductsByOwnerId(long ownerId) throws ProductServiceLogicException;

    ProductDTO addNewProduct(AddProductRequest request) throws ProductServiceLogicException;

    Product getProductById(Long id) throws ProductServiceLogicException, ProductNotFoundException;

    Product getProductByRef(long ownerId, String ref) throws ProductServiceLogicException, ProductNotFoundException;

    Product getProductByDesignation(long ownerId, String designation) throws ProductNotFoundException, ProductServiceLogicException;

    void deleteProductById(Long id);
}

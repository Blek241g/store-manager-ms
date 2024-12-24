package org.scalke.productsservice.web.controllers;

import lombok.AllArgsConstructor;
import org.scalke.productsservice.dtos.ProductDTO;
import org.scalke.productsservice.entities.Product;
import org.scalke.productsservice.exceptions.ProductNotFoundException;
import org.scalke.productsservice.exceptions.ProductServiceLogicException;
import org.scalke.productsservice.services.ProductService;
import org.scalke.productsservice.web.requests.AddProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
public class ProductControllerImpl implements ProductController {

    private ProductService productService;


    // Get Methods
    @Override
    public ResponseEntity<Page<ProductDTO>> getProductsByOwnerId(int page, int size, String sortBy, boolean ascending, Long ownerId) throws ProductServiceLogicException {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);


        return ResponseEntity.ok(productService.getProductByOwnerId(ownerId, pageable));
    }

    @Override
    public ResponseEntity<Product> getProductById(Long id) throws ProductServiceLogicException, ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductById(id));
    }

    @Override
    public ResponseEntity<Product> getProductByRef(String ref, long ownerId) throws ProductServiceLogicException, ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductByRef(ownerId, ref));
    }

    @Override
    public ResponseEntity<Product> getProductByDesignation(String des, long ownerId) throws ProductServiceLogicException, ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.FOUND).body(productService.getProductByDesignation(ownerId, des));
    }

    @Override
    public ResponseEntity<?> deleteProductById(Long id) throws ProductServiceLogicException, ProductNotFoundException {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    // End Get Methods

    @Override
    public ResponseEntity<ProductDTO> addNewProduct(Long ownerId, AddProductRequest request) throws ProductServiceLogicException {
        ProductDTO p =  productService.addNewProduct(request);
        URI productLocation = MvcUriComponentsBuilder.fromController(getClass()).path("/id/{id}").buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(productLocation).body(p);
    }

}

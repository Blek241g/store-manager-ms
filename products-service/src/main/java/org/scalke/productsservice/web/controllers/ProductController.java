package org.scalke.productsservice.web.controllers;

import jakarta.validation.Valid;
import org.scalke.productsservice.dtos.ProductDTO;
import org.scalke.productsservice.entities.Product;
import org.scalke.productsservice.exceptions.ProductNotFoundException;
import org.scalke.productsservice.exceptions.ProductServiceLogicException;
import org.scalke.productsservice.web.requests.AddProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/products")
public interface ProductController {

    @PostMapping("/owner/{ownerId}")
    ResponseEntity<ProductDTO> addNewProduct(@PathVariable Long ownerId, @Valid @RequestBody AddProductRequest request) throws ProductServiceLogicException;

    //Get Methods
    @GetMapping("/owner/{ownerId}")
    ResponseEntity<Page<ProductDTO>> getProductsByOwnerId(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
                                                       @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "true") boolean ascending,
                                                       @PathVariable Long ownerId) throws ProductServiceLogicException;

    @GetMapping("/id/{id}")
    ResponseEntity<Product> getProductById(@PathVariable(required = true) Long id) throws ProductServiceLogicException, ProductNotFoundException;

    @GetMapping("/reference")
    ResponseEntity<Product> getProductByRef(@RequestParam(required = true) String ref,
                                            @RequestParam(required = true) long ownerId) throws ProductServiceLogicException, ProductNotFoundException;

    @GetMapping("/designation")
    ResponseEntity<Product> getProductByDesignation(@RequestParam(required = true) String des,
                                                    @RequestParam(required = true) long ownerId) throws ProductServiceLogicException, ProductNotFoundException;
    //End Get Methods

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProductById(@PathVariable Long id) throws ProductServiceLogicException, ProductNotFoundException;
}

package org.scalke.productsservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.NotImplementedException;
import org.scalke.productsservice.constants.AppMessage;
import org.scalke.productsservice.dtos.ProductDTO;
import org.scalke.productsservice.entities.Product;
import org.scalke.productsservice.exceptions.ProductNotFoundException;
import org.scalke.productsservice.exceptions.ProductServiceLogicException;
import org.scalke.productsservice.mappers.ProductMapper;
import org.scalke.productsservice.repositories.ProductRepository;
import org.scalke.productsservice.web.clients.OwnerRestClient;
import org.scalke.productsservice.web.requests.AddProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Transactional
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private ProductMapper productMapper;
    private OwnerRestClient ownerRestClient;

    @Override
    public Page<ProductDTO> getProductByOwnerId(long ownerId, Pageable pageable) throws ProductServiceLogicException {
        try {
            return productRepository.findAllByOwnerIdAndDeleted(ownerId, false, pageable).map(p -> productMapper.entityToDto(p));

            // Exemple to manage response with logic
//            if (produits.isEmpty()) {
//                throw new RuntimeException("Aucun produit trouvé"); // Exception personnalisée recommandée
//            }
//
//            return produitMapper.toDtoList(produits);

        }catch (Exception e) {
            log.error("Failed to fetch all products by owner: " + e.getMessage());
            throw new ProductServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ProductDTO addNewProduct(AddProductRequest request) throws ProductServiceLogicException {
        try {
            if(checkOwnerId(request.getOwnerId())){
                return productMapper.entityToDto(
                        productRepository.save(Product.builder()
                                        .createdAt(LocalDateTime.now())
                                        .deleted(false)
                                        .designation(request.getDesignation())
                                        .description(request.getDescription())
                                        .photo(request.getPhoto())
                                        .ref(request.getRef())
                                        .state(request.getState())
                                        .uPrice(request.getUprice())
                                        .vat(request.getVat())
                                        .ownerId(request.getOwnerId())
                                        .build())
                );
            }else{
                throw new ProductServiceLogicException("Owner not found", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Failed to add new Product: " + e.getMessage());
            throw new ProductServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Product getProductById(Long id) throws ProductServiceLogicException, ProductNotFoundException {
        try {
            return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));
        }catch (ProductNotFoundException e){
            log.error("Product not found by id: " + e.getMessage());
            throw e;
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to fetch product by id: " + e.getMessage());
            throw new ProductServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Product getProductByRef(long ownerId, String ref) throws ProductServiceLogicException, ProductNotFoundException {
        try {
            Product product = productRepository.findProductByOwnerIdAndRef(ownerId, ref);
            if(Objects.isNull(product)){
                throw new ProductNotFoundException("Product not found with ref: " + ref + "and ownerId :"+ownerId);
            }
            return product;
        }catch (ProductNotFoundException e){
            log.error("Product not found by ref: " + e.getMessage());
            throw e;
        }
        catch (Exception e){
            log.error("Failed to fetch product by ref: " + e.getMessage());
            throw new ProductServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Product getProductByDesignation(long ownerId, String designation) throws ProductNotFoundException, ProductServiceLogicException {
        try {
            Product product = productRepository.findProductByOwnerIdAndDesignation(ownerId, designation);
            if(Objects.isNull(product)){
                throw new ProductNotFoundException("Product not found with designation: " + designation);
            }
            return product;
        }catch (ProductNotFoundException e){
            log.error("Product not found by designation: " + e.getMessage());
            throw e;
        }
        catch (Exception e){
            log.error("Failed to fetch product by designation: " + e.getMessage());
            throw new ProductServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteProductById(Long id) {
        throw new NotImplementedException("Delete product is not implemented yet");
    }

    @Override
    public int getTotalProductsByOwnerId(long ownerId) throws ProductServiceLogicException {
        try {
            return productRepository.countProductsByOwner(ownerId);
        }catch (Exception e){
            log.error("Failed to fetch total of products by owner: " + e.getMessage());
            throw new ProductServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean checkOwnerId(Long ownerId) throws ProductServiceLogicException {
        if(getTotalProductsByOwnerId(ownerId) > 0)
            return true;
        else
            return checkOwnerToStoreService(ownerId);
    }

    private boolean checkOwnerToStoreService(Long ownerId) throws ProductServiceLogicException {
        try {
            log.info("Checking owner to store service: {}", ownerId);
            return ownerRestClient.checkIfMerchantExistWithId(ownerId);
        }
        catch (Exception e) {
            log.error("Failed to fetch merchant by ID: " + e.getMessage());
            throw new ProductServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

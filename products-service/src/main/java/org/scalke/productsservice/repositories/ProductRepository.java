package org.scalke.productsservice.repositories;


import org.scalke.productsservice.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("select p from Product p where p.ownerId=:id and p.deleted=false ")
//    List<Product> findProductsByOwnerId(Long id);

    Page<Product> findAllByOwnerId(Long id, Pageable pageable);
    Page<Product> findAllByOwnerIdAndDeleted(Long id, Boolean deleted, Pageable pageable);

    Product findProductByOwnerIdAndRef(Long id, String ref);
    Product findProductByOwnerIdAndDesignation(Long id, String designation);

    @Query("select count(p) from Product p where p.ownerId = :ownerId")
    int countProductsByOwner(long ownerId);
}

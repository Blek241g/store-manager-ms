package org.scalke.storeservice.repositories;


import org.scalke.storeservice.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    boolean existsMerchantById(Long id);
}

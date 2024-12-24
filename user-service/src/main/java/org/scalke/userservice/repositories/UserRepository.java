package org.scalke.userservice.repositories;

import com.github.javafaker.App;
import org.scalke.userservice.entities.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findAppUsersByUsername(String username);
    AppUser findAppUsersByEmail(String email);
    AppUser findFirstByEmail(String email);
    AppUser findFirstByUsername(String username);
    Page<AppUser> findAppUserByCreatedBy(Long id, Pageable pageable);

    @Modifying
    @Query("update AppUser  u set u.deleted=false where u.id=:id")
    void deleteUserById(Long id);

    @Modifying
    @Query("update AppUser u set u.deleted=false where u=:appUser")
    void deleteUser(AppUser appUser);
}

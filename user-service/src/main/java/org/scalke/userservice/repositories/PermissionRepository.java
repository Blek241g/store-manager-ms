package org.scalke.userservice.repositories;

import org.scalke.userservice.entities.AppPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PermissionRepository extends JpaRepository<AppPermission, Long> {
    AppPermission findAppPermissionByName(String name);

    AppPermission findFirstByName(final String name);
}

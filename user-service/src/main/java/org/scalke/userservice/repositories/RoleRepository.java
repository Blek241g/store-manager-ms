package org.scalke.userservice.repositories;

import org.scalke.userservice.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findAppRoleByName(String name);
    AppRole findFirstByName(String name);
}

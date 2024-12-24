package org.scalke.userservice.services;


import org.scalke.userservice.entities.AppPermission;
import org.scalke.userservice.entities.AppRole;
import org.scalke.userservice.exceptions.PermissionServiceLogicException;
import org.scalke.userservice.exceptions.RoleServiceLogicException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    AppRole addNewRole(AppRole appRole) throws RoleServiceLogicException;
    AppRole findAppRoleByName(String name) throws RoleServiceLogicException;
    AppRole findAppRoleById(long id) throws RoleServiceLogicException;
    Page<AppRole> findAllAppRoles(Pageable pageable) throws RoleServiceLogicException;
    AppRole updateAppRole(AppRole appRole) throws RoleServiceLogicException;
    AppRole updateAppRole(long roleId, String roleName) throws RoleServiceLogicException;
    void deleteAppRole(long id) throws RoleServiceLogicException;
    void deleteAppRole(AppRole appRole) throws RoleServiceLogicException;
    void deleteAppRole(String name) throws RoleServiceLogicException;
}

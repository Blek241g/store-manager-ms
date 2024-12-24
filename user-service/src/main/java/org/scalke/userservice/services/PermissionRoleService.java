package org.scalke.userservice.services;

import org.scalke.userservice.entities.AppPermission;
import org.scalke.userservice.entities.AppRole;
import org.scalke.userservice.exceptions.PermissionRoleServiceLogicException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionRoleService {
    AppRole addPermissionToRole(Long roleId, Long permissionId) throws PermissionRoleServiceLogicException;
    AppRole addPermissionToRole(String role, String permission) throws PermissionRoleServiceLogicException;
    AppRole addPermissionToRole(AppRole role, AppPermission permission) throws PermissionRoleServiceLogicException;
    AppRole removePermissionFromRole(Long roleId, Long permissionId) throws PermissionRoleServiceLogicException;
    AppRole removePermissionFromRole(String role, String permission) throws PermissionRoleServiceLogicException;
    AppRole removePermissionFromRole(AppRole appRole, AppPermission appPermission) throws PermissionRoleServiceLogicException;

}

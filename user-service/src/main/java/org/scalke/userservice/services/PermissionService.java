package org.scalke.userservice.services;


import org.scalke.userservice.entities.AppPermission;
import org.scalke.userservice.exceptions.PermissionServiceLogicException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService {
    AppPermission addNewPermission(AppPermission appPermission) throws PermissionServiceLogicException;
    AppPermission findPermissionByName(String name) throws PermissionServiceLogicException;
    Page<AppPermission> findPermissions(Pageable pageable) throws PermissionServiceLogicException;
    AppPermission findPermissionById(long id) throws PermissionServiceLogicException;
    AppPermission updatePermission(AppPermission permission) throws PermissionServiceLogicException;
    AppPermission updatePermission(long permissionId, String permissionName) throws PermissionServiceLogicException;
    void deletePermissionBy(long id) throws PermissionServiceLogicException;
    void deletePermission(String name) throws PermissionServiceLogicException;

}

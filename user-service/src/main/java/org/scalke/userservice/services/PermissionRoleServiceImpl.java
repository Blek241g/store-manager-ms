package org.scalke.userservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scalke.userservice.constants.AppMessage;
import org.scalke.userservice.entities.AppPermission;
import org.scalke.userservice.entities.AppRole;
import org.scalke.userservice.exceptions.PermissionRoleServiceLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class PermissionRoleServiceImpl implements PermissionRoleService {

    private RoleService roleService;
    private PermissionService permissionService;



    @Override
    public AppRole addPermissionToRole(Long roleId, Long permissionId) throws PermissionRoleServiceLogicException {
        try {
            AppRole role = roleService.findAppRoleById(roleId);
            AppPermission permission = permissionService.findPermissionById(permissionId);

            if(!role.getPermissions().contains(permission)) {
                role.getPermissions().add(permission);
                return roleService.updateAppRole(role);
            }else {
                throw new PermissionRoleServiceLogicException("Role has already have this permission", HttpStatus.CONFLICT);
            }
        }catch (PermissionRoleServiceLogicException e){
            throw e;
        }
        catch (Exception e) {
            log.error("Failed to add permission to role roleId and PermissionId"+e.getMessage());
            throw new PermissionRoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AppRole addPermissionToRole(String role, String permission) throws PermissionRoleServiceLogicException {
        try {
            AppRole role1 = roleService.findAppRoleByName(role);
            AppPermission permission1 = permissionService.findPermissionByName(permission);
            return addPermissionToRole(role1, permission1);
        }catch (Exception e) {
            log.error("Failed to add permission to role with role name and permission name"+e.getMessage());
            throw new PermissionRoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AppRole addPermissionToRole(AppRole role, AppPermission permission) throws PermissionRoleServiceLogicException {
        try {
            if(role.getPermissions()!= null && !role.getPermissions().contains(permission)) {
                role.getPermissions().add(permission);
            }else if(role.getPermissions()==null) {
                role.setPermissions(new ArrayList<>());
                role.getPermissions().add(permission);
            }
            else {
                throw new PermissionRoleServiceLogicException("Role has already have this permission", HttpStatus.CONFLICT);
            }
            return roleService.updateAppRole(role);
        }catch (PermissionRoleServiceLogicException e){
            throw e;
        }
        catch (Exception e) {
            log.error("Failed to add permission to role Role type and Permission type"+e.getMessage());
            throw new PermissionRoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AppRole removePermissionFromRole(Long roleId, Long permissionId) throws PermissionRoleServiceLogicException {
        try {
            AppRole role = roleService.findAppRoleById(roleId);
            AppPermission permission = permissionService.findPermissionById(permissionId);
            return removePermissionFromRole(role, permission);
        }catch (Exception e) {
            log.error("Failed to remove permission from role with id role and id permission "+e.getMessage());
            throw new PermissionRoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AppRole removePermissionFromRole(String role, String permission) throws PermissionRoleServiceLogicException {
        try {
            AppRole role1 = roleService.findAppRoleByName(role);
            AppPermission permission1 = permissionService.findPermissionByName(permission);
            return removePermissionFromRole(role1, permission1);
        }catch (Exception e) {
            log.error("Failed to remove permission from role with role name and permission name"+e.getMessage());
            throw new PermissionRoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AppRole removePermissionFromRole(AppRole appRole, AppPermission appPermission) throws PermissionRoleServiceLogicException {
        try {
            if(appRole.getPermissions().contains(appPermission)) {
                appRole.getPermissions().remove(appPermission);
                return roleService.updateAppRole(appRole);
            }else {
                throw new PermissionRoleServiceLogicException("Role haven't this permission", HttpStatus.BAD_REQUEST);
            }
        }catch (PermissionRoleServiceLogicException e){
            throw e;
        }
        catch (Exception e) {
            log.error("Failed to add permission to role "+e.getMessage());
            throw new PermissionRoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

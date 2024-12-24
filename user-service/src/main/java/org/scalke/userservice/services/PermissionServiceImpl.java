package org.scalke.userservice.services;

import lombok.extern.slf4j.Slf4j;
import org.scalke.userservice.constants.AppMessage;
import org.scalke.userservice.entities.AppPermission;
import org.scalke.userservice.exceptions.PermissionServiceLogicException;
import org.scalke.userservice.repositories.PermissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PermissionServiceImpl implements PermissionService{
    private PermissionRepository permissionRepository;


    @Override
    public AppPermission addNewPermission(AppPermission appPermission) throws PermissionServiceLogicException {
        try {
            return permissionRepository.save(appPermission);
        }catch (Exception e) {
            log.error("Failed to add new permission " + e.getMessage());
            throw new PermissionServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AppPermission findPermissionByName(String name) throws PermissionServiceLogicException {
        try {
//            AppPermission permission = permissionRepository.findAppPermissionByName(name);
            AppPermission permission = permissionRepository.findFirstByName(name);

            if(permission == null) {
                throw new PermissionServiceLogicException("Permission with name "+name+" not found", HttpStatus.NOT_FOUND);
            }
            return permission;
        }catch (PermissionServiceLogicException e) {
            throw e;
        }
        catch (Exception e) {
            log.error("Failed to fetch permission with name" + e.getMessage());
            throw new PermissionServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Page<AppPermission> findPermissions(Pageable pageable) throws PermissionServiceLogicException {
        try {
            return permissionRepository.findAll(pageable);
        }catch (Exception e) {
            log.error("Failed to fetch permissions: " + e.getMessage());
            throw new PermissionServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    public AppPermission findPermissionById(long id) throws PermissionServiceLogicException {
        try {
            return permissionRepository.findById(id).orElseThrow(() -> new PermissionServiceLogicException("Permission with id:"+id+" not found !",
                    HttpStatus.NOT_FOUND));
        }catch (PermissionServiceLogicException e) {
            throw e;
        }
        catch (Exception e) {
            log.error("Failed to fetch permission with id" + e.getMessage());
            throw new PermissionServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AppPermission updatePermission(AppPermission permission) throws PermissionServiceLogicException {
        try {
            AppPermission appPermission = findPermissionById(permission.getId());
            return permissionRepository.save(appPermission);
        }catch (Exception e) {
            log.error("Failed to update permission with Permission type" + e.getMessage());
            throw new PermissionServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public AppPermission updatePermission(long permissionId, String permissionName) throws PermissionServiceLogicException {
        try {
            AppPermission appPermission = findPermissionById(permissionId);
            appPermission.setName(permissionName);
            return permissionRepository.save(appPermission);
        }catch (Exception e) {
            log.error("Failed to update permission with permission id and permission name" + e.getMessage());
            throw new PermissionServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deletePermissionBy(long id) throws PermissionServiceLogicException {
        try {
            findPermissionById(id);
            permissionRepository.deleteById(id);
        }catch (Exception e) {
            log.error("Failed to delete permission with id" + e.getMessage());
            throw new PermissionServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deletePermission(String name) throws PermissionServiceLogicException {
        try {
            AppPermission permission = findPermissionByName(name);
            permissionRepository.delete(permission);
        }catch (Exception e) {
            log.error("Failed to delete permission with name" + e.getMessage());
            throw new PermissionServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

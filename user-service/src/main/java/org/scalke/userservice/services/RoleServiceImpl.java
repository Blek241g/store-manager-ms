package org.scalke.userservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scalke.userservice.constants.AppMessage;
import org.scalke.userservice.entities.AppRole;
import org.scalke.userservice.exceptions.RoleServiceLogicException;
import org.scalke.userservice.repositories.RoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Override
    public AppRole addNewRole(AppRole appRole) throws RoleServiceLogicException {
      try {
          return roleRepository.save(appRole);
      }catch (Exception e) {
          log.error("Failed to add new role" + e.getMessage());
          throw new RoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @Override
    public AppRole findAppRoleByName(String name) throws RoleServiceLogicException {
       try {
           return roleRepository.findFirstByName(name);
       }catch (Exception e) {
           log.error("Failed to find app role by name" + e.getMessage());
           throw new RoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Override
    public AppRole findAppRoleById(long id) throws RoleServiceLogicException {
        try {
            return roleRepository.findById(id).orElseThrow(() -> new RoleServiceLogicException("Role with id "+id+" not found !",
                    HttpStatus.NOT_FOUND));
        }catch (RoleServiceLogicException e){
            throw e;
        }
        catch (Exception e) {
            log.error("Failed to find app role by id" + e.getMessage());
            throw new RoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Page<AppRole> findAllAppRoles(Pageable pageable) throws RoleServiceLogicException {
        try {
            return roleRepository.findAll(pageable);
        }catch (Exception e) {
            log.error("Failed to find app roles page" + e.getMessage());
            throw new RoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AppRole updateAppRole(AppRole appRole) throws RoleServiceLogicException {
        try {
            AppRole role = findAppRoleById(appRole.getId());
            return roleRepository.save(role);
        }catch (Exception e) {
            log.error("Failed to update AppRole with AppRole type" + e.getMessage());
            throw new RoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AppRole updateAppRole(long roleId, String roleName) throws RoleServiceLogicException {
        try {
            AppRole role = findAppRoleById(roleId);
            role.setName(roleName);
            return roleRepository.save(role);
        }catch (Exception e) {
            log.error("Failed to update app role" + e.getMessage());
            throw new RoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteAppRole(long id) throws RoleServiceLogicException {
        try {
            roleRepository.deleteById(id);
        }catch (Exception e) {
            log.error("Failed to delete AppRole with id" + e.getMessage());
            throw new RoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteAppRole(AppRole appRole) throws RoleServiceLogicException {
        try {
            AppRole role = findAppRoleById(appRole.getId());
            roleRepository.delete(appRole);
        }catch (Exception e) {
            log.error("Failed to delete AppRole" + e.getMessage());
            throw new RoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteAppRole(String name) throws RoleServiceLogicException {
        try {
            AppRole role = findAppRoleByName(name);
            roleRepository.delete(role);
        }catch (Exception e) {
            log.error("Failed to delete AppRole with name" + e.getMessage());
            throw new RoleServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

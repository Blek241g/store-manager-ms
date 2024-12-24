package org.scalke.userservice.services;

import org.scalke.userservice.entities.AppRole;
import org.scalke.userservice.entities.AppUser;
import org.scalke.userservice.exceptions.RoleUserServiceLogicException;

public interface RoleUserService {
    void addRoleToUser(Long userId, Long roleId) throws RoleUserServiceLogicException;
    void addRoleToUser(AppUser user, AppRole role) throws RoleUserServiceLogicException;

}

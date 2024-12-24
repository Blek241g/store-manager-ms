package org.scalke.userservice.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.scalke.userservice.constants.AppMessage;
import org.scalke.userservice.entities.AppRole;
import org.scalke.userservice.entities.AppUser;
import org.scalke.userservice.exceptions.RoleUserServiceLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class RoleUserServiceImpl implements RoleUserService {

    private RoleService roleService;
    private UserService userService;

    @Override
    public void addRoleToUser(Long userId, Long roleId) throws RoleUserServiceLogicException {
        try {
            AppUser appUser = userService.findUserById(userId);
            AppRole role = roleService.findAppRoleById(roleId);
            addRoleToUser(appUser, role);
        }catch (Exception e) {
            log.error("Error while adding role to user with userId and roleId " + e.getMessage());
            throw new RoleUserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void addRoleToUser(AppUser user, AppRole role) throws RoleUserServiceLogicException {
        try {
            if(user.getRoles() !=null && !user.getRoles().contains(role)) {
                user.getRoles().add(role);
            }else if(user.getRoles() == null){
                user.setRoles(new ArrayList<AppRole>());
                user.getRoles().add(role);
            }
            else{
                throw new RoleUserServiceLogicException("User has already this role", HttpStatus.CONFLICT);
            }
            userService.updateUser(user);
        }catch (RoleUserServiceLogicException e){
            throw e;
        }
        catch (Exception e) {
            log.error("Error while adding role to user " + e.getMessage());
            throw new RoleUserServiceLogicException(AppMessage.SOME_THING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

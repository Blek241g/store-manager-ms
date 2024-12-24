package org.scalke.userservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModuleUserServiceImpl implements ModuleUserService {
    public RoleUserService roleUserService;
    public UserService userService;
    public RoleService roleService;
}

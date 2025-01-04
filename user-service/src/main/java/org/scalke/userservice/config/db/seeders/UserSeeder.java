package org.scalke.userservice.config.db.seeders;

import com.github.javafaker.App;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.scalke.userservice.constants.UserType;
import org.scalke.userservice.entities.AppPermission;
import org.scalke.userservice.entities.AppRole;
import org.scalke.userservice.entities.AppUser;
import org.scalke.userservice.exceptions.PermissionRoleServiceLogicException;
import org.scalke.userservice.exceptions.PermissionServiceLogicException;
import org.scalke.userservice.exceptions.RoleServiceLogicException;
import org.scalke.userservice.exceptions.UserServiceLogicException;
import org.scalke.userservice.mappers.UserMapper;
import org.scalke.userservice.services.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j

public class UserSeeder implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup;
    RoleService roleService;
    UserService userService;
    RoleUserService roleUserService;
    PermissionService permissionService;
    PermissionRoleService permissionRoleService;
    UserMapper userMapper;
    Faker faker;

    public UserSeeder(RoleService roleService, UserService userService, RoleUserService roleUserService,
                      PermissionService permissionService, PermissionRoleService permissionRoleService,
                      UserMapper userMapper) {
        this.alreadySetup = false;
        this.roleService = roleService;
        this.userService = userService;
        this.roleUserService = roleUserService;
        this.permissionService = permissionService;
        this.permissionRoleService = permissionRoleService;
        this.userMapper = userMapper;
        faker = new Faker(Locale.of("fr"));
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        try {
            if (alreadySetup)
                return;



            List<AppPermission> superAdminPermissions = getSuperAdminPermission();
            List<AppPermission> ownerPermissions = getOwnerPermission();
            AppRole superAdminRole =  createRoleIfNotExist("SUPER_ADMIN", superAdminPermissions);
            AppRole ownerRole = createRoleIfNotExist("OWNER", ownerPermissions);
            AppRole agentRole = createRoleIfNotExist("REGULAR", null);

            AppUser superAdmin = createUserIfNotExist(AppUser.builder()
                    .email("super_admin@example.com")
                    .password("super_admin")
                    .username("super_admin")
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .userType(UserType.ADMIN)
                    .isActive(true)
                    .deleted(false)
                    .avatar("super_admin.jpeg")
                    .createdBy(0L)
                    .build());

            AppUser owner = createUserIfNotExist(AppUser.builder()
                    .email("owner@example.com")
                    .password("owner")
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .username("owner")
                    .isActive(true)
                    .deleted(false)
                    .userType(UserType.OWNER)
                    .avatar("owner.jpeg")
                    .createdBy(superAdmin.getId())
                    .roles(List.of(ownerRole))
                    .build());

            AppUser agent = createUserIfNotExist(AppUser.builder()
                    .email("agent@example.com")
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .password("agent")
                    .username("agent")
                    .userType(UserType.REGULAR)
                    .isActive(true)
                    .deleted(false)
                    .avatar("agent.jpeg")
                    .createdBy(owner.getId())
                    .build());

            permissionRoleService.addPermissionToRole("REGULAR", "show_product");
            roleUserService.addRoleToUser(superAdmin.getId(), superAdminRole.getId());
            roleUserService.addRoleToUser(agent, agentRole);

            AppRole role1 = createRoleIfNotExist("open_store", null);
            roleUserService.addRoleToUser(agent, role1);

            log.info("Create others users");
            int nbUsers = 1;
            createUsers(nbUsers, superAdmin);
            createUsers(nbUsers, owner);
        }catch (Exception e) {
            log.error(e.getMessage(), e);

        }
    }

    @Transactional
    public void createUsers(int n, AppUser creator) throws UserServiceLogicException {
        String name;
        UserType type;
        if(creator.getUserType() == UserType.ADMIN){
            name = "owner"+creator.getId();
            type = UserType.OWNER;
            log.info("In createUsers, creatorID : {}", creator.getId());
        }else if(creator.getUserType() == UserType.OWNER){
            name = "agent"+creator.getId();
            type= UserType.REGULAR;
        }else{
            name = "user"+creator.getId();
            type = UserType.REGULAR;
        }
        for(int i=0; i<n; i++){
            createUserIfNotExist(AppUser.builder()
                    .email(faker.internet().emailAddress())
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .phoneNumber(faker.phoneNumber().phoneNumber())
                    .password(name)
                    .username(name+i)
                    .userType(type)
                    .isActive(true)
                    .deleted(false)
                    .avatar("agent.jpeg")
                    .createdBy(creator.getId())
                    .build());
        }

    }

    @Transactional
    public AppRole createRoleIfNotExist(String name, List<AppPermission> permissions) throws RoleServiceLogicException {

        AppRole role = roleService.findAppRoleByName(name);
        if (role == null) {
            role = new AppRole();
            role.setName(name);
            role.setPermissions(permissions);
            role= roleService.addNewRole(role);
        }
        return role;
    }

    @Transactional
    public AppUser createUserIfNotExist(AppUser appUser) throws UserServiceLogicException {
        log.info("Creating user in createUserIfNotExist {}", appUser);
        AppUser user = userMapper.dtoToEntity(userService.findUserByEmail(appUser.getEmail()));
        log.info("found user {}", user);
        if(user == null){
           return userMapper.dtoToEntity(userService.addNewUser(appUser));
        }
        return user;
    }

    @Transactional
    public AppPermission createPermissionIfNotExists(AppPermission appPermission) throws PermissionServiceLogicException {
        AppPermission permission = null;
        try {
            permission = permissionService.findPermissionByName(appPermission.getName());
            return permission;
        }catch (PermissionServiceLogicException e) {
            if(e.status == HttpStatus.NOT_FOUND){
                permission = permissionService.addNewPermission(appPermission);
                return permission;
            }
        }

        return permission;
    }

    @Transactional
    public List<AppPermission> getSuperAdminPermission(){
        ArrayList<String> permissionNames = new ArrayList<>();
        ArrayList<AppPermission> permissions = new ArrayList<>();
        permissionNames.add("manage_super_admin_dashboard");
        permissionNames.add("manage_user");
        permissionNames.add("create_user");
        permissionNames.add("show_user");
        permissionNames.add("edit_user");
        permissionNames.add("delete_user");

        permissionNames.add("manage_role");
        permissionNames.add("create_role");
        permissionNames.add("show_role");
        permissionNames.add("edit_role");
        permissionNames.add("delete_role");

        permissionNames.add("manage_permission");
        permissionNames.add("create_permission");
        permissionNames.add("show_permission");
        permissionNames.add("edit_permission");
        permissionNames.add("delete_permission");

        permissionNames.add("manage_plan");
        permissionNames.add("create_plan");
        permissionNames.add("show_plan");
        permissionNames.add("edit_plan");
        permissionNames.add("manage_order");


        permissionNames.forEach(p ->
                {
                    try {
                        permissions.add(createPermissionIfNotExists(new AppPermission(null, p)));
                    } catch (PermissionServiceLogicException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return permissions;
    }

    @Transactional
    public List<AppPermission> getOwnerPermission(){
        ArrayList<String> permissionNames = new ArrayList<>();
        ArrayList<AppPermission> permissions = new ArrayList<>();
        permissionNames.add("manage_product");
        permissionNames.add("create_product");
        permissionNames.add("show_product");
        permissionNames.add("edit_product");
        permissionNames.add("delete_product");

        permissionNames.forEach(p -> {
            try {
                permissions.add(createPermissionIfNotExists(new AppPermission(null, p)));
            } catch (PermissionServiceLogicException e) {
                throw new RuntimeException(e);
            }
        });
        return permissions;
    }
}
